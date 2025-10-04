package com.zidtech.security.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDTO {

    private UUID user_uuid;
    private String username;
}
