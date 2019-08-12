package org.sid.service;

import org.sid.dao.RoleRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public AppUser addUser(String username, String password, String confirmedPassword) {
        AppUser user = userRepository.findByUsername(username);
        if(user != null) throw new RuntimeException("User already exists");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm password");

        AppUser user1 = new AppUser();
        user1.setUsername(username);
        user1.setPassword(bCryptPasswordEncoder.encode(password));
        user1.setActivated(true);
        userRepository.save(user1);
        addRoleToUser("USER", username);
        return user1;
    }

    @Override
    public AppRole addRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String roleName, String username) {
            AppUser user = userRepository.findByUsername(username);
            AppRole role = roleRepository.findByRoleName(roleName);
             user.getRoles().add(role);
    }
}
