package com.keunwon.springboot.repository;

import com.keunwon.springboot.HttpTraceWrapper;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SpringDataHttpTraceRepository implements HttpTraceRepository {
    private final HttpTraceWrapperRepository repository;

    public SpringDataHttpTraceRepository(HttpTraceWrapperRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HttpTrace> findAll() {
        return repository.findAll()
                .map(HttpTraceWrapper::getHttpTrace)
                .collect(toList());
    }

    @Override
    public void add(HttpTrace trace) {
        repository.save(new HttpTraceWrapper(trace));
    }
}
