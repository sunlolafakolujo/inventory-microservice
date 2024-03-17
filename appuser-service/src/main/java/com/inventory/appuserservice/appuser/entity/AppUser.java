package com.inventory.appuserservice.appuser.entity;

import com.inventory.appuserservice.address.entity.Address;
import com.inventory.appuserservice.staticdata.UserType;
import com.inventory.appuserservice.userrole.entity.Role;
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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@SQLDelete(sql = "UPDATE app_user SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
public class AppUser {
    @Id
    @SequenceGenerator(name = "app_user_seq",
            sequenceName = "app_user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "app_user_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(unique = true, updatable = false
            ,nullable = false)
    private String username;

    @Column(unique = true, updatable = false,
            nullable = false)
    @Email(message = "Specify correct email address")
    private String email;

    @Column(unique = true, updatable = false,
            nullable = false)
    private String mobile;

    @Column(length = 60)
    private String password;

    @Transient
    private String confirmPassword;
    private boolean deleted = Boolean.FALSE;
    private boolean isEnabled = Boolean.FALSE;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "app_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(insertable = false)
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
}
