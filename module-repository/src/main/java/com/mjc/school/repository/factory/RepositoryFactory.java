package com.mjc.school.repository.factory;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.implementation.AuthorRepository;
import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;

public class RepositoryFactory {
    private static RepositoryFactory instance;
    private final BaseRepository<NewsModel, Long> newsRepository;
    private final BaseRepository<AuthorModel, Long> authorsRepository;

    public RepositoryFactory() {
        this.newsRepository = new NewsRepository();
        this.authorsRepository = new AuthorRepository();
    }

    public static RepositoryFactory getInstance(){
        if (instance == null)
            instance = new RepositoryFactory();
        return instance;
    }

    public BaseRepository<NewsModel, Long> getNewsRepository(){
        return newsRepository;
    }
    public BaseRepository<AuthorModel, Long> getAuthorsRepository(){
        return authorsRepository;
    }
}
