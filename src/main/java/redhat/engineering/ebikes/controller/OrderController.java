package redhat.engineering.ebikes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import redhat.engineering.ebikes.services.BikeService;
import redhat.engineering.ebikes.services.OrderService;

@Controller
public class OrderController {
    @Autowired
    BikeService bikeService;

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public String allOrders(Model model) {
        model.addAttribute("orders", orderService.retrieveOrders());

        return "/orders";
    }

    @GetMapping("/order")
    public String order(@RequestParam Long bikeId, Model model) {
        if (bikeService.retrieveABike(bikeId).isPresent()) {
            model.addAttribute("bike", bikeService.retrieveABike(bikeId));
            return "purchase";
        }

        return "redirect:/dashboard";
    }

    @PostMapping("/order")
    public String placeOrder(@RequestParam Long bikeId, Model model) {
        if (bikeService.retrieveABike(bikeId).isPresent()) {
            model.addAttribute("bike", bikeService.retrieveABike(bikeId));
            return "purchase";
        }

        return "redirect:/dashboard";
    }
}

