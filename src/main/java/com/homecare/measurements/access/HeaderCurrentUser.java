package com.homecare.measurements.access;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.RequiredArgsConstructor;

/**
 * The gateway authenticates the user and forwards their id in {@code X-User-Id}.
 */
@Component
@RequestScope
@RequiredArgsConstructor
public class HeaderCurrentUser implements CurrentUser {

    private final HttpServletRequest request;

    @Override
    public String userId() {
        return request.getHeader("X-User-Id");
    }
}
