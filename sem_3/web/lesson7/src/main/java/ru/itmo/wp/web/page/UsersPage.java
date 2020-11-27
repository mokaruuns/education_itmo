package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @noinspection unused
 */
public class UsersPage {
    private final UserService userService = new UserService();

    private void action(HttpServletRequest request, Map<String, Object> view) {

        // No operations.
    }

    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("users", userService.findAll());
    }

    private void findUser(HttpServletRequest request, Map<String, Object> view) {
        view.put("user",
                userService.find(Long.parseLong(request.getParameter("userId"))));
    }

    private void changePermissions(HttpServletRequest request, Map<String, Object> view) {
        ensureAuth(request);
        if (!((User) request.getSession().getAttribute("user")).isAdmin()) {
            request.getSession().setAttribute("message", "You aren`t admin!");
            throw new RedirectException("/index");
        }
        userService.changePermissions(Long.valueOf(request.getParameter("userId")), request.getParameter("secondAction").equals("true"));
        view.put("changedUser", userService.find(Long.parseLong(request.getParameter("userId"))));
    }

    private void ensureAuth(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("message", "You aren`t entered!");
            throw new RedirectException("/index");
        }
    }
}
