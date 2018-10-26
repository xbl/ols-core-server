package com.thoughtworks.nho.service.impl;

import com.thoughtworks.nho.cofiguration.security.JWTUser;
import com.thoughtworks.nho.cofiguration.security.LoginRequestUser;
import com.thoughtworks.nho.exception.InvalidCredentialException;
import com.thoughtworks.nho.repository.TokenAuthRepository;
import com.thoughtworks.nho.service.AuthService;
import com.thoughtworks.nho.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    @Value("${security.jwt.token-prefix:Bearer}")
    private String tokenPrefix;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.expiration-in-seconds}")
    private long expirationInSeconds;

    @Autowired
    private TokenAuthRepository authRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public JWTUser login(HttpServletResponse response, LoginRequestUser loginRequestUser) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestUser.getUsername(), loginRequestUser.getPassword()));
        JWTUser principal = (JWTUser) authenticate.getPrincipal();

        Map payload = StringUtils.readJsonStringAsObject(StringUtils.writeObjectAsJsonString(principal), Map.class);

        response.addHeader(header, String.join(" ", tokenPrefix,
                authRepository.generateToken(payload)));
        return principal;
    }


    @Override
    public JWTUser getAuthorizedJWTUser(HttpServletRequest request) {
        String payload = authRepository.extractAuthorizedPayload(extractToken(request));
        return StringUtils.readJsonStringAsObject(payload, JWTUser.class);
    }

    @Override
    public boolean hasJWTToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(header);
        return StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(tokenPrefix);
    }

    private String extractToken(HttpServletRequest request) {
        if (!hasJWTToken(request)) {
            throw new InvalidCredentialException();
        }
        return request.getHeader(header).substring(tokenPrefix.length() + 1);
    }
}
