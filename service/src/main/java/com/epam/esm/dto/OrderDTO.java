package com.epam.esm.dto;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class OrderDTO {
    private int id;
    private GiftCertificateDTO gift;
    private UserDTO user;
    private double price;
    private Date time;

    public OrderDTO() {
    }

    public OrderDTO(int id, GiftCertificateDTO gift, UserDTO user, double price, Date time) {
        this.id = id;
        this.gift = gift;
        this.user = user;
        this.price = price;
        this.time = time;
    }

    public OrderDTO(GiftCertificateDTO gift, UserDTO user, double price, Date time) {
        this.gift = gift;
        this.user = user;
        this.price = price;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GiftCertificateDTO getGift() {
        return gift;
    }

    public void setGift(GiftCertificateDTO gift) {
        this.gift = gift;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

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
        OrderDTO orderDTO = (OrderDTO) o;
        return id == orderDTO.id && Double.compare(orderDTO.price, price) == 0 && gift.equals(orderDTO.gift) && user.equals(orderDTO.user) && time.equals(orderDTO.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gift, user, price, time);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", gift=" + gift +
                ", user=" + user +
                ", price=" + price +
                ", time=" + time +
                '}';
    }
}
