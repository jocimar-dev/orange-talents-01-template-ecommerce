package com.zup.nossocartao.category;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ManyToOne
    private Category motherCategory;

    @Deprecated
    public Category() {

    }

    public Category(@NotBlank String name) {
        this.name = name; }

    public void setMotherCategory(Category motherCategory) {
        this.motherCategory = motherCategory;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Category.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("motherCategory=" + motherCategory)
                .toString();
    }
}
