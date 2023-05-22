package com.example.kosproject.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class EventTenantRequest {
    @NotBlank(message = "Event id is required")
    private Integer eventId;

    @NotBlank(message = "Tenant id is required")
    private Integer tenantId;
}
