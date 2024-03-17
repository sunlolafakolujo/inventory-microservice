package com.inventory.appuserservice.userverificationtoken.controller;



import com.inventory.appuserservice.appuser.entity.AppUser;
import com.inventory.appuserservice.events.event.RegistrationEvent;
import com.inventory.appuserservice.userverificationtoken.entity.UserVerification;
import com.inventory.appuserservice.userverificationtoken.service.UserVerificationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/userVerification")
public record UserVerificationController(UserVerificationService userVerificationService,
                                         ApplicationEventPublisher publisher) {
    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result=userVerificationService.validateVerificationToken(token);

        if (result.equalsIgnoreCase("Valid token")){
            return "Registration successful";
        }
        return "Bad user";
    }

    @GetMapping("/resendVerificationToken")
    public String resendVerificationToken(@RequestParam("token") String OldToken, HttpServletRequest request) {
        UserVerification userVerification =userVerificationService.generateNewVerificationToken(OldToken);
        AppUser appUser=userVerification.getAppUser();
        publisher.publishEvent(new RegistrationEvent(appUser,applicationUrl(request)));
        return "Click on the link in the message sent your inbox to verify registration";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+ ":"+request.getServerPort()+request.getContextPath();
    }
}
