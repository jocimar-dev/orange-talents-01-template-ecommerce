package com.zup.nossocartao.category;

import com.zup.nossocartao.validator.ChecksId;
import com.zup.nossocartao.validator.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.StringJoiner;

public class NewCategoryRequest {

    @NotBlank
    private String name;

    @Positive
    @ChecksId(domainClass = Category.class,fieldName = "id")
    private Long idMotherCategory;

    public void setName(String name) {
        this.name = name;
    }

    public void setIdCategoriaMae(Long idMotherCategory) {
        this.idMotherCategory = idMotherCategory;
    }

    public String getName() {
        return name;
    }

    public Long getIdMotherCategory() {
        return idMotherCategory;
    }

    public Category toCategory(EntityManager manager) {
        Category newCategory = new Category(name);
        if(idMotherCategory != null) {
            Category motherCategory = manager.find(Category.class,idMotherCategory);
            Assert.notNull(motherCategory, "Id inválido");

            newCategory.setMotherCategory(motherCategory);
        }
        return newCategory;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NewCategoryRequest.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("idMotherCategory=" + idMotherCategory)
                .toString();
    }
}
