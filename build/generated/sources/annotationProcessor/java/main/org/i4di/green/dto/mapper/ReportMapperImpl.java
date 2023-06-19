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
    date = "2023-06-19T15:39:07+0200",
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

        reportDTO.setAddress( report.getAddress() );
        reportDTO.setFinishedBy( userMapper.userToUserDTO( report.getFinishedBy() ) );
        reportDTO.setLatitude( report.getLatitude() );
        reportDTO.setPhoto( report.getPhoto() );
        reportDTO.setDescription( report.getDescription() );
        reportDTO.setTitle( report.getTitle() );
        reportDTO.setIsFinished( report.getIsFinished() );
        if ( report.getCreatedAt() != null ) {
            reportDTO.setCreatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).print( report.getCreatedAt() ).trim() );
        }
        reportDTO.setDeleted( report.getDeleted() );
        reportDTO.setCreatedBy( userMapper.userToUserDTO( report.getCreatedBy() ) );
        reportDTO.setId( report.getId() );
        if ( report.getUpdatedAt() != null ) {
            reportDTO.setUpdatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).print( report.getUpdatedAt() ).trim() );
        }
        reportDTO.setLongitude( report.getLongitude() );

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

        report.setAddress( reportDTO.getAddress() );
        report.setFinishedBy( userMapper.userDTOToUser( reportDTO.getFinishedBy() ) );
        report.setLatitude( reportDTO.getLatitude() );
        report.setPhoto( reportDTO.getPhoto() );
        report.setDescription( reportDTO.getDescription() );
        report.setTitle( reportDTO.getTitle() );
        report.setIsFinished( reportDTO.getIsFinished() );
        if ( reportDTO.getCreatedAt() != null ) {
            report.setCreatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).parseDateTime( reportDTO.getCreatedAt() ) );
        }
        report.setDeleted( reportDTO.getDeleted() );
        report.setCreatedBy( userMapper.userDTOToUser( reportDTO.getCreatedBy() ) );
        report.setId( reportDTO.getId() );
        if ( reportDTO.getUpdatedAt() != null ) {
            report.setUpdatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).parseDateTime( reportDTO.getUpdatedAt() ) );
        }
        report.setLongitude( reportDTO.getLongitude() );

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
