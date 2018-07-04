package com.thoughtworks.nho.repository;

import java.util.Map;

public interface TokenAuthRepository {

    String generateToken(Map<String, Object> payload);

    String extractAuthorizedPayload(String token);

}