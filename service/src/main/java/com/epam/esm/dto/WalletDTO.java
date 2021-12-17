package com.epam.esm.dto;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class WalletDTO {
    private Long id;
    private double balance;
//    private UserDTO user;

    public WalletDTO() {
    }

    public WalletDTO(Long id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public WalletDTO(double balance) {
        this.balance = balance;
    }

//    public WalletDTO(int id, double balance, UserDTO user) {
//        this.id = id;
//        this.balance = balance;
////        this.user = user;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
//
//    public UserDTO getUser() {
//        return user;
//    }
//
//    public void setUser(UserDTO user) {
//        this.user = user;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletDTO walletDTO = (WalletDTO) o;
        return Objects.equals(id, walletDTO.id) &&
                Double.compare(walletDTO.balance, balance) == 0;
//                && Objects.equals(user, walletDTO.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance);
    }

    @Override
    public String toString() {
        return "WalletDTO{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}