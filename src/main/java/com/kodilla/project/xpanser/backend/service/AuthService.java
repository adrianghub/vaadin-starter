package com.kodilla.project.xpanser.backend.service;

import com.kodilla.project.xpanser.backend.entity.Role;
import com.kodilla.project.xpanser.backend.entity.User;
import com.kodilla.project.xpanser.backend.repository.UserRepository;
import com.kodilla.project.xpanser.ui.AboutView;
import com.kodilla.project.xpanser.ui.DashboardView;
import com.kodilla.project.xpanser.ui.MainLayout;
import com.kodilla.project.xpanser.ui.list.ListView;
import com.kodilla.project.xpanser.ui.logout.LogoutView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    public class AuthException extends Exception {
    }

    private final UserRepository userRepository;


    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public record AuthorizedRoute(String route, String name,
                                  Class<? extends Component> view) {
    }

    public void authenticate(String email, String password) throws AuthException {
        User user = userRepository.getByEmail(email);

        if (user != null && user.comparePassword(password)) {
            VaadinSession.getCurrent().setAttribute(User.class, user);
            createRoutes(user.getRole());
        } else {
            throw new AuthException();
        }
    }

    private void createRoutes(Role role) {
        getAuthorizedRoutes(role).stream()
                .forEach(route ->
                        RouteConfiguration.forSessionScope().setRoute(
                                route.route, route.view, MainLayout.class));
    }

    public List<AuthorizedRoute> getAuthorizedRoutes(Role role) {
        var routes = new ArrayList<AuthorizedRoute>();

        if (role.equals(Role.USER)) {
            routes.add(new AuthorizedRoute("", "Product List | Xpanser", ListView.class));
            routes.add(new AuthorizedRoute("dashboard", "Dashboard | Xpanser", DashboardView.class));
            routes.add(new AuthorizedRoute("logout", "Logout", LogoutView.class));

        } else if (role.equals(Role.ADMIN)) {
            routes.add(new AuthorizedRoute("", "Product List | Xpanser", ListView.class));
            routes.add(new AuthorizedRoute("dashboard", "Dashboard | Xpanser", DashboardView.class));
            routes.add(new AuthorizedRoute("about", "About | Expanser", AboutView.class));
            routes.add(new AuthorizedRoute("logout", "Logout", LogoutView.class));
        }

        return routes;
    }

    public void register(String username, String email, String password) {
        userRepository.save(new User(username, email, password, Role.USER));
    }

}
