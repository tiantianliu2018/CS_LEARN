package tacosjdbc;

import tacos.domian.entity.Ingredient;

/**
 * @author liutiantian
 * @create 2020-04-07 14:56
 */
public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Ingredient findOne();

    Ingredient save(Ingredient ingredient);
}
