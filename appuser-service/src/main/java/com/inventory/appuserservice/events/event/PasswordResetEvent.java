package com.inventory.appuserservice.events.event;


import com.inventory.appuserservice.appuser.entity.AppUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class PasswordResetEvent extends ApplicationEvent {
    private String applicationUrl;
    private AppUser appUser;
    public PasswordResetEvent(String applicationUrl,AppUser appUser) {
        super(appUser);
        this.applicationUrl=applicationUrl;
        this.appUser=appUser;
    }
}
