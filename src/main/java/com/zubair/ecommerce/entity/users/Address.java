package com.zubair.ecommerce.entity.users;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@SQLDelete(sql = "update address set is_deleted = true where id=?")
@SQLRestriction("is_deleted = false")
public class Address {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "id")
    private UUID addressId;

    private String city;
    private String state;
    private String country;
    private String addressLine;
    private String zipCode;
    private String label;
    private Boolean isDeleted;

    @PrePersist
    protected void setDefaultValues()
    {
        if(isDeleted == null)
        {
            isDeleted = false;
        }
    }
}
