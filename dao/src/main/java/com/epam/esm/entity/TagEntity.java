package com.epam.esm.entity;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "gift_certificate", schema = "gift_certificates")
public class TagEntity {
    private int id;
    private String name;
    private Set<GiftCertificateEntity> tagGifts;

    public TagEntity() {

    }

    public TagEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagEntity(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "giftTags")
    public Set<GiftCertificateEntity> getTagGifts() {
        return tagGifts;
    }

    public void setTagGifts(Set<GiftCertificateEntity> tagGifts) {
        this.tagGifts = tagGifts;
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