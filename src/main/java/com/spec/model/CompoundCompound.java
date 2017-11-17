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
@Table(name = "compound_compound")
@Getter
@Setter
@NoArgsConstructor
public class CompoundCompound implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;



    @Column(name = "child_percentage")
    private Double childPercentage;

    @Column(name = "mixedBowlPercentage")
    private Double mixedBowlPercentage;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Compound parent;

    @ManyToOne
    @JoinColumn(name = "child_id", referencedColumnName = "id")
    private Compound child;




    public CompoundCompound(Compound parent, Compound child, double childPercentage){

        this.parent = parent;
        this.child = child;
        this.childPercentage = childPercentage;

    }

}
