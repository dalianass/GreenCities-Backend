package org.i4di.green.service.impl;

import org.i4di.green.domain.Report;
import org.i4di.green.dto.ReportDTO;
import org.i4di.green.dto.mapper.ReportMapper;
import org.i4di.green.dto.mapper.UserMapper;
import org.i4di.green.repository.ReportRepository;
import org.i4di.green.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    private final UserMapper userMapper;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, ReportMapper reportMapper, UserMapper userMapper, UserServiceImpl userServiceImpl) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
        this.userMapper = userMapper;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public List<ReportDTO> list() {
        return reportMapper.reportsToReportDTOs(reportRepository.findAllByOrderByCreatedAtDesc());
    }

    @Override
    public Optional<ReportDTO> show(Long id) {
        Optional<Report> byId = reportRepository.findById(id);

        return byId.map(reportMapper::reportToReportDTO);
    }


    @Override
    public Optional<ReportDTO> create(ReportDTO reportDTO) {

        Long userID = reportDTO.getCreatedBy().getId();
        reportDTO.setCreatedBy(userServiceImpl.show(userID).get());
        Report toCreate = reportMapper.reportDTOToReport(reportDTO);

        return Optional.of(reportMapper.reportToReportDTO(reportRepository.save(toCreate)));
    }


    @Override
    public boolean delete(Long id) {
        Optional<Report> byId = reportRepository.findById(id);

        if (!byId.isPresent() || byId.get().getDeleted()) {
            return false;
        }

        reportRepository.delete(id);
        return true;
    }

    @Override
    public Optional<ReportDTO> update(Long id, ReportDTO reportDTO) {
        Optional<Report> byId = reportRepository.findById(id);

        if (!byId.isPresent()) {
            return Optional.empty();
        }
        byId.get().setDescription(reportDTO.getDescription());
        byId.get().setTitle(reportDTO.getTitle());
        byId.get().setAddress(reportDTO.getAddress());
//        byId.get().setLatitude(reportDTO.getLatitude());
//        byId.get().setLongitude(reportDTO.getLongitude());
        byId.get().setIsFinished(reportDTO.getIsFinished());
//        byId.get().setFinishedBy(userMapper.userDTOToUser(reportDTO.getFinishedBy()));

        return Optional.of(reportMapper.reportToReportDTO(reportRepository.save(byId.get())));

    }

    @Override
    public Long countResolved() {
        Long result = reportRepository.countFinishedReportsCustom();
        return result != null ? result : 0;
    }
}

