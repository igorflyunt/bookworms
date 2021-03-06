package com.softserve.bookworm.servlet.user;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.softserve.bookworm.service.AuthorService;
import com.softserve.bookworm.servlet.Jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/author")
@Singleton
public class AuthorDetailsServlet extends HttpServlet {
    @Inject
    private AuthorService authorService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int authorId = Integer.parseInt(request.getParameter("authorId"));
        authorService.findById(authorId).ifPresent(author -> request.setAttribute("author", author));
        request.getRequestDispatcher(Jsp.AUTHOR_VIEW).forward(request, response);
    }
}
