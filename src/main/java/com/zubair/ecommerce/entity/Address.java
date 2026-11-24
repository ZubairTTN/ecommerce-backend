package com.zubair.ecommerce.entity;


import com.zubair.ecommerce.enums.AddressType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(
        name = "address",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {
                        "user_id",
                        "address_line",
                        "city",
                        "state",
                        "country",
                        "zip_code"
                }
        )
)
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 20)
    private String city;

    @Column(nullable = false, length = 20)
    private String state;

    @Column(nullable = false, length = 20)
    private String country;

    @Column(name = "address_line", nullable = false, length = 50)
    private String addressLine;

    @Column(name = "zip_code", nullable = false, length = 20)
    private String zipCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", nullable = false)
    private AddressType label;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



}
