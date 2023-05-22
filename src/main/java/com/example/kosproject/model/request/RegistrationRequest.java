package com.example.kosproject.model.request;

import com.example.kosproject.util.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class RegistrationRequest {
    @NotBlank(message = "User Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    private String phone;

    private String address;
    private String password;

//    @NotBlank(message = "Role is required")
//    @Pattern(regexp = "ADMIN|TENANT", message = "Role must ADMIN or TENANT")
//    private Role role;

//    private String isActive;
}
