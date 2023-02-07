package ru.bojark.repository;

import ru.bojark.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Stub
public class PostRepository {

  private AtomicInteger idCount = new AtomicInteger(0);
  private Map<Integer, Post> posts = new ConcurrentHashMap<>();

  public List<Post> all() {
    return Collections.emptyList();
  }

  public Optional<Post> getById(long id) {
    return Optional.empty();
  }

  public Post save(Post post) {
    return post;
  }

  public void removeById(long id) {
  }
}
