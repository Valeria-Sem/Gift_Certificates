package com.epam.esm.entity;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GiftTagEntity {
    private int id;
    private int giftId;
    private int tagId;

    public GiftTagEntity() {
    }

    public GiftTagEntity(int id, int giftId, int tagId) {
        this.id = id;
        this.giftId = giftId;
        this.tagId = tagId;
    }

    public GiftTagEntity(int giftId, int tagId) {
        this.giftId = giftId;
        this.tagId = tagId;
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

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftTagEntity that = (GiftTagEntity) o;
        return id == that.id && giftId == that.giftId && tagId == that.tagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, giftId, tagId);
    }

    @Override
    public String toString() {
        return "GiftTagEntity{" +
                "id=" + id +
                ", giftId=" + giftId +
                ", tagId=" + tagId +
                '}';
    }
}
