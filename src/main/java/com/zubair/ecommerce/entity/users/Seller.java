package com.zubair.ecommerce.entity.users;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "userId")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Seller extends User{
    @Column(unique = true)
    private String gst;
    @Column(unique = true)
    private Long companyContact;
    private String companyName;

//    @OneToMany(mappedBy = "seller")
//    private Set<Product> productList;
}
