package com.inventory.appuserservice.passwordtoken.controller;

import com.inventory.appuserservice.appuser.entity.AppUser;
import com.inventory.appuserservice.appuser.service.AppUserService;
import com.inventory.appuserservice.events.event.PasswordResetEvent;
import com.inventory.appuserservice.passwordtoken.dto.PasswordToken;
import com.inventory.appuserservice.passwordtoken.exception.UserPasswordVerificationNotFoundException;
import com.inventory.appuserservice.passwordtoken.service.UserPasswordVerificationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/passwordVerificationToken")
public record UserPasswordVerificationController(UserPasswordVerificationService userPasswordVerificationService,
                                                 ApplicationEventPublisher publisher, AppUserService userService) {

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordToken passwordToken, HttpServletRequest request) {

        AppUser user = userService.fetchByUsernameOrEmailOrMobile(passwordToken.getUsernameOrMobileOrEmail());
        if (user != null) {
            publisher.publishEvent(new PasswordResetEvent(applicationUrl(request), user));
        }
        return "An email has been sent to your email to reset your password";
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestBody PasswordToken passwordToken, @RequestParam("token") String token) {
        Optional<AppUser> appUser = userPasswordVerificationService.findUserByPasswordToken(token);
        String result = userPasswordVerificationService.validatePasswordToken(token);

        if (!result.equalsIgnoreCase("Valid")) {
            return "Invalid token";
        }
        if (!appUser.isPresent()) {
            userPasswordVerificationService.changeUserPassword(appUser.get(),passwordToken.getNewPassword());
            return "Password Reset Successfully";
        }else return "invalid token";

    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordToken passwordToken) {

        AppUser user = userService.fetchByUsernameOrEmailOrMobile(passwordToken.getUsernameOrMobileOrEmail());
        if (!userPasswordVerificationService.checkIfOldPasswordExist(user, passwordToken.getOldPassword())) {
            throw new UserPasswordVerificationNotFoundException("Invalid old password");
        }
        userPasswordVerificationService.changeUserPassword(user, passwordToken.getNewPassword());
        return "Password changed successfully";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
