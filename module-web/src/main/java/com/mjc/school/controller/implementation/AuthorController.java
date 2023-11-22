package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
//import com.mjc.school.service.factory.ServiceFactory;
import com.mjc.school.service.implementation.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
@Component
@Controller
public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {
    @Qualifier("authorService")
    private final BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
//        this.authorService = ServiceFactory.getInstance().getAuthorService();
    }


    @Override
    public List<AuthorDtoResponse> readAll() {
        return authorService.readAll();
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        return authorService.readById(id);
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        return authorService.create(createRequest);
    }

    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        return authorService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return authorService.deleteById(id);
    }
}
