package com.src.application.controllers;

import com.src.core.interfaces.IActorService;
import com.src.datamodel.Actor;
import com.src.datamodel.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/actor")
@RequiredArgsConstructor
public class ActorController {

    private  final IActorService actorService;
    @GetMapping("getAllActors")
    @Cacheable("All-actors")
    public CompletableFuture<List<Actor>> getAll() {
        return this.actorService.getAll();
    }

    @GetMapping("getActorsByPage/{size}/{page}")
    public CompletableFuture<Page<Actor>> getActorsByPage(@PathVariable int size, @PathVariable int page) {
        return this.actorService.getActorsByPage(size, page);
    }

    @PostMapping("create")
    public CompletableFuture<Actor> create(@RequestBody Actor actor) {
        return this.actorService.save(actor);
    }

    @GetMapping("getById/{id}")
    public CompletableFuture<Actor> getById(@PathVariable Long id) {
        return this.actorService.getById(id);
    }

    @PutMapping("update")
    public CompletableFuture<Actor> update(@RequestBody Actor actor) {
        return this.actorService.update(actor);
    }
    @DeleteMapping("delete/{id}")
    public CompletableFuture<Boolean> delete(@PathVariable Long id) {
        return this.actorService.delete(id);
    }
}
