package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
//import com.mjc.school.service.factory.ServiceFactory;
import com.mjc.school.service.implementation.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
@Controller
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {
    @Qualifier("newsService")
    private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
//        this.newsService = ServiceFactory.getInstance().getNewsService();
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsService.readAll();
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        return newsService.readById(id);
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        return newsService.create(createRequest);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        return newsService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return newsService.deleteById(id);
    }
}
