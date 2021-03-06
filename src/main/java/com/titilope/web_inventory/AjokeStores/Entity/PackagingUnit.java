package com.titilope.web_inventory.AjokeStores.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="packaging_units")
public class PackagingUnit extends BaseEntity {


    @Column(name = "unit_quantity")
    private int unitQuantity;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "unit_price", precision = 2)
    private BigDecimal unitPrice;


    @Override
    public String toString() {
        return "PackagingUnit{" +
                "id=" + id +
                ", unitQuantity=" + unitQuantity +
                ", stockQuantity=" + stockQuantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
