package com.thekey.stylekeyserver.auth.domain.oauth;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.function.Function;
import org.springframework.stereotype.Component;

import com.thekey.stylekeyserver.auth.domain.enums.OAuthProvider;

@Component
public class RequestOAuthInfoService {
    private final Map<OAuthProvider, OAuthApiClient> clients;

    public RequestOAuthInfoService(List<OAuthApiClient> clients) {
        this.clients = clients.stream().collect(
                Collectors.toUnmodifiableMap(OAuthApiClient::oAuthProvider, Function.identity())
        );
    }

    public OAuthInfoResponse request(OAuthLoginParams params) {
        OAuthApiClient client = clients.get(params.oAuthProvider());
        String accessToken = client.requestAccessToken(params);
        return client.requestOauthInfo(accessToken);
    }
}
