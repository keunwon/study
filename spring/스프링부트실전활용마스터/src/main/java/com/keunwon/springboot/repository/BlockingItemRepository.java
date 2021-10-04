package com.keunwon.springboot.repository;

import com.keunwon.springboot.Item;
import org.springframework.data.repository.CrudRepository;

public interface BlockingItemRepository extends CrudRepository<Item, String> {

}
