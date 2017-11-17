package com.spec.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Svetik on 20/08/2017.
 */
@Entity
@Table(name = "compound_component")
@Getter
@Setter
@NoArgsConstructor
public class CompoundComponent implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "component_percentage")
    private Double componentPercentage;

    @Column(name = "mixedBowlPercentage")
    private Double mixedBowlPercentage;

    @ManyToOne
    @JoinColumn(name = "compound_id", referencedColumnName = "id")
    private Compound compound;

    @ManyToOne
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    private Component component;


    public CompoundComponent(Compound compound, Component component, double componentPercentage) {

        this.compound = compound;
        this.component = component;
        this.componentPercentage = componentPercentage;

    }

}
