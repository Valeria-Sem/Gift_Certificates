package com.epam.esm.dto;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDTO {
    private int id;
    private String login;
    private WalletDTO wallet;

    public UserDTO() {
    }

    public UserDTO(int id, String login, WalletDTO wallet) {
        this.id = id;
        this.login = login;
        this.wallet = wallet;
    }

    public UserDTO(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public UserDTO(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public WalletDTO getWallet() {
        return wallet;
    }

    public void setWallet(WalletDTO wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id && login.equals(userDTO.login) && wallet.equals(userDTO.wallet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, wallet);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}