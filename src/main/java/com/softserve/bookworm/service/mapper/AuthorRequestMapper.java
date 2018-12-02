package com.softserve.bookworm.service.mapper;

import com.softserve.bookworm.model.Author;
import com.softserve.bookworm.servlet.util.DateUtil;

import javax.servlet.http.HttpServletRequest;

public class AuthorRequestMapper extends AbstractRequestMapper<Author> implements RequestMapper<Author> {

    protected AuthorRequestMapper() {
        super(new Author());
    }

    @Override
    public Author mapToEntity(HttpServletRequest request) {
        entity.setFirstName(request.getParameter("firstName"));
        entity.setLastName(request.getParameter("lastName"));
        entity.setBiography(request.getParameter("biography"));
        entity.setBirthDate(DateUtil.getDate(request.getParameter("birthDate")));
        return entity;
    }
}
