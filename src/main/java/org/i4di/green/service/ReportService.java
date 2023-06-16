package org.i4di.green.service;

import org.i4di.green.dto.ReportDTO;
import org.i4di.green.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface ReportService {


    /**
     * Lists all reports.
     *
     * @return List of all reports.
     */
    List<ReportDTO> list();

    /**
     * Returns the report with the given {@code id}.
     *
     * @param id Report ID.
     * @return {@link Optional} with report, or empty {@link Optional} if the report with the given ID does not exist.
     */
    Optional<ReportDTO> show(Long id);

    /**
     * Creates a new report.
     *
     * @param reportDTO DTO containing new report data.
     * @return {@link Optional} with created report, or empty {@link Optional} if the report was not created.
     */
    Optional<ReportDTO> create(ReportDTO reportDTO);

    /**
     * Deletes the report with the given {@code id}.
     *
     * @param id Report ID.
     * @return {@code true} if the report is deleted; {@code false} otherwise.
     */
    boolean delete(Long id);

    Optional<ReportDTO> update(Long id, ReportDTO reportDTO);

    Long countResolved();
}
