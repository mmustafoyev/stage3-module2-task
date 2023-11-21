package com.mjc.school.service.implementation;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.factory.RepositoryFactory;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.mapper.CustomMapper;
import com.mjc.school.service.validator.Validator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mjc.school.service.exceptions.ErrorCode.AUTHOR_ID_DOES_NOT_EXIST;

public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final BaseRepository<AuthorModel, Long> authorRepository;
    private final Validator validator;
    private final CustomMapper mapper = CustomMapper.INSTANCE;

    public AuthorService() {
        this.validator = Validator.getValidator();
        this.authorRepository = RepositoryFactory.getInstance().getAuthorsRepository();
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return mapper.authorModelListToDtoList(authorRepository.readAll());
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        validator.validateAuthorId(id);
        if (authorRepository.existById(id)){
            AuthorModel author = authorRepository.readById(id).get();
            return mapper.authorModelToAuthorDto(author);
        }else{
            throw new NotFoundException(
                    String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        validator.validateAuthorId(createRequest.id());
        validator.validateAuthorDto(createRequest);
        AuthorModel authorModel = mapper.authorDtoToAuthorModel(createRequest);
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        authorModel.setCreateDate(date);
        authorModel.setLastUpdateDate(date);
        AuthorModel author = authorRepository.create(authorModel);
        return mapper.authorModelToAuthorDto(author);

    }

    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        validator.validateAuthorId(updateRequest.id());
        validator.validateAuthorDto(updateRequest);
        if(authorRepository.existById(updateRequest.id())){
            AuthorModel authorModel = mapper.authorDtoToAuthorModel(updateRequest);
            LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            authorModel.setLastUpdateDate(date);
            AuthorModel author = authorRepository.update(authorModel);
            return mapper.authorModelToAuthorDto(author);
        }else
            throw new NotFoundException(
                    String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), updateRequest.id()));

    }

    @Override
    public boolean deleteById(Long id) {
        validator.validateAuthorId(id);
        if(authorRepository.existById(id))
            return authorRepository.deleteById(id);
        else
            throw new NotFoundException(
                    String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), id));
    }
}
