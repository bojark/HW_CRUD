package ru.bojark.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import ru.bojark.model.Post;
import ru.bojark.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

@Controller
public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private static final String POST_DELETED = "Post deleted.";
    private final PostService service;
    private final Gson GSON = new Gson();

    public PostController(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.all();
        response.getWriter().print(GSON.toJson(data));
    }

    public void getById(long id, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.getById(id);
        response.getWriter().print(GSON.toJson(data));
    }

    public void save(Reader body, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var post = GSON.fromJson(body, Post.class);
        final var data = service.save(post);
        response.getWriter().print(GSON.toJson(data));
    }

    public void removeById(long id, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        service.removeById(id);
        response.getWriter().print(GSON.toJson(new Post(id, POST_DELETED)));
    }

}
