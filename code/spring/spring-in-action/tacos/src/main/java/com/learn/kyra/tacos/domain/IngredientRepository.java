package com.learn.kyra.tacos.domain;

import com.learn.kyra.tacos.domain.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * @author liutiantian
 * @create 2020-04-09 16:44
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
