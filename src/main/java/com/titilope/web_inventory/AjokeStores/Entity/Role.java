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
public class Role extends BaseEntity{

    @Column(unique = true)
    private String name;

}

