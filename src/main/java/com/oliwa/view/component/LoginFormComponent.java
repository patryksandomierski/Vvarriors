package com.oliwa.view.component;

import com.oliwa.view.MainPage;
import com.oliwa.web.AuthService;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LoginFormComponent extends CustomComponent {
    public LoginFormComponent() {
        TextField username = new TextField("Username");
        username.focus();

        PasswordField password = new PasswordField("Password");

        CheckBox rememberMe = new CheckBox("Remember me");

        Button button = new Button("Login", e -> onLogin(username.getValue(), password.getValue(), rememberMe.getValue()));
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        FormLayout formLayout = new FormLayout(username, password, button, rememberMe);
        formLayout.setSizeUndefined();

        VerticalLayout layout = new VerticalLayout(formLayout);
        layout.setSizeFull();
        layout.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);

        setCompositionRoot(layout);
        setSizeFull();
    }

    private void onLogin(String username, String password, boolean rememberMe) {
        if (AuthService.login(username, password, rememberMe)) {
            MainPage ui = (MainPage) UI.getCurrent();
            ui.showPrivateContent();
        } else {
            Notification.show("Kurna, zle!", Notification.Type.ERROR_MESSAGE);
        }
    }
}
