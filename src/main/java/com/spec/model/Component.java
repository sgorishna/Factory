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
@Table(name = "component")
@Getter
@Setter
@NoArgsConstructor
public class Component  implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "component")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CompoundComponent> compoundsListByIdComponent;

    @OneToMany(mappedBy = "component")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProductComponent> productListByIdComponent;



    public Component(String name){

        this.name = name;
    }



}
