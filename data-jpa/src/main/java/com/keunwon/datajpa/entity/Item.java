package com.keunwon.datajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@Entity
public class Item extends BaseEntity implements Persistable<String> {

    @Id
    private String id;

    @Override
    public boolean isNew() {
        return getCreateDate() == null;
    }
}
