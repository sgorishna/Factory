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
@Table(name="compound")
@Getter
@Setter
@NoArgsConstructor
public class Compound  implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;


    private String name;

    @OneToMany(mappedBy = "compound")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CompoundComponent> componentListByIdCompound;

    @OneToMany(mappedBy = "compound")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProductCompound> productListByIdCompound;

    @OneToMany(mappedBy = "child")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CompoundCompound> parentByChildId;

    @OneToMany(mappedBy = "parent")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CompoundCompound> childByParentId;



    public Compound(String name){

        this.name = name;
    }

}
