package com.zubair.ecommerce.entity;


import com.zubair.ecommerce.enums.AddressType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/* Here, we are specifying a unique constraint to ensure that duplicate
   values cannot be stored in a particular column or combination of columns.
   In this case, we are applying a unique constraint to a combination of
   six columns, meaning that no two records can have identical values
   across all six columns. Hence, it is called a Composite Unique Constraint. */

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
