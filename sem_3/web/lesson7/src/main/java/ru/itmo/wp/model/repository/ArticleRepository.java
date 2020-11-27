package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;

import java.util.List;

public interface ArticleRepository {
    void save(Article article);

    List<Article> findAll();
    Article find(Long id);
    List<Article> findByUserId(Long userId);
    void changeVisibility(Long articleId, boolean hide);
}
