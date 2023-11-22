package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    @Qualifier("dataSource")
    private final DataSource dataSource;

    public NewsRepository() {
        dataSource = DataSource.getInstance();
    }

    @Override
    public List<NewsModel> readAll() {

        return dataSource.getNews();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return dataSource.getNews().stream().filter(news -> news.getId() == id).findFirst();
    }

    @Override
    public NewsModel create(NewsModel entity) {
        List<NewsModel> list = dataSource.getNews();
        list.sort(Comparator.comparing(NewsModel::getId));
        if(!list.isEmpty())
            entity.setId(list.get(list.size() - 1).getId() + 1);
        else
            entity.setId(1L);
        list.add(entity);
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        NewsModel newsModel = readById(entity.getId()).get();
        newsModel.setTitle(entity.getTitle());
        newsModel.setContent(entity.getContent());
        newsModel.setAuthorId(entity.getAuthorId());
        newsModel.setLastUpdateDate(entity.getLastUpdateDate());
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        NewsModel newsModel = readById(id).get();
        return dataSource.getNews().remove(newsModel);
    }

    @Override
    public boolean existById(Long id) {
        return dataSource.getNews().stream().anyMatch(news -> news.getId() == id);
    }
}
