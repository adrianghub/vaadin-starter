package com.kodilla.project.xpanser.ui.login;

import com.kodilla.project.xpanser.backend.service.AuthService;
import com.kodilla.project.xpanser.ui.register.RegisterView;
import com.kodilla.project.xpanser.util.BottomMessagePanel;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("login")
@PageTitle("Login | Xpanser")
@JsModule("./styles/shared-styles.js")
@CssImport("./styles/views/login/login-view.css")
public class LoginView extends VerticalLayout {

    private final AuthService authService;

    public LoginView(AuthService authService) {
        this.authService = authService;
        addClassName("login-view");
        TextField email = new TextField();
        email.setPlaceholder("Email");
        PasswordField password = new PasswordField();
        password.setPlaceholder("Password");

        Paragraph registerMessage = new Paragraph("Don't have an account?");
        RouterLink registerLink = new RouterLink("Register", RegisterView.class);

        add(
                new H1("Xpanser App"),
                email,
                password,
                new Button("Login", e -> handleLogin(
                        email.getValue(),
                        password.getValue()
                )),
                new Paragraph("Secret credentials hidden somewhere in dev tools..."),
                BottomMessagePanel.addBottomMessage(registerMessage, registerLink, "register-message")
        );
    }

    private void handleLogin(String email, String password) {
        try {
            authService.authenticate(email, password);
            UI.getCurrent().navigate("");
        } catch (AuthService.AuthException authException) {
            Notification.show("Wrong credentials.");
        }
    }
}
