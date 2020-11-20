package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @noinspection unused
 */
public class ArticlePage {
    private final ArticleService articleService = new ArticleService();


    private void action(HttpServletRequest request, Map<String, Object> view) {
        ensureAuth(request);
    }



    private void create(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        ensureAuth(request);
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setText(request.getParameter("text"));
        article.setUserId(((User) request.getSession().getAttribute("user")).getId());
        articleService.validateArticle(article);
        articleService.save(article);
        request.getSession().setAttribute("message", "You are successfully published!");
        throw new RedirectException("/index");
    }
    private void ensureAuth(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("message", "You aren't entered!");
            throw new RedirectException("/index");
        }
    }
}
