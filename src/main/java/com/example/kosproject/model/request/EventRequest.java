package com.example.kosproject.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class EventRequest {
    @NotBlank(message = "Event Name is required")
    private String eventName;

    private String eventDescription;

    @NotBlank(message = "Event Date is required")
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "Format tanggal harus yyyy-mm-dd")
    private String eventDate;
}
