package com.titilope.web_inventory.AjokeStores.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sale")
public class Sale extends BaseEntity{

    private Date dateSold;

    private double totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_id")
    private List<ProductSale> productSales;

    @OneToOne
    @JoinColumn(name = "sales_officer_id")
    private User salesOfficer;


    public void addToProductSales(ProductSale productSale)
    {
        if (productSales == null) {
            productSales = new ArrayList<>();
        }
        productSales.add(productSale);
    }

    @Override
    public String toString() {
        return "Sale{" +
                ", id=" + id +
                ", dateSold=" + dateSold +
                ", totalPrice=" + totalPrice +
                ", salesOfficer=" + salesOfficer +
                ", productSales=" + productSales +
                '}';
    }
}
