package com.test.station.dto;

import com.test.station.enums.Status;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class StationUpdateRequest {
    @NotBlank
    private String stationName;
    @NotNull
    private String stationImage;
    @NotNull
    private BigDecimal stationPricing;
    @NotBlank
    private String stationAddress;
    @NotNull
    private Status status;
}
