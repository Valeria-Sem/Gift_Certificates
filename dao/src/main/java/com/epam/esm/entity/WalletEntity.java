package com.epam.esm.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "wallet", schema = "gift_certificates")
public class WalletEntity {
    private Long id;
    private double balance;
    private UserEntity user;

    public WalletEntity() {
    }

    public WalletEntity(Long id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public WalletEntity(double balance) {
        this.balance = balance;
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

    @Basic
    @Column(name = "balance")
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @OneToOne(mappedBy = "wallet")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletEntity that = (WalletEntity) o;
        return Objects.equals(id, that.id) &&
                Double.compare(that.balance, balance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance);
    }

    @Override
    public String toString() {
        return "WalletEntity{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}