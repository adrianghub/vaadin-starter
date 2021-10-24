package com.kodilla.project.expanser.ui.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login | Expanser")
public class LoginView extends VerticalLayout {

    public LoginView() {
        addClassName("login-view");
        TextField email = new TextField();
        email.setPlaceholder("Email");
        PasswordField password = new PasswordField();
        password.setPlaceholder("Password");
        add(
                new H1("Expanser App"),
                email,
                password,
                new Button("Login")
        );
    }
}
