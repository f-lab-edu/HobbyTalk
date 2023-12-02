package com.jongho.user.application.facade;

import java.util.Map;

public interface AuthUserFacade {
    public Map<String, String> signIn(String username, String password, String userAgent);
}
