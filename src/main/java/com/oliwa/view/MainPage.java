package com.oliwa.view;

import com.oliwa.view.component.LoginFormComponent;
import com.oliwa.web.AuthService;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme("valo")
public class MainPage extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        if (!AuthService.isAuthenticated()) {
            setContent(new LoginFormComponent());
        } else {
            showPrivateContent();
        }
    }

    public void showPrivateContent() {
        String username = (String) VaadinSession.getCurrent().getAttribute(AuthService.SESSION_USERNAME);
        Label label = new Label("Welcome, " + username);
        label.addStyleName(ValoTheme.LABEL_HUGE);
        Button button = new Button("WYJAZD", this::logout);
        setContent(new VerticalLayout(label, button));
    }

    private void logout(Button.ClickEvent event) {
        AuthService.logOut();
    }
}
