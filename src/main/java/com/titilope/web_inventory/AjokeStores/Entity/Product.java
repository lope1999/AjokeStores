package com.titilope.web_inventory.AjokeStores.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
public class Product extends BaseEntity{

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "packaging_unit_id")
    private PackagingUnit packagingUnit;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", packagingUnit=" + packagingUnit +
                '}';
    }
}
