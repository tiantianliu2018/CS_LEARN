package tacos.domian;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.domian.entity.Taco;

/**
 * @author liutiantian
 * @create 2020-04-07 20:01
 */
@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {
}
