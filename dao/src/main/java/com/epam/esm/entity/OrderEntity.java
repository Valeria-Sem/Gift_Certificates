package com.epam.esm.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "gift_certificates")
public class OrderEntity {
    private Long id;
    private GiftCertificateEntity gift;
    private UserEntity user;
    private double price;
    private Time time;

    public OrderEntity() {
    }

    public OrderEntity(Long id, GiftCertificateEntity gift,
                       UserEntity user, double price, Time time) {
        this.id = id;
        this.gift = gift;
        this.user = user;
        this.price = price;
        this.time = time;
    }

    public OrderEntity(GiftCertificateEntity gift,
                       UserEntity user, double price, Time time) {
        this.gift = gift;
        this.user = user;
        this.price = price;
        this.time = time;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "gift_id", referencedColumnName = "id", nullable = false)
    public GiftCertificateEntity getGift() {
        return gift;
    }

    public void setGift(GiftCertificateEntity gift) {
        this.gift = gift;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Double.compare(that.price, price) == 0 &&
                id.equals(that.id) &&
                gift.equals(that.gift) &&
                user.equals(that.user) &&
                time.equals(that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gift, user, price, time);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", gift=" + gift +
                ", user=" + user +
                ", price=" + price +
                ", time=" + time +
                '}';
    }
}
