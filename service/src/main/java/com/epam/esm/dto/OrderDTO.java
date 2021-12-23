package com.epam.esm.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Component
public class OrderDTO extends RepresentationModel<OrderDTO> {
    private Long id;
    private GiftCertificateDTO gift;
    private UserDTO user;
    private double price;
    private Time time;

    public OrderDTO() {
    }

    public OrderDTO(Long id, GiftCertificateDTO gift, UserDTO user, double price, Time time) {
        this.id = id;
        this.gift = gift;
        this.user = user;
        this.price = price;
        this.time = time;
    }

    public OrderDTO(GiftCertificateDTO gift, UserDTO user, double price, Time time) {
        this.gift = gift;
        this.user = user;
        this.price = price;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        OrderDTO orderDTO = (OrderDTO) o;
        return Double.compare(orderDTO.price, price) == 0 &&
                time == orderDTO.time &&
                id.equals(orderDTO.id) &&
                gift.equals(orderDTO.gift) &&
                user.equals(orderDTO.user);
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
