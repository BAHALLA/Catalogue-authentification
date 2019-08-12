package org.sid.service;

import org.sid.entities.AppRole;
import org.sid.entities.AppUser;

public interface AccountService {
    public AppUser addUser(String username, String password, String confirmedPassword);
    public AppRole addRole(AppRole role);
    public AppUser loadUserByUsername(String username);
    public void addRoleToUser(String roleName, String username);
}
