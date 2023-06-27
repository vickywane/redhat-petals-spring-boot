package redhat.engineering.ebikes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import redhat.engineering.ebikes.entities.Bike;
import redhat.engineering.ebikes.entities.Service_User;
import redhat.engineering.ebikes.services.BikeService;

import java.util.Optional;

@Controller
public class BikeController {
    @Autowired
    BikeService bikeService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("bikes", bikeService.retrieveBikes());

        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("bikes", bikeService.retrieveBikes());

        return "dashboard";
    }

    @GetMapping("/purchase")
    public String purchase(@RequestParam Long bikeId, Model model) {
        if (bikeService.retrieveABike(bikeId).isPresent()) {
            model.addAttribute("bike", bikeService.retrieveABike(bikeId));
            return "purchase";
        }

        return "redirect:/dashboard";
    }

    @PostMapping("/purchase/{bikeId}")
    public String submitPurchase(@PathVariable Long bikeId , Model model) {
        Optional<Bike> retrievedBike = bikeService.retrieveABike(bikeId);

        if (retrievedBike.isPresent()) {
            model.addAttribute("bike", retrievedBike);
            bikeService.purchaseBike(retrievedBike);

            return "home";
        }

        return "redirect:/home";
    }

    @PostMapping("/create-bike")
    public String postBikeAd(@ModelAttribute Bike bikeData, Model model) {
        model.addAttribute("bike", bikeData);
        bikeService.postBikeAd(bikeData);

        return "redirect:/dashboard";
    }

    @GetMapping("/create-bike")
    public String createBike(Model model) {
        model.addAttribute("bike", new Bike());
        return "create-bike";
    }

    @GetMapping("/bike/update")
    public String updateBike(@RequestParam Long id, Model model) {
        if (bikeService.retrieveABike(id).isPresent()) {
            model.addAttribute("bike_form", new Bike());
            model.addAttribute("bike", bikeService.retrieveABike(id));
            return "update-bike";
        }

        return "redirect:/dashboard";
    }

    @PostMapping("/bike/update")
    public String submitBikeUpdateForm(@RequestParam Long id, Model model) {
        if (bikeService.retrieveABike(id).isPresent()) {
            model.addAttribute("bike", bikeService.retrieveABike(id));
            return "update-bike";
        }

        return "redirect:/dashboard";
    }

    @GetMapping("/bike/delete/{bikeId}")
    public String deleteBike(@PathVariable Long bikeId, Model model) {
        Optional<Bike> singleBike = bikeService.retrieveABike(bikeId);

        singleBike.ifPresent(bike -> bikeService.deleteABike((Long) bike.getId()));

        return "redirect:/dashboard";
    }
}

