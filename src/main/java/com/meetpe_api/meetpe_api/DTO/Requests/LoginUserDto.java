package com.meetpe_api.meetpe_api.DTO.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUserDto {
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @NotNull(message = "email is required")
    private String email;
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password cannot be blank")
    private String password;

}
