package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {
    private final DataSource dataSource;

    public AuthorRepository() {
        dataSource = DataSource.getInstance();
    }


    @Override
    public List<AuthorModel> readAll() {
        return dataSource.getAuthors();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return dataSource.getAuthors().stream().filter(author -> author.getId() == id).findFirst();
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        List<AuthorModel> list = dataSource.getAuthors();
        list.sort(Comparator.comparing(AuthorModel::getId));
        if(!list.isEmpty())
            entity.setId(list.get(list.size() - 1).getId() + 1);
        else
            entity.setId(1L);
        list.add(entity);
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        AuthorModel authorModel = readById(entity.getId()).get();
        authorModel.setName(entity.getName());
        authorModel.setLastUpdateDate(entity.getLastUpdateDate());
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        AuthorModel authorModel = readById(id).get();
        return dataSource.getNews().remove(authorModel);
    }

    @Override
    public boolean existById(Long id) {
        return dataSource.getAuthors().stream().anyMatch(authorModel -> authorModel.getId() == id);
    }
}
