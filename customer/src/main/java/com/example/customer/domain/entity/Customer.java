package com.example.customer.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "customers")
public class Customer {

    @Id
    private UUID id;

    private String name;

    private String number;

    private String address;

    public void setAddress(String address) {
        this.address = address;
    }

}
