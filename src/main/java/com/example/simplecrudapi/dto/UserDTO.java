package com.example.simplecrudapi.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not in valid format")
    private String email;

    @Size(min = 6, max = 6, message = "Zip code must have 6 characters")
    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}$", message = "Zip code is not in valid format")
    private String zipCode;
}
