package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
//import com.mjc.school.repository.factory.RepositoryFactory;
import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;

import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.mapper.CustomMapper;
import com.mjc.school.service.validator.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mjc.school.service.exceptions.ErrorCode.NEWS_ID_DOES_NOT_EXIST;
import static com.mjc.school.service.validator.Validator.getValidator;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
    @Qualifier("newsRepository")
    private final BaseRepository<NewsModel, Long> newsRepository;
    @Qualifier("newsValidator")
    private final Validator validator;
    private CustomMapper mapper = CustomMapper.INSTANCE;

    public NewsService(NewsRepository newsRepository, Validator newsValidator) {
        this.newsRepository = newsRepository;
        validator = newsValidator;
//        this.validator = getValidator();
//        this.newsRepository = RepositoryFactory.getInstance().getNewsRepository();

    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return mapper.newsModelListToDtoList(newsRepository.readAll());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        validator.validateNewsId(id);
        if (newsRepository.existById(id)) {
            NewsModel newsModel = newsRepository.readById(id).get();
            return mapper.newsModelToNewsDto(newsModel);
        } else{
            throw new NotFoundException(
                    String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), id));
            }

    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        validator.validateNewsDto(createRequest);
        NewsModel newsModel = mapper.newsDtoToNewsModel(createRequest);
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        newsModel.setCreateDate(date);
        newsModel.setLastUpdateDate(date);
        NewsModel news = newsRepository.create(newsModel);
        return mapper.newsModelToNewsDto(news);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        validator.validateNewsId(updateRequest.id());
        validator.validateNewsDto(updateRequest);
        if (newsRepository.existById(updateRequest.id())){
            NewsModel newsModel = mapper.newsDtoToNewsModel(updateRequest);
            LocalDateTime updateDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            newsModel.setLastUpdateDate(updateDate);
            NewsModel news = newsRepository.update(newsModel);
            return mapper.newsModelToNewsDto(news);
        }
        else{
            throw new NotFoundException(
                    String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), updateRequest.id()));
        }
    }

    @Override
    public boolean deleteById(Long id) {
        validator.validateAuthorId(id);
        if (newsRepository.existById(id))
            return newsRepository.deleteById(id);
        else
            throw new NotFoundException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), id));
    }
}
