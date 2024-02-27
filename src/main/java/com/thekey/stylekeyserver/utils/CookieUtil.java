package com.thekey.stylekeyserver.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.util.SerializationUtils;


import java.util.Base64;
import java.util.Optional;

import static com.thekey.stylekeyserver.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository.ACCESS_TOKEN;
import static com.thekey.stylekeyserver.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository.REFRESH_TOKEN;


public class CookieUtil {

    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);

        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    public static String serialize(Object obj) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(
                SerializationUtils.deserialize(
                        Base64.getUrlDecoder().decode(cookie.getValue())
                )
        );
    }

    public static void addCookiesToHeaders(HttpServletRequest request, HttpServletResponse response) {
        Optional<Cookie> accessTokenCookie = getCookie(request, ACCESS_TOKEN);
        Optional<Cookie> refreshTokenCookie = getCookie(request, REFRESH_TOKEN);

        accessTokenCookie.ifPresent(cookie ->
                response.addHeader(HttpHeaders.SET_COOKIE, getCookieString(cookie))
        );

        refreshTokenCookie.ifPresent(cookie ->
                response.addHeader(HttpHeaders.SET_COOKIE, getCookieString(cookie))
        );
    }

    private static String getCookieString(Cookie cookie) {
        return cookie.getName() + "=" + cookie.getValue()
                + "; Path=" + cookie.getPath()
                + "; Max-Age=" + cookie.getMaxAge()
                + "; HttpOnly";
    }
}

