package com.zubair.ecommerce.entity.product;


import com.zubair.ecommerce.entity.order.Cart;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ProductVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID productVariationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    private Integer quantityAvailable;
    private BigDecimal price;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Map<String, Object> metadata;

    private String primaryImageName;
    private boolean isActive;

    @OneToMany(mappedBy = "productVariation")
    private List<Cart> cartItems;
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
        this.isActive = true;
    }
}
