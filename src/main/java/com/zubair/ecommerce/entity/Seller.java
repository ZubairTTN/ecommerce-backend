package com.zubair.ecommerce.entity;

import com.zubair.ecommerce.auditing.ApplicationAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Seller extends ApplicationAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 15)
    private String gst;

    @Column(name = "company_contact", nullable = false, length = 10)
    private String companyContact;

    @Column(name = "company_name", nullable = false, length = 30)
    private String companyName;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "is_approved")
    private boolean isApproved;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
