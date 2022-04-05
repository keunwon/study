package com.myshop.springconfig.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static com.myshop.springconfig.security.WebSecurityConfig.AUTH_COOKIE_NAME;

@RequiredArgsConstructor
public class CookieSecurityContextRepository implements SecurityContextRepository {
    private final UserDetailsService userDetailsService;

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Cookie cookie = findAuthCookie(requestResponseHolder.getRequest());

        if (cookie == null) { return context; }

        final String id = getUserId(cookie);
        if (id != null) { populateAuthentication(context, id); }

        return context;
    }

    private void populateAuthentication(SecurityContext context, String id) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(id);
        context.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
    }

    private String getUserId(Cookie cookie) {
        return URLEncoder.encode(cookie.getValue(), StandardCharsets.UTF_8);
    }

    private Cookie findAuthCookie(HttpServletRequest request) {
        if (request.getCookies() == null) { return null; }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> AUTH_COOKIE_NAME.equals(cookie.getName()))
                .findAny()
                .orElse(null);
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return false;
    }
}
