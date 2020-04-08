package tacosjdbc;

import tacos.domian.entity.Order;

/**
 * @author liutiantian
 * @create 2020-04-07 15:17
 */
public interface OrderRepository {
    Order save(Order order);
}
