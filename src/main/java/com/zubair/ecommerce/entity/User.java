package com.zubair.ecommerce.entity;

import com.zubair.ecommerce.auditing.ApplicationAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends ApplicationAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 30)
    private String firstName;

    @Column(length = 30)
    private String middleName;

    @Column(nullable = false, length = 30)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String hashedPassword;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_expired")
    private boolean isExpired;

    @Column(name = "is_locked")
    private boolean isLocked;

    @Column(name = "invalid_attempt_count")
    private Integer invalidAttemptCount = 0;

    @Column(name = "password_update_date")
    private LocalDateTime passwordUpdateDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();


}
