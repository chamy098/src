package com.src.application.controllers;

import com.src.core.interfaces.IRequestCounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/requestCounter")
@RequiredArgsConstructor
public class RequestCounterController{
    private final IRequestCounterService service;

    @GetMapping("getCount")
    public long getAllMovies() {
        return this.service.getRequestCount();
    }

}
