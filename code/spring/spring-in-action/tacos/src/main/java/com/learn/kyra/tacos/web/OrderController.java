package com.learn.kyra.tacos.web;

import com.learn.kyra.tacos.domain.OrderRepository;
import com.learn.kyra.tacos.domain.entity.Order;
import com.learn.kyra.tacos.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.awt.print.Pageable;

/**
 * @author liutiantian
 * @create 2020-04-06 19:42
 */
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
                               SessionStatus status,
                               @AuthenticationPrincipal User user){

        if (errors.hasErrors()){
            return "orderForm";
        }

        order.setUser(user);

        orderRepository.save(order);
        // 重置会话
        status.setComplete();

        log.info("Order submitted: " + order);
        return "redirect:/";
    }

    /**
     * 列出经过身份验证的用户之前的订单
     */
    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model){
        Pageable pageable = (Pageable) PageRequest.of(0, 10);
        model.addAttribute("orders",
                orderRepository.findByUserOrderByPlacedAtDesc(user));

        return "orderList";
    }
}
