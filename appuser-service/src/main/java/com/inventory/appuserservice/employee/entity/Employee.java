package com.inventory.appuserservice.employee.entity;

import com.inventory.appuserservice.appuser.entity.AppUser;
import com.inventory.appuserservice.image.entity.Picture;
import com.inventory.appuserservice.nextofkin.entity.NextOfKin;
import com.inventory.appuserservice.staticdata.EmploymentType;
import com.inventory.appuserservice.staticdata.Gender;
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
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@SQLDelete(sql = "UPDATE employee SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
public class Employee implements Serializable {
    @Id
    @SequenceGenerator(name = "employee_seq",
            sequenceName = "employee_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "employee_seq")
    private Long id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String otherName;
    private LocalDate dateOfBirth;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dateOfEmployment;
    private LocalDate retirementDate;
    private String stateOfOrigin;
    private String nationality;
    private boolean deleted=Boolean.FALSE;

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private NextOfKin nextOfKin;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AppUser appUser;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Picture picture;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(insertable = false)
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
}
