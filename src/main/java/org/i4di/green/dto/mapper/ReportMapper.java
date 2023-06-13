package org.i4di.green.dto.mapper;

import org.i4di.green.domain.Report;
import org.i4di.green.dto.ReportDTO;
import org.i4di.green.dto.mapper.defaults.UserFromId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring", uses={UserMapper.class})
public interface ReportMapper extends UserFromId {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Report.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Report.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "address", target = "address")
    @Mapping(source = "photo", target = "photo")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "deleted", target = "deleted")
    @Mapping(source = "createdBy", target = "createdBy") //ovde potreban User, zato extend UserFromId ?
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    ReportDTO reportToReportDTO(Report report);

    List<ReportDTO> reportsToReportDTOs(List<Report> reports);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Report.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Report.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "address", target = "address")
    @Mapping(source = "photo", target = "photo")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "deleted", target = "deleted")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    Report reportDTOToReport(ReportDTO reportDTO);

    List<Report> reportDTOsToReports(List<ReportDTO> reportDTOs);


}
