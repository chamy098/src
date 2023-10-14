package com.src.core.interfaces;

public interface IRequestCounterService {
    void incrementRequestCount();

    long getRequestCount();
}
