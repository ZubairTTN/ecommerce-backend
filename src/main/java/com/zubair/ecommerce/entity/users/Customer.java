package com.zubair.ecommerce.entity.users;

import com.zubair.ecommerce.entity.order.Cart;
import com.zubair.ecommerce.entity.order.Orders;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "userId")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Customer extends User{
    private Long contact;

    @OneToMany(mappedBy = "customer")
    private Set<Orders> orders;

    @OneToMany(mappedBy = "customer")
    private Set<Cart> carts;
}
