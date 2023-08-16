package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.dto.LoginDto;
import github.com.dusansisarica.videogameshop.dto.RegistrationDto;
import github.com.dusansisarica.videogameshop.dto.UserDto;
import github.com.dusansisarica.videogameshop.model.Role;
import github.com.dusansisarica.videogameshop.model.User;
import github.com.dusansisarica.videogameshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    public User save(RegistrationDto registrationDto){
        User newUser = new User(passwordEncoder.encode(registrationDto.passwordFirst), registrationDto.email, false);
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findByName("ROLE_USER"));
        newUser.setRoles(roles);
        userRepository.save(newUser);
        return newUser;
    }

    public boolean emailExists(String email){
        if (userRepository.findByEmail(email) != null){
            return true;
        }
        return false;
    }

    public User findUserByEmailAndPassword(LoginDto loginDto){
        User user = userRepository.findByEmailAndPassword(loginDto.email, loginDto.password);
        if (user == null || !user.isVerified()) return null;
        return user;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
        else return user;

    }
}
