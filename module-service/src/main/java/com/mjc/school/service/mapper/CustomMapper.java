package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomMapper {

    CustomMapper INSTANCE = Mappers.getMapper(CustomMapper.class);
    List<NewsDtoResponse> newsModelListToDtoList(List<NewsModel> newsModelList);
    List<AuthorDtoResponse> authorModelListToDtoList(List<AuthorModel> authorModelList);


    NewsDtoResponse newsModelToNewsDto(NewsModel newsModel);

    @Mappings({
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdateDate", ignore = true)
    })
    NewsModel newsDtoToNewsModel(NewsDtoRequest newsDtoRequest);

    AuthorDtoResponse authorModelToAuthorDto(AuthorModel authorModel);
    @Mappings({
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdateDate", ignore = true)
    })
    AuthorModel authorDtoToAuthorModel(AuthorDtoRequest authorDtoRequest);

}
