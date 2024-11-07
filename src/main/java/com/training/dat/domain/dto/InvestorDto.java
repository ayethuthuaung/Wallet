package com.training.dat.domain.dto;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints;

@Getter
@Setter
public class InvestorDto {
  
    @NotBlank
    @Size(max = 50)
    private String investorAccountName;

    @NotBlank
    private String accountType;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private String dateOfBirth;
}
