package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();

    public void validateArticle(Article article) throws ValidationException {
        if (Strings.isNullOrEmpty(article.getTitle())) {
            throw new ValidationException("Title is required");
        }
        if (article.getTitle().length() > 100) {
            throw new ValidationException("Title can't be longer than 100 characters");
        }

        if (Strings.isNullOrEmpty(article.getText())) {
            throw new ValidationException("Text is required");
        }
        if (article.getText().length() > 500) {
            throw new ValidationException("Text can't be longer than 500 characters");
        }
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public List<ArticleView> findViews() {
        List<ArticleView> articleViews = new ArrayList<>();
        for (Article article : articleRepository.findAll()) {
            articleViews.add(new ArticleView(article, userRepository.find(article.getUserId())));
        }
        return articleViews;
    }

    public List<Article> findByUserId(Long userId) {
        return articleRepository.findByUserId(userId);
    }

    public Article find(Long id) {
        return articleRepository.find(id);
    }

    public void changeVisibility(Long articleId, boolean hide) {
        articleRepository.changeVisibility(articleId, hide);
    }

    public static class ArticleView {
        private Article article;
        private User user;

        public ArticleView(Article article, User user) {
            this.article = article;
            this.user = user;
        }

        public Article getArticle() {
            return article;
        }

        public void setArticle(Article article) {
            this.article = article;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }


}
