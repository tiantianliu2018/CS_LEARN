package tacos.domian;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.domian.entity.Order;
import tacos.domian.entity.User;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

/**
 * @author liutiantian
 * @create 2020-04-07 20:01
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByDeliveryZip(String deliveryZip);

    List<Order> readOrderByDeliveryZipAndPlaceAtBetween(
            String deliveryZip, Date startDate, Date endDate);

    List<Order> findByUserOrderByPlaceAtDesc(User user, Pageable pageable);
//    @Query("Order o where o.City='Shanghai'")
//    List<Order> readOrdersDeliveredInSeattle();
}
