package com.eventdrive.challenge.analytics.domain.entities;

import com.eventdrive.challenge.analytics.domain.enums.EventType;
import lombok.Data;
import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;

@Table
public @Data class Event {

    @QuerySqlField(index = true)
    @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Long id;
    @QuerySqlField
    @PrimaryKeyColumn(name = "event_type", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private EventType eventType;
    @QuerySqlField
    @PrimaryKeyColumn(name = "user_email", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    private String userEmail;
    @QuerySqlField
    @Column("payload")
    private String payload;
    @Column("event_date")
    @QuerySqlField
    private Date eventDate;

    public Event(Long id, EventType eventType, String userEmail, String payload, Date eventDate) {
        this.id = id;
        this.eventType = eventType;
        this.userEmail = userEmail;
        this.payload = payload;
        this.eventDate = eventDate;
    }
}