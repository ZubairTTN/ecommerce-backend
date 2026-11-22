package com.zubair.ecommerce.entity.product;


import com.zubair.ecommerce.entity.category.Category;
import com.zubair.ecommerce.entity.users.Seller;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "update product set is_deleted = true where id = ?")
@SQLRestriction("is_deleted=false")
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID productId;

    @ManyToOne
    @JoinColumn(name = "sellerUserId", referencedColumnName = "userId")
    private Seller seller;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    private Boolean isCancellable;

    private Boolean isReturnable;

    private String brand;

    private Boolean isActive;

    private Boolean isDeleted;


    @OneToMany(mappedBy = "product")
    private List<ProductVariation> productVariationList;

    @OneToMany(mappedBy = "product")
    private List<ProductReview> productReviewList;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastUpdated;
    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;
    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;

    @PrePersist
    public void prePersist() {
        this.isActive = false;
        this.isDeleted = false;
    }
}
