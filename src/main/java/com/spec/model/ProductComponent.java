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
@Table(name = "product_component")
@Getter
@Setter
@NoArgsConstructor
public class ProductComponent implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;



    @Column(name = "component_percentage")
    private Double componentPercentage;

    @Column(name = "mixedBowlPercentage")
    private Double mixedBowlPercentage;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    private Component
            component;




    public ProductComponent(Product product, Component component, double componentPercentage){

        this.product = product;
        this.component = component;
        this.componentPercentage = componentPercentage;

    }
}
