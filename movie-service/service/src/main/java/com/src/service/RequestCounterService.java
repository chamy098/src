package com.src.service;

import com.src.core.interfaces.IRequestCounterService;
import org.springframework.stereotype.Service;

@Service
public class RequestCounterService  implements IRequestCounterService {

    private long requestCount = 0;

    @Override
    public void incrementRequestCount() {
        requestCount += 1;
        System.out.println("Request count: " + requestCount);
    }

    @Override
    public long getRequestCount() {
        return requestCount;
    }
}
