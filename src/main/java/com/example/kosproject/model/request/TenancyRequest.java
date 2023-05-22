package com.example.kosproject.model.request;

import com.example.kosproject.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class TenancyRequest {
    @NotBlank(message = "Price id is required")
    private Integer priceId;

    @NotBlank(message = "Start date is required")
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "Format tanggal harus yyyy-mm-dd")
    private String startDate;

    @NotBlank(message = "End date is required")
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "Format tanggal harus yyyy-mm-dd")
    private String endDate;

    @NotBlank(message = "User id is required")
    private List<User> user;

}

