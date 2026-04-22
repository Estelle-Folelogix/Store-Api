package com.estelle.store.product.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;


@Component
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer proId;
    private String prodName;
    private BigDecimal price;
    private int quantity;
    private String brand;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private boolean available;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date releaseDate;

    private String imageName;
    private String imageType;

    @Lob
    @Column(name = "image_data", length = 1000000) // Défine max length
    @JdbcTypeCode(java.sql.Types.VARBINARY)
    private byte[] imageData;
}
