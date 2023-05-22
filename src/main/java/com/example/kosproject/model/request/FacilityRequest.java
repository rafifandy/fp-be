package com.example.kosproject.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class FacilityRequest {

    @NotBlank(message = "Facility name is required")
    private String facilityName;

    private String facilityDescription;
}
