package redhat.engineering.ebikes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import redhat.engineering.ebikes.entities.Service_User;
import redhat.engineering.ebikes.services.UserService;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String postLoginData(Model model) {
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("service_user", new Service_User());

        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute Service_User serviceUser, Model model) {
        model.addAttribute("service_user", serviceUser);
        userService.createUser(serviceUser);

        return "redirect:/dashboard";
    }

    @GetMapping("/users")
    public String customers(Model model) {
        model.addAttribute("service_users", userService.retrieveUsers("CUSTOMERS"));

        return "users";
    }

    @GetMapping("/user/delete/{userId}")
    public String deleteUser(@PathVariable Long userId, Model model) {
        Optional<Service_User> retrievedUser = userService.retrieveAUser(userId) ;

        if (retrievedUser.isPresent()) {
            userService.deleteAUser(userId);
        }

        return "redirect:/users";
    }
}

