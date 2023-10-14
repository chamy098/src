package com.src.core.interfaces;

import com.src.datamodel.Actor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IActorService {
    CompletableFuture<List<Actor>> getAll();

    CompletableFuture<Page<Actor>> getActorsByPage(int size, int page);

    CompletableFuture<Actor> save(Actor actor);

    CompletableFuture<Actor> getById(Long id);

    CompletableFuture<Actor> update(Actor actor);

    CompletableFuture<Boolean> delete(Long id);
}
