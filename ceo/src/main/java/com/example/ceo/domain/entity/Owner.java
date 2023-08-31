package com.example.ceo.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "owners")
public class Owner {

    @Id
    private UUID id;

    private String name;

    private String number;

    @OneToMany(mappedBy = "owner")
    private List<Store> stores;

}
