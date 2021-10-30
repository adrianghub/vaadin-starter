package com.kodilla.project.xpanser.ui.register;

import com.kodilla.project.xpanser.backend.service.AuthService;
import com.kodilla.project.xpanser.ui.login.LoginView;
import com.kodilla.project.xpanser.util.BottomMessagePanel;
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

@Route("register")
@PageTitle("Register | Xpanser")
@JsModule("./styles/shared-styles.js")
@CssImport("./styles/views/register/register-view.css")
public class RegisterView extends VerticalLayout {

    private final AuthService authService;

    Paragraph loginMessage = new Paragraph("Already have an account?");
    RouterLink loginLink = new RouterLink("Login", LoginView.class);

    public RegisterView(AuthService authService) {
        this.authService = authService;
        addClassName("register-view");
        TextField username = new TextField();
        username.setPlaceholder("Username");
        TextField email = new TextField();
        email.setPlaceholder("Email");
        PasswordField password = new PasswordField();
        password.setPlaceholder("Password");
        PasswordField confirmedPassword = new PasswordField();
        confirmedPassword.setPlaceholder("Confirm Password");
        add(
                new H1("Xpanser App"),
                username,
                email,
                password,
                confirmedPassword,
                new Button("Register", e -> handleRegister(
                        username.getValue(),
                        email.getValue(),
                        password.getValue(),
                        confirmedPassword.getValue()
                )),
                BottomMessagePanel.addBottomMessage(loginMessage, loginLink, "login-message")
        );
    }

    private void handleRegister(String username, String email, String password, String confirmedPassword) {
        if (username.trim().isEmpty()) {
            Notification.show("Username shouldn't be an empty field.");
        } else if (email.isEmpty()) {
            Notification.show("Email shouldn't be an empty field.");
        } else if (password.isEmpty()) {
            Notification.show("Password shouldn't be an empty field.");
        } else if (!password.equals(confirmedPassword)) {
            Notification.show("Passwords don't match.");
        } else {
            authService.register(username, email, password);
            Notification.show("Link with activation has been sent to provided email address.");
        }
    }
}
