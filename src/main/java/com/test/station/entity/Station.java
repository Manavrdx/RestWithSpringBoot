package com.test.station.entity;

import com.test.station.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "tbl_station"
)
public class Station {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "station_id")
    private Long stationId;

    @Column(name = "station_name", nullable = false)
    private String stationName;

    @Column(name = "station_image", nullable = false)
    private String stationImage; // Image URL

    @Column(name = "station_pricing", nullable = false)
    private BigDecimal stationPricing; //By Default the precision is 19 with a scale of 2

    @Column(name = "station_address", nullable = false)
    private String stationAddress;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate; //nice field to have for analytics purpose

    @Column(name = "last_modified_date", nullable = false)
    private LocalDateTime lastModifiedDate; //nice field to have for analytics purpose

    /* We are never gonna physically delete the station from our database
    We will change the status to deleted */
    @Column(name = "station_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status stationStatus;
}
