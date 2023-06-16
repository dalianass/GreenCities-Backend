package org.i4di.green.domain;

import javax.persistence.*;
import java.sql.Time;

@Entity
public class Report extends Timestamped {
    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String title;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private Double latitude;

    @Column(nullable = true)
    private Double longitude;

    @Column(nullable = true)
    private String photo;

    @Column(nullable = true, columnDefinition = "boolean default false")
    private Boolean deleted;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(nullable = true, name = "created_by_id", foreignKey = @ForeignKey(name = "FK_REPORT_CREATED_BY"))
    private User createdBy;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(nullable = true, name = "finished_by_id", foreignKey = @ForeignKey(name = "FK_REPORT_FINISHED_BY"))
    private User finishedBy;

    @Column(nullable = true, columnDefinition = "boolean default false")
    private boolean isFinished;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getFinishedBy() {
        return finishedBy;
    }

    public void setFinishedBy(User finishedBy) {
        this.finishedBy = finishedBy;
    }

    public boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean finished) {
        isFinished = finished;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
