package com.spec.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Svetik on 15/08/2017.
 */
@Entity
@Table(name="product")
@Getter
@Setter
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;


    private String name;

    private String code;

    private String legalName;

    @OneToMany(mappedBy = "product")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProductCompound> compoundsListByIdProduct;

    @OneToMany(mappedBy = "product")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProductComponent> componentsListByIdProduct;

    public Product(String name){

        this.name = name;
    }

}
