package com.epam.esm.bean;

import java.util.Objects;

public class TagBean {
    private int id;
    private String name;

    public TagBean(){

    }

    public TagBean(int id, String name){
        this.id = id;
        this.name = name;
    }

    public TagBean(String name){
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
        TagBean tagBean = (TagBean) o;
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
