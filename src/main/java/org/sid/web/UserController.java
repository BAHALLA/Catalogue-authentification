package org.sid.web;

import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.sid.web.models.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm) {
        return accountService.addUser(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
    }
}
