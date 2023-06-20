package ru.netology.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.model.Post;
import ru.netology.service.PostService;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping("/api/posts")
@RestController
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    //метод отправляет ответ со списком всех постов
    @GetMapping
    public List<Post> all() throws IOException {
        return service.all();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id, HttpServletResponse response) throws IOException {
        return service.getById(id);
    }

    @PostMapping
    public Post save(@RequestBody Post post) {
            return service.save(post);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id, HttpServletResponse response) {
        service.removeById(id);
    }
}
