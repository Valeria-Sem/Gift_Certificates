package com.epam.esm.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class WalletDTO {
    private Long id;
    private double balance;

    public WalletDTO() {
    }

    public WalletDTO(Long id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public WalletDTO(double balance) {
        this.balance = balance;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletDTO walletDTO = (WalletDTO) o;
        return Objects.equals(id, walletDTO.id) &&
                Double.compare(walletDTO.balance, balance) == 0;
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