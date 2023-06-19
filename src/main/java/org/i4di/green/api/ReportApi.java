package org.i4di.green.api;
import org.i4di.green.dto.ReportDTO;
import org.i4di.green.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/reports")
public class ReportApi {

    private final ReportService reportService;

    @Autowired
    public ReportApi(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(reportService.list(), HttpStatus.OK);
    }

    @GetMapping("/reports-of-user/{id}")
    public ResponseEntity<?> listReportsFromUser(@PathVariable(value="id")Long id) {
        return new ResponseEntity<>(reportService.listReportsOfUser(id), HttpStatus.OK);
    }

    @GetMapping("/broj-resenih")
    public Long vratiBrojUklonjenihDeponija() {
        return reportService.countResolved();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable(value = "id") Long id) {
        Optional<ReportDTO> byId = reportService.show(id);

        return byId
                .map(reportDTO -> new ResponseEntity<>(reportDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ReportDTO reportDTO) {
        Optional<ReportDTO> reportDTOOptional = reportService.create(reportDTO);

        if (!reportDTOOptional.isPresent()) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reportDTOOptional, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        boolean result = reportService.delete(id);

        return result
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ReportDTO reportDTO) {
        Optional<ReportDTO> updated = reportService.update(id, reportDTO);
        return updated
                .map(updatedReportDTO -> new ResponseEntity<>(updatedReportDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
