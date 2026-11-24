package com.zubair.ecommerce.entity.users;

import com.zubair.ecommerce.entity.product.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "userId") /* tells Hibernate that the Seller table uses a column named userId to reference the primary key of the User table. */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Seller extends User{
    @Column(unique = true)
    private String gst;
    @Column(unique = true)
    private Long companyContact;
    private String companyName;

    @OneToMany(mappedBy = "seller")
    private Set<Product> productList;
}
