package com.learn.kyra.tacos.web;

import com.learn.kyra.tacos.domain.OrderRepository;
import com.learn.kyra.tacos.domain.entity.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * @author liutiantian
 * @create 2020-04-09 17:14
 */
@RestController
@RequestMapping(path = "/orders", produces = "application/json")
@CrossOrigin(origins = "*")
public class TacoOrderController {
    private OrderRepository orderRepository;

    public TacoOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping(consumes = "application/json")
    public Iterable<Order> allOrders(){
        return orderRepository.findAll();
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order postOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }
    /**
     * 完全替换，如果字段缺失，直接替换为 null
     */
    @PutMapping("/{orderId}")
    public Order putOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }

    /**
     * 部分替换
     */
    @PatchMapping(value = "/{orderId}", consumes = "application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId,
                            @RequestBody Order patch){
        Order order = orderRepository.findById(orderId).get();

        if (patch.getDeliveryName() != null){
            order.setDeliveryName(patch.getDeliveryName());
        }

        if (patch.getDeliveryStreet() != null){
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }

        if (patch.getDeliveryCity() != null){
            order.setDeliveryCity(patch.getDeliveryCity());
        }

        if (patch.getDeliveryState() != null){
            order.setDeliveryState(patch.getDeliveryState());
        }

        if (patch.getDeliveryZip() != null){
            order.setDeliveryZip(patch.getDeliveryZip());
        }

        if (patch.getCcNumber() != null){
            order.setCcNumber(patch.getCcNumber());
        }

        if (patch.getCcExpiration() != null){
            order.setCcExpiration(patch.getCcExpiration());
        }

        if (patch.getCcCVV() != null){
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId){
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
}
