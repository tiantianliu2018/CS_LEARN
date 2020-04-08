package tacosjdbc;

import tacos.domian.entity.Taco;

/**
 * @author liutiantian
 * @create 2020-04-07 15:17
 */
public interface TacoRepository {
    Taco save(Taco taco);
}
