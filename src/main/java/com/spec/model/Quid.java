package com.spec.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Svetik on 14/01/2018.
 */
@Entity
@Table(name = "quid")
@Getter
@Setter
@NoArgsConstructor
public class Quid {

    public Quid(int product_id, String quidded, String declared, String allergen, String function){

        this.product_id = product_id;
        this.quidded = quidded;
        this.declared = declared;
        this.allergen = allergen;
        this.function = function;

    }

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "quidded")
    private String quidded;

    @Column(name = "declared")
    private String declared;

    @Column(name = "allergen")
    private String allergen;

    @Column(name = "function")
    private String function;

    @Column(name = "declaration")
    private String declaration;

    @Column(name = "declaration_editable")
    private String declarationEditable;

    @Column(name = "product_id")
    private int product_id;







}
