package id.ac.ui.cs.advprog.snackscription_subscriptionbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class SubscriptionBoxController {
    @GetMapping("/Subscription-Box-Admin")
    public String homePage() {
        return "SubscriptionBoxAdmin Display";
    }
}