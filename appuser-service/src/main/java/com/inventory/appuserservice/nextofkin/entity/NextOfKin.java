package com.inventory.appuserservice.nextofkin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventory.appuserservice.address.entity.Address;
import com.inventory.appuserservice.employee.entity.Employee;
import com.inventory.appuserservice.staticdata.RelationshipWithNextOfKin;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@SQLDelete(sql = "UPDATE next_of_kin SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
public class NextOfKin implements Serializable {
    @Id
    private String id;
    private String name;

    @Email
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String mobile;

    @Enumerated(EnumType.STRING)
    private RelationshipWithNextOfKin relationshipWithNextOfKin;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(insertable = false)
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
}
