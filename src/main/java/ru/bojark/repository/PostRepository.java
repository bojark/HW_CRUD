package ru.bojark.repository;

import org.springframework.stereotype.Repository;
import ru.bojark.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class PostRepository {

    private static final String EMPTY_POST_TEXT = "There are no posts on the server right now.";
    private final AtomicLong idCount = new AtomicLong(1);
    private final Map<Long, Post> posts = new ConcurrentHashMap<>();

    public PostRepository(){
        posts.put(0L, new Post(0, EMPTY_POST_TEXT));
    }

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.of(posts.get(id));
    }

    public Post save(Post post) {
        long id = post.getId();

        if (id == 0) {
            newPost(post);
        }

        if (id != 0) {
            if (posts.get(id) == null) {
                newPost(post);
            } else {
                posts.put(id, post);
            }
        }

        return post;
    }

    public void removeById(long id) {
        if(posts.get(id) != null && id != 0){
            posts.remove(id);
        }
    }

    private void newPost(Post post) {
        long currentId = idCount.getAndAdd(1);
        post.setId(currentId);
        posts.put(currentId, post);
    }

}
