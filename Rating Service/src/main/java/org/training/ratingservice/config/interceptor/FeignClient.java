package org.training.ratingservice.config.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import static org.training.ratingservice.dto.Constants.HEADER_KEY;
import static org.training.ratingservice.dto.Constants.HEADER_VALUE_PREFIX;

public class FeignClient implements RequestInterceptor {

    @Autowired
    private OAuth2AuthorizedClientManager authorizedClientManager;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        String accessToken = authorizedClientManager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("rating-internal").principal("internal").build()).getAccessToken().getTokenValue();
        requestTemplate.header(HEADER_KEY, HEADER_VALUE_PREFIX + accessToken);
    }
}
