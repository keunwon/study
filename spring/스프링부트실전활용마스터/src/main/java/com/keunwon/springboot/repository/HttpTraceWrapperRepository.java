package com.keunwon.springboot.repository;

import com.keunwon.springboot.HttpTraceWrapper;
import org.springframework.data.repository.Repository;

import java.util.stream.Stream;

public interface HttpTraceWrapperRepository extends Repository<HttpTraceWrapper, String> {

    Stream<HttpTraceWrapper> findAll();

    void save(HttpTraceWrapper wrapper);
}
