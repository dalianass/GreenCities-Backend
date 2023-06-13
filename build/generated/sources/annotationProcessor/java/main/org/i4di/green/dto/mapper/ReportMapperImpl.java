package org.i4di.green.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.i4di.green.domain.Report;
import org.i4di.green.dto.ReportDTO;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-12T16:43:17+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class ReportMapperImpl implements ReportMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ReportDTO reportToReportDTO(Report report) {
        if ( report == null ) {
            return null;
        }

        ReportDTO reportDTO = new ReportDTO();

        if ( report.getCreatedAt() != null ) {
            reportDTO.setCreatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).print( report.getCreatedAt() ).trim() );
        }
        reportDTO.setAddress( report.getAddress() );
        reportDTO.setDeleted( report.getDeleted() );
        reportDTO.setCreatedBy( userMapper.userToUserDTO( report.getCreatedBy() ) );
        reportDTO.setLatitude( report.getLatitude() );
        reportDTO.setPhoto( report.getPhoto() );
        reportDTO.setDescription( report.getDescription() );
        reportDTO.setId( report.getId() );
        reportDTO.setTitle( report.getTitle() );
        if ( report.getUpdatedAt() != null ) {
            reportDTO.setUpdatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).print( report.getUpdatedAt() ).trim() );
        }
        reportDTO.setLongitude( report.getLongitude() );
        reportDTO.setFinishedBy( userMapper.userToUserDTO( report.getFinishedBy() ) );

        return reportDTO;
    }

    @Override
    public List<ReportDTO> reportsToReportDTOs(List<Report> reports) {
        if ( reports == null ) {
            return null;
        }

        List<ReportDTO> list = new ArrayList<ReportDTO>( reports.size() );
        for ( Report report : reports ) {
            list.add( reportToReportDTO( report ) );
        }

        return list;
    }

    @Override
    public Report reportDTOToReport(ReportDTO reportDTO) {
        if ( reportDTO == null ) {
            return null;
        }

        Report report = new Report();

        if ( reportDTO.getCreatedAt() != null ) {
            report.setCreatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).parseDateTime( reportDTO.getCreatedAt() ) );
        }
        report.setAddress( reportDTO.getAddress() );
        report.setDeleted( reportDTO.getDeleted() );
        report.setCreatedBy( userMapper.userDTOToUser( reportDTO.getCreatedBy() ) );
        report.setLatitude( reportDTO.getLatitude() );
        report.setPhoto( reportDTO.getPhoto() );
        report.setDescription( reportDTO.getDescription() );
        report.setId( reportDTO.getId() );
        report.setTitle( reportDTO.getTitle() );
        if ( reportDTO.getUpdatedAt() != null ) {
            report.setUpdatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).parseDateTime( reportDTO.getUpdatedAt() ) );
        }
        report.setLongitude( reportDTO.getLongitude() );
        report.setFinishedBy( userMapper.userDTOToUser( reportDTO.getFinishedBy() ) );

        return report;
    }

    @Override
    public List<Report> reportDTOsToReports(List<ReportDTO> reportDTOs) {
        if ( reportDTOs == null ) {
            return null;
        }

        List<Report> list = new ArrayList<Report>( reportDTOs.size() );
        for ( ReportDTO reportDTO : reportDTOs ) {
            list.add( reportDTOToReport( reportDTO ) );
        }

        return list;
    }
}
