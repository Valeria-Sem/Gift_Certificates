package com.epam.esm.entity;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TagEntity {
    private int id;
    private String name;

    public TagEntity() {

    }

    public TagEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagEntity(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagEntity tagBean = (TagEntity) o;
        return id == tagBean.id && Objects.equals(name, tagBean.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "TagBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
