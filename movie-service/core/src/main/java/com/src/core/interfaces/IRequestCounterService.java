package com.src.core.interfaces;

import com.src.datamodel.Movie;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IRequestCounterService {
    void incrementRequestCount();

    long getRequestCount();
}
