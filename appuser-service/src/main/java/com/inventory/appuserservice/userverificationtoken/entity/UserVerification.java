package com.inventory.appuserservice.userverificationtoken.entity;


import com.inventory.appuserservice.appuser.entity.AppUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE user_verification SET deleted= true WHERE id=?")
@Where(clause = "deleted = false")
public class UserVerification implements Serializable {
    private static final Integer EXPIRATION_TIME = 60;
    @Id
    @SequenceGenerator(name = "user_verification_seq",
            sequenceName = "user_verification_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_verification_seq")
    private Long id;
    private String token;
    private Date expectedExpirationTime;
    private boolean deleted = Boolean.FALSE;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AppUser appUser;

    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    @Column(insertable = false)
    private Date updatedAt;

    public UserVerification(String token, AppUser appUser) {
        super();
        this.token = token;
        this.appUser = appUser;
        this.expectedExpirationTime = calculateExpirationTime(EXPIRATION_TIME);
    }

    public UserVerification(String token) {
        super();
        this.token = token;
        this.expectedExpirationTime = calculateExpirationTime(EXPIRATION_TIME);
    }

    private Date calculateExpirationTime(Integer expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expirationTime);
        return new Date(calendar.getTime().getTime());
    }
}
