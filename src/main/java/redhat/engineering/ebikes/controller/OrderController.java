package redhat.engineering.ebikes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import redhat.engineering.ebikes.services.BikeService;
import redhat.engineering.ebikes.services.OrderService;

@Controller
public class OrderController {
    @Autowired
    BikeService bikeService;

    @Autowired
    OrderService orderService;

    @GetMapping("/dashboard/orders")
    public String allOrders(Model model) {
        model.addAttribute("orders", orderService.retrieveOrders());

        return "/orders";
    }
}

