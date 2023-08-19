package com.test.station.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class StationSaveRequest {
    @NotBlank
    private String stationName;

    /*
    If the type here would've been MultipartFile, we could use our custom
    annotation @ValidImage to check whether valid Image has been provided
    or not and throw respective exception accordingly.
    */
    @NotNull
    private String stationImage;
    @NotNull
    private BigDecimal stationPricing;
    @NotBlank
    private String stationAddress;
}
