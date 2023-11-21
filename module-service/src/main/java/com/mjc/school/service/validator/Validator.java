package com.mjc.school.service.validator;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exceptions.ValidatorException;

import static com.mjc.school.service.exceptions.ErrorCode.*;
public class Validator {
    private static final String AUTHOR_ID = "Author id";
    private static final String NEWS_ID = "News id";
    private static final String NEWS_TITLE = "News title";
    private static final Integer NEWS_TITLE_MIN_LENGTH = 5;
    private static final Integer NEWS_TITLE_MAX_LENGTH = 30;
    private static final String NEWS_CONTENT = "News content";
    private static final Integer NEWS_CONTENT_MIN_LENGTH = 5;
    private static final Integer NEWS_CONTENT_MAX_LENGTH = 255;
    private static final String AUTHOR_NAME = "Author name";
    private static final Integer AUTHOR_NAME_MIN_LENGTH = 3;
    private static final Integer AUTHOR_NAME_MAX_LENGTH = 15;

    private static Validator validator;

    public static Validator getValidator(){
        if(validator == null)
            validator = new Validator();
        return validator;
    }

    public void validateNewsId(Long newsId) {
        validateNumber(newsId, NEWS_ID);
    }

    public void validateAuthorId(Long authorId) {
        validateNumber(authorId, AUTHOR_ID);
        if (authorId > 20) {
            throw new ValidatorException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), authorId));
        }
    }
    public void validateNewsDto(NewsDtoRequest dtoRequest) {
        validateString(dtoRequest.title(), NEWS_TITLE, NEWS_TITLE_MIN_LENGTH, NEWS_TITLE_MAX_LENGTH);
        validateString(
                dtoRequest.content(), NEWS_CONTENT, NEWS_CONTENT_MIN_LENGTH, NEWS_CONTENT_MAX_LENGTH);
        validateAuthorId(dtoRequest.authorId());
    }
    public void validateAuthorDto(AuthorDtoRequest dtoRequest) {
        validateString(dtoRequest.name(), AUTHOR_NAME, AUTHOR_NAME_MIN_LENGTH, AUTHOR_NAME_MAX_LENGTH);

    }
    private void validateNumber(Long id, String parameter) {
        if (id == null || id < 1) {
            throw new ValidatorException(
                    String.format(VALIDATE_NEGATIVE_OR_NULL_NUMBER.getMessage(), parameter, parameter, id));
        }
    }

    private void validateString(String value, String parameter, int minLength, int maxLength) {
        if (value == null) {
            throw new ValidatorException(
                    String.format(VALIDATE_NULL_STRING.getMessage(), parameter, parameter));
        }
        if (value.trim().length() < minLength || value.trim().length() > maxLength) {
            throw new ValidatorException(
                    String.format(
                            VALIDATE_STRING_LENGTH.getMessage(),
                            parameter,
                            minLength,
                            maxLength,
                            parameter,
                            value));
        }
    }
}
