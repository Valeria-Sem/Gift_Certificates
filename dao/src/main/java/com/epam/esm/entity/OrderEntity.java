package com.epam.esm.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "order", schema = "gift_certificates")
public class OrderEntity {
    private int id;
    private GiftCertificateEntity giftIdByGiftCertificate;
    private UserEntity userIdByUserEntity;
    private double price;
    private Date time;

    public OrderEntity() {
    }

    public OrderEntity(int id, GiftCertificateEntity giftIdByGiftCertificate, UserEntity userIdByUserEntity, double price, Date time) {
        this.id = id;
        this.giftIdByGiftCertificate = giftIdByGiftCertificate;
        this.userIdByUserEntity = userIdByUserEntity;
        this.price = price;
        this.time = time;
    }

    public OrderEntity(GiftCertificateEntity giftIdByGiftCertificate, UserEntity userIdByUserEntity, double price, Date time) {
        this.giftIdByGiftCertificate = giftIdByGiftCertificate;
        this.userIdByUserEntity = userIdByUserEntity;
        this.price = price;
        this.time = time;
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

    @ManyToOne
    @JoinColumn(name = "gift_id", referencedColumnName = "id", nullable = false)
    public GiftCertificateEntity getGiftIdByGiftCertificate() {
        return giftIdByGiftCertificate;
    }

    public void setGiftIdByGiftCertificate(GiftCertificateEntity giftIdByGiftCertificate) {
        this.giftIdByGiftCertificate = giftIdByGiftCertificate;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUserIdByUserEntity() {
        return userIdByUserEntity;
    }

    public void setUserIdByUserEntity(UserEntity userIdByUserEntity) {
        this.userIdByUserEntity = userIdByUserEntity;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id == that.id && Double.compare(that.price, price) == 0 &&
                giftIdByGiftCertificate.equals(that.giftIdByGiftCertificate) &&
                userIdByUserEntity.equals(that.userIdByUserEntity) &&
                time.equals(that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, giftIdByGiftCertificate, userIdByUserEntity, price, time);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", giftIdByGiftCertificate=" + giftIdByGiftCertificate +
                ", userIdByUserEntity=" + userIdByUserEntity +
                ", price=" + price +
                ", time=" + time +
                '}';
    }
}
