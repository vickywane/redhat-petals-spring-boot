package redhat.engineering.ebikes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import redhat.engineering.ebikes.entities.Service_User;
import redhat.engineering.ebikes.services.BikeService;

import java.io.Console;

@Controller
public class BikeController {
    @Autowired
    BikeService bikeService;

    @GetMapping("/")
    public String index(Model model) {
        //  model.addAttribute("bikes", bikeService.getBikes());
        return "home";
    }

    // AUTHENTICATION
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String postLoginData(Model model) {
    // TODO: Add conditional to redirect user to home if they're a normal user

        return "redirect:/";
    }

    @PostMapping("/signup")
    public String submitSignup(Service_User userData, Model model) {
        model.addAttribute("service_user", new Service_User());
        bikeService.createUser(userData);

        return "redirect:/dashboard";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("service_user", new Service_User());

        return "signup";
    }

    // CONSOLE
    @GetMapping("/dashboard")
    public String dashboard(Service_User userData, Model model) {
        return "dashboard";
    }

    @GetMapping("/purchase")
    public String purchase(Service_User userData, Model model) {
        return "purchase";
    }

    @GetMapping("/create-bike")
    public String createBike(Service_User userData, Model model) {
        return "create-bike";
    }
}

