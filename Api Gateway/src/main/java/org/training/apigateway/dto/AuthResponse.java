package org.training.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {

    private String userId;

    private String accessToken;

    private String refreshId;

    private long expiresAt;

    private Collection<String> authorities;
}
