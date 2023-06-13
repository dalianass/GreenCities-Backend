package org.i4di.green.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Objects;

/**
 * An abstract entity definition, for entities that should be provided by default with the information
 * on creation and last update timestamp. Additionally, the abstract entity declares a generic auto
 * incremental key of type {@link Long}.
 *
 */
@MappedSuperclass
public abstract class Timestamped {

    /**
     * Date and time format pattern with information on time zone offset.
     */
    public static final String ISO_8601_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZ";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdAt;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updatedAt;

    /**
     * Returns the auto-generated primary key of this entity instance.
     *
     * @return Entity primary key.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the primary key of this entity instance to the value specified.
     *
     * @param id Entity primary key to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the timestamp denoting when this entity instance is created.
     *
     * @return Entity creation timestamp.
     */
    public DateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp denoting when this entity instance is created
     * to the value specified.
     *
     * @param createdAt Entity creation timestamp to set.
     */
    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns the timestamp denoting when this entity instance is last
     * updated.
     *
     * @return Entity update timestamp.
     */
    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp denoting when this entity instance is last updated
     * to the value specified.
     *
     * @param updatedAt Entity update timestamp to set.
     */
    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Method invoked before each instance is persisted.
     * <p>
     * Sets the creation and update timestamp to current timestamp.
     */
    @PrePersist
    void onCreate() {
        setCreatedAt(new DateTime());
        setUpdatedAt(this.createdAt);
    }

    /**
     * Method invoked before each instance of this type is updated.
     * <p>
     * Sets the update timestamp to current timestamp.
     */
    @PreUpdate
    void onUpdate() {
        setUpdatedAt(new DateTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timestamped that = (Timestamped) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
