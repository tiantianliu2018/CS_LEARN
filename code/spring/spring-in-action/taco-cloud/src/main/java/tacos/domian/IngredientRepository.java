package tacos.domian;

import org.springframework.data.repository.CrudRepository;
import tacos.domian.entity.Ingredient;

/**
 * @author liutiantian
 * @create 2020-04-07 20:00
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
