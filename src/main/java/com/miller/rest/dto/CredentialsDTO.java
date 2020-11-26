package com.miller.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredentialsDTO {
    private String userName;
    private String password;
}
