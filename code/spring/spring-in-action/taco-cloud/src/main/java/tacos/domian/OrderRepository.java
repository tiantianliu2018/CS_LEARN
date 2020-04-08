package tacos.domian;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tacos.domian.entity.Order;

import java.util.Date;
import java.util.List;

/**
 * @author liutiantian
 * @create 2020-04-07 20:01
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByDeliveryZip(String deliveryZip);

    List<Order> readOrderByDeliveryZipAndPlaceAtBetween(
            String deliveryZip, Date startDate, Date endDate);

    @Query("Order o where o.City='Shanghai'")
    List<Order> readOrdersDeliveredInSeattle();
}
