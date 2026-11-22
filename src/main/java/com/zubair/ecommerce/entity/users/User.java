package com.zubair.ecommerce.entity.users;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "update users set is_deleted = true where id =?")
@SQLRestriction("is_deleted = false")
public class User {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "id")
    private UUID userID;
    @Column(name = "email", unique = true)
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String password;

    private Boolean isDeleted;
    private Boolean isActive;
    private Boolean isExpired;
    private Boolean isLocked;
    private Integer invalidAttemptCount;
    private LocalDateTime passwordUpdateDate;
    private Date lastActivationTokenGenerationTime;
    private Date lastForgotPasswordTokenGenerationTime;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @LastModifiedDate
//    @Column(insertable = false)
    private LocalDateTime lastUpdated;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;

    @LastModifiedBy
//    @Column(insertable = false)
    private String updatedBy;


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            )
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Builder.Default
    private Set<Address> addresses = new HashSet<>();

//    Helper Methods
    public void addRole(Role role)
    {
        this.roles.add(role);
    }

    public void addAddress(Address address)
    {
        this.addresses.add(address);
    }

    @PrePersist
    protected void setDefaultValues()
    {
        if(isDeleted == null)
        {
            isDeleted = false;
        }
    }


}
