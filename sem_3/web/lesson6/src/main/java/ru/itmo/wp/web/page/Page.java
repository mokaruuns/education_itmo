package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class Page {
    private final UserService userService = new UserService();
    private HttpServletRequest request;

    public void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }
    public void before(HttpServletRequest request, Map<String, Object> view){
        this.request = request;
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }

        User user = getUser();
        if (user != null) {
            view.put("user", user);
        }
        view.put("userCount", userService.findCount());

    }
    public void after(HttpServletRequest request, Map<String, Object> view){
        view.put("userCount", userService.findCount());
    }

    protected void setMessage(String message){
        request.getSession().setAttribute("message", message);
    }
    protected void setUser(User user){
        request.getSession().setAttribute("user", user);
    }
    protected User getUser(){
        return (User) request.getSession().getAttribute("user");
    }

}
