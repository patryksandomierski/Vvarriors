package com.oliwa.view;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = "/test-page")
@Theme("valo")
public class TestPage extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        LoginForm loginForm = new LoginForm();
        loginForm.setUsernameCaption("Nazwa uzytkownika:");
        loginForm.setPasswordCaption("Haslo:");
        loginForm.setCaption("Caption ziooo");




        Panel panel = new Panel("This is a Panel");
//        panel.setWidth(50, Unit.PERCENTAGE);
        VerticalLayout panelContent = new VerticalLayout();
        panelContent.setWidth(50, Unit.PERCENTAGE);
        panelContent.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        panelContent.addComponent(new Label("Hello!"));
        panelContent.addComponent(loginForm);
//        panelContent.set

//        panel.setContent(loginForm);
        panel.setContent(panelContent);
        setContent(panel);
    }
}
