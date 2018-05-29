package com.example.kuangjiademo.jwt;

import java.io.Serializable;

/**
 * @author liuyang
 * @since 2018/5/29 16:46
 */
public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
