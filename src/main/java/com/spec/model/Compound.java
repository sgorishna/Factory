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

    private String code;

    @OneToMany(mappedBy = "compound")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CompoundComponent> componentListByIdCompound;

    @Override
    public boolean equals(Object o) {

        boolean res =true;
        if (this == o) return res;
        if (o == null || getClass() != o.getClass())
            res= false;

        Compound compound = (Compound) o;

        if (getId() != compound.getId())
            res= false;
        if (!getName().equals(compound.getName()))
            res= false;
//        return (getCode() != null) && getCode().equals(compound.getCode()) || getCode() == null && (compound.getCode() == null);
return res;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        return result;
    }

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
