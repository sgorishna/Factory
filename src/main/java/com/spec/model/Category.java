package com.spec.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Svetik on 25/11/2017.
 */
@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @Column(name = "id")
    private int id;
    private String name;

    @OneToMany(mappedBy = "category")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CategoryComponent> componentsByIdCategory;

    public Category(String name){

        this.name = name;
    }

}
