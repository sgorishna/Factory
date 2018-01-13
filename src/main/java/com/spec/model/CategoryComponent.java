package com.spec.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Svetik on 25/11/2017.
 */
@Entity
@Table(name = "category_component")
@Getter
@Setter
@NoArgsConstructor
public class CategoryComponent {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    private Component component;


}
