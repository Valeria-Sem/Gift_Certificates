package com.epam.esm.dto;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TagDTO {
    private Long id;
    private String name;

    public TagDTO() {

    }

    public TagDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagDTO(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        TagDTO tagBean = (TagDTO) o;
        return Objects.equals(id, tagBean.id) &&
                Objects.equals(name, tagBean.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "TagDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
