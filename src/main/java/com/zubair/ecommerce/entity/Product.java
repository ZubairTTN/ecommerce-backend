package com.zubair.ecommerce.entity;

import com.zubair.ecommerce.auditing.ApplicationAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends ApplicationAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "description", length = 100)
    private String desc;

    @Column(name = "is_cancellable")
    private Boolean isCancellable;

    @Column(name = "is_returnable")
    private Boolean isReturnable;

    @Column(name = "is_active")
    private Boolean isActive = false;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    private String brand;

    @ManyToOne
    @JoinColumn(name = "seller_user_id", nullable = false)
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<ProductVariations> productVariationList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductReview> productReviews = new ArrayList<>();
}
