package com.kodilla.project.xpanser.ui.login;

import com.kodilla.project.xpanser.backend.service.AuthService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login | Xpanser")
public class LoginView extends VerticalLayout {

    public LoginView(AuthService authService) {
        addClassName("login-view");
        TextField email = new TextField();
        email.setPlaceholder("Email");
        PasswordField password = new PasswordField();
        password.setPlaceholder("Password");
        add(
                new H1("Xpanser App"),
                email,
                password,
                new Button("Login", e -> {
                    try {
                        authService.authenticate(email.getValue(), password.getValue());
                        UI.getCurrent().navigate("/");
                    } catch (AuthService.AuthException authException) {
                        Notification.show("Wrong credentials.");
                    }
                })
        );
    }
}
