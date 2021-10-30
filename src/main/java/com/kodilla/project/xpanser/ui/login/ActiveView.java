package com.kodilla.project.xpanser.ui.login;

import com.kodilla.project.xpanser.backend.service.AuthService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.List;
import java.util.Map;

@Route("active")
@PageTitle("Active | Xpanser")
@JsModule("./styles/shared-styles.js")
@CssImport("./styles/views/active/active-view.css")
public class ActiveView extends VerticalLayout implements BeforeEnterObserver {

    private final AuthService authService;

    public ActiveView(AuthService authService) {
        this.authService = authService;
        addClassName("active-view");
        add(
                new RouterLink("Back to login page", LoginView.class)
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        try {
            Map<String, List<String>> params = event.getLocation().getQueryParameters().getParameters();
            String code = params.get("code").get(0);
            authService.activate(code);
            Notification.show("Account activated successfully!");
        } catch (AuthService.AuthException e) {
            Notification.show("Provided link is invalid.");
        }

    }
}
