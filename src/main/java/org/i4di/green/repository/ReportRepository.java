package org.i4di.green.repository;

import org.i4di.green.domain.Report;
import org.i4di.green.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
    List<Report> findAllByOrderByCreatedAtDesc();

    List<Report> findAllByFinishedById(Long userId);

//    Long countByTitle(String title);

//    @Query("SELECT COUNT(u) FROM Report u WHERE u.createdAt <> u.updatedAt")
//    Long countByDifferentCreatedAtAndUpdatedAt();

    @Query("SELECT COUNT(u) FROM Report u WHERE u.isFinished = true")
    Long countFinishedReportsCustom();

    Optional<Report> findById(Long id);

    @Query(value = "UPDATE project SET deleted = TRUE WHERE id = :id ;", nativeQuery = true)
    @Modifying
    void delete(@Param(value = "id") Long id);

}
