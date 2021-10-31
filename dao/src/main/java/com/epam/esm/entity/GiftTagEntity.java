package com.epam.esm.entity;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GiftTagEntity {
    private int id;
    private int giftId;
    private String tagName;

    public GiftTagEntity() {
    }

    public GiftTagEntity(int id, int giftId, String tagName) {
        this.id = id;
        this.giftId = giftId;
        this.tagName = tagName;
    }

    public GiftTagEntity(int giftId, String tagName) {
        this.giftId = giftId;
        this.tagName = tagName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGiftId() {
        return giftId;
    }

    public void setGiftId(int giftId) {
        this.giftId = giftId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftTagEntity that = (GiftTagEntity) o;
        return id == that.id && giftId == that.giftId && tagName.equals(that.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, giftId, tagName);
    }

    @Override
    public String toString() {
        return "GiftTagEntity{" +
                "id=" + id +
                ", giftId=" + giftId +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
