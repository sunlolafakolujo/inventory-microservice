package com.inventory.appuserservice.image.entity;

import jakarta.persistence.*;
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
@SQLDelete(sql = "UPDATE picture SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
public class Picture implements Serializable {

    public Picture(String name, String imageType, byte[] picByte) {
        this.name = name;
        this.imageType = imageType;
        this.picByte = picByte;
    }

    @Id
    @SequenceGenerator(name = "employee_seq",
            sequenceName = "employee_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "employee_seq")
    private Long id;

    private String name;
    private String imageType;
    private boolean deleted=Boolean.FALSE;

    private byte[] picByte;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    @Column(insertable = false)
    private Date updatedAt;
}
