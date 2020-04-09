package tacos.domian;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.domian.entity.Ingredient;

/**
 * @author liutiantian
 * @create 2020-04-07 20:00
 */
@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
