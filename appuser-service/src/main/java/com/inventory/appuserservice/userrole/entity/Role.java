package com.inventory.appuserservice.userrole.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "roles")
@SQLDelete(sql = "UPDATE roles SET deleted= true WHERE id=?")
@Where(clause = "deleted=false")
public class Role {
    @Id
    private String id;
    @Column(unique = true)
    private String roleName;

    private boolean deleted=Boolean.FALSE;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(insertable = false)
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
}
