package com.src.service;

import com.src.core.exceptions.NotFoundException;
import com.src.core.interfaces.IActorService;
import com.src.datamodel.Actor;
import com.src.persistence.ActorRepository;
import com.src.persistence.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ActorService implements IActorService {

    private final ActorRepository actorRepository;

    private final MovieRepository movieRepository;

    @Override
    public CompletableFuture<List<Actor>> getAll() {
        return CompletableFuture.supplyAsync(this.actorRepository::findAll);
    }

    @Override
    public CompletableFuture<Page<Actor>> getActorsByPage(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Actor> actors = this.actorRepository.findAll(pageable);
        return CompletableFuture.supplyAsync(() ->  actors);
    }

    @Override
    public CompletableFuture<Actor> save(Actor actor) {
        //Save movies if any
        if(actor.getMovies() != null && !actor.getMovies().isEmpty()){
            this.movieRepository.saveAll(actor.getMovies());
        }
        return CompletableFuture.supplyAsync(() -> this.actorRepository.save(actor));
    }

    @Override
    public CompletableFuture<Actor> getById(Long id) {
        Actor existingActor = this.actorRepository.findById(id).orElse(null);
        if(existingActor == null) {
            throw new NotFoundException("Actor not found");
        }
        return CompletableFuture.supplyAsync(() -> existingActor);
    }

    @Override
    public CompletableFuture<Actor> update(Actor actor) {
        //Check if actor exists
        getById(actor.getId());
        //Update movies if any
        if(actor.getMovies() != null && !actor.getMovies().isEmpty()){
            this.movieRepository.saveAll(actor.getMovies());
        }
        return CompletableFuture.supplyAsync(() -> this.actorRepository.save(actor));
    }

    @Override
    public CompletableFuture<Boolean> delete(Long id) {
        //Check if actor exists
        getById(id);
        return CompletableFuture.supplyAsync(() -> {
            this.actorRepository.deleteById(id);
            return true;
        });
    }
}
