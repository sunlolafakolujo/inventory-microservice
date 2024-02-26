package com.inventory.appuserservice.address.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE address SET deleted= true WHERE id=?")
@Where(clause = "deleted = false")
public class Address {

    @Id
    private String id;
    private String fullAddress;
    private String city;
    private String landmark;

    @Column(name = "states")
    private String state;
    private String country;
}
