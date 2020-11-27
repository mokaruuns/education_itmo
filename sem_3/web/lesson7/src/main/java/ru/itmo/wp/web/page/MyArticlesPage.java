package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @noinspection unused
 */
public class MyArticlesPage {
    private final ArticleService articleService = new ArticleService();
    private final UserService userService = new UserService();


    private void action(HttpServletRequest request, Map<String, Object> view) {
        ensureAuth(request);
        view.put("articles", articleService.findByUserId(((User) request.getSession().getAttribute("user")).getId()));
    }

    private void changeVisibility(HttpServletRequest request, Map<String, Object> view) {
        ensureAuth(request);
        if (!request.getSession().getAttribute("user").equals(userService.find(Long.parseLong(request.getParameter("id"))))) {
            request.getSession().setAttribute("message", "You aren't author!");
            throw new RedirectException("/index");
        }
        articleService.changeVisibility(Long.valueOf(request.getParameter("id")));
        view.put("article", articleService.find(Long.valueOf(request.getParameter("id"))));
    }

    private void ensureAuth(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("message", "You aren't entered!");
            throw new RedirectException("/index");
        }
    }

}
