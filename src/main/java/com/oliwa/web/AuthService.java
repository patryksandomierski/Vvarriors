package com.oliwa.web;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;

public class AuthService {

    public static final String SESSION_USERNAME = "username";

    private static final String COOKIE_NAME = "remember-me";

    public static boolean isAuthenticated() {
        return VaadinSession.getCurrent().getAttribute(SESSION_USERNAME) != null || loginRememberedUser();
    }

    public static boolean login(String userName, String password, boolean rememberMe) {
        if(UserService.isUserAuthenticated(userName, password)) {
            VaadinSession.getCurrent().setAttribute(SESSION_USERNAME, userName);

            if(rememberMe) {
                rememberUser(userName);
            }

            return true;
        }

        return false;
    }

    public static void logOut() {
        Optional<Cookie> cookie = getRememberMeCookie();
        if(cookie.isPresent()) {
            String id = cookie.get().getValue();
            UserService.forgetUser(id);
            deleteRememberMeCookie();
        }

        VaadinSession.getCurrent().close();
        Page.getCurrent().setLocation("");
    }

    private static Optional<Cookie> getRememberMeCookie() {
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(COOKIE_NAME))
                .findFirst();
    }

    private static boolean loginRememberedUser() {
        Optional<Cookie> rememberMeCookie = getRememberMeCookie();

        if(rememberMeCookie.isPresent()) {
            String id = rememberMeCookie.get().getValue();
            String userName = UserService.getRememberedUser(id);

            if(userName != null) {
                VaadinSession.getCurrent().setAttribute(SESSION_USERNAME, userName);

                return true;
            }
        }

        return false;
    }

    private static void rememberUser(String userName) {
        String id = UserService.rememberUser(userName);

        Cookie cookie = new Cookie(COOKIE_NAME, id);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24); // 1 day
        VaadinService.getCurrentResponse().addCookie(cookie);
    }

    private static void deleteRememberMeCookie() {
        Cookie cookie = new Cookie(COOKIE_NAME, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        VaadinService.getCurrentResponse().addCookie(cookie);
    }
}
