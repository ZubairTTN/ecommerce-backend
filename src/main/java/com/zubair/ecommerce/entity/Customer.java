package com.zubair.ecommerce.entity;


import com.zubair.ecommerce.auditing.ApplicationAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends ApplicationAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(optional = false) /* relationship is mandatory. The entity cannot exist as a valid persisted entity without being associated with a User. */
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false, length = 10)
    private String contact;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
