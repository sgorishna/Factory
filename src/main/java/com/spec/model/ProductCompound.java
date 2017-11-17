package com.spec.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Svetik on 26/08/2017.
 */
@Entity
@Table(name = "product_compound")
@Getter
@Setter
@NoArgsConstructor
public class ProductCompound implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;



    @Column(name = "compound_percentage")
    private Double compoundPercentage;

    @Column(name = "mixedBowlPercentage")
    private Double mixedBowlPercentage;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "compound_id", referencedColumnName = "id")
    private Compound compound;



    public ProductCompound(Product product, Compound compound, double compoundPercentage){

        this.product = product;
        this.compound = compound;
        this.compoundPercentage = compoundPercentage;

    }
}
