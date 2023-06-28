package org.training.apigateway.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.apigateway.dto.AuthResponse;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OidcUser user,
            Model model
            ){

        AuthResponse response = AuthResponse.builder()
                .accessToken(client.getAccessToken().getTokenValue())
                .userId(user.getEmail())
                .expiresAt(client.getAccessToken().getExpiresAt().getEpochSecond())
                .refreshId(client.getRefreshToken().getTokenValue())
                .authorities(user.getAuthorities().stream().map(grantedAuthority -> {
                    return grantedAuthority.getAuthority();
                }).collect(Collectors.toList())).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
