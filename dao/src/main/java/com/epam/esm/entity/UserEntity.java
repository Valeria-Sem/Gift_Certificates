package com.epam.esm.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "gift_certificates")
public class UserEntity {
    private Long id;
    private String login;
    private WalletEntity wallet;

    public UserEntity() {
    }

    public UserEntity(Long id, String login, WalletEntity wallet) {
        this.id = id;
        this.login = login;
        this.wallet = wallet;
    }

    public UserEntity(String login, WalletEntity wallet) {
        this.login = login;
        this.wallet = wallet;
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
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @OneToOne
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    public WalletEntity getWallet() {
        return wallet;
    }

    public void setWallet(WalletEntity wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(id, user.id) &&
                login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}