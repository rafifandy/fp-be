package com.example.kosproject.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PaymentRequest {
    @NotBlank(message = "Tenancy id is required")
    private Integer tenancyId;

    @NotBlank(message = "Payment date is required")
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "Format tanggal harus yyyy-mm-dd")
    private String paymentDate;

    @NotBlank(message = "Payment amount is required")
    private Integer paymentAmount;
}
