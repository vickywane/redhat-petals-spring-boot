package redhat.engineering.ebikes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import redhat.engineering.ebikes.entities.Bike;
import redhat.engineering.ebikes.entities.Service_User;
import redhat.engineering.ebikes.services.BikeService;

@Controller
public class BikeController {
    @Autowired
    BikeService bikeService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("bikes", bikeService.retrieveBikes());

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
    public String dashboard(Model model) {
        model.addAttribute("bikes", bikeService.retrieveBikes());

        return "dashboard";
    }

    @GetMapping("/users")
    public String customers(Model model) {
        model.addAttribute("service_users", bikeService.retrieveUsers("CUSTOMERS"));

        return "users";
    }

    @GetMapping("/purchase")
    public String purchase(@RequestParam Long bikeId, Service_User spring , Model model) {
        if (bikeService.retrieveABike(bikeId).isPresent()) {
            model.addAttribute("bike", bikeService.retrieveABike(bikeId));
            return "purchase";
        }

        return "redirect:/dashboard";
    }

    @PostMapping("/create-bike")
    public String postBikeAd(Bike bikeData, Model model) {
        model.addAttribute("bike", new Bike());
        bikeService.postBikeAd(bikeData);

        return "redirect:/dashboard";
    }

    @GetMapping("/create-bike")
    public String createBike(@ModelAttribute Bike bike, Model model) {
        model.addAttribute("bike", new Bike());
        return "create-bike";
    }
}

