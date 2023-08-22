package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.dto.LoginDto;
import github.com.dusansisarica.videogameshop.dto.RegistrationDto;
import github.com.dusansisarica.videogameshop.dto.UserDetailDto;
import github.com.dusansisarica.videogameshop.dto.UserDto;
import github.com.dusansisarica.videogameshop.mapper.AddressDtoMapper;
import github.com.dusansisarica.videogameshop.mapper.UserDtoMapper;
import github.com.dusansisarica.videogameshop.model.Address;
import github.com.dusansisarica.videogameshop.model.Role;
import github.com.dusansisarica.videogameshop.model.User;
import github.com.dusansisarica.videogameshop.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
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
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserDtoMapper userDtoMapper;
    @Autowired
    private AddressDtoMapper addressDtoMapper;

    public UserDto save(RegistrationDto registrationDto, String siteUrl) throws MessagingException, UnsupportedEncodingException {
        User newUser = new User(passwordEncoder.encode(registrationDto.passwordFirst), registrationDto.email, false);
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findByName("ROLE_USER"));
        newUser.setRoles(roles);
        String randomCode = RandomString.make(64);
        newUser.setVerificationCode(randomCode);
        newUser.setAddress(new Address("", "", "", null, null));
        userRepository.save(newUser);
        emailService.sendVerificationEmail(newUser, siteUrl);
        return userDtoMapper.fromModeltoDTO(newUser);
    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        if (user == null || user.isVerified()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setVerified(true);
            userRepository.save(user);
            return true;
        }
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

    public UserDetailDto getUserDetails(String email){
        User user = userRepository.findByEmail(email);
        UserDetailDto userDto = new UserDetailDto();
        userDto.address = addressDtoMapper.fromModeltoDTO(user.getAddress());
        userDto.email = user.getEmail();
        userDto.name = user.getName();
        userDto.surname = user.getSurname();
        return userDto;
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

    public UserDto changeDetails(UserDetailDto userDetail) {
        User user = userRepository.findByEmail(userDetail.email);
        user.setName(userDetail.name);
        user.setSurname(userDetail.surname);
        Address address = new Address(userDetail.address.address, userDetail.address.city, userDetail.address.country, userDetail.address.longitude, userDetail.address.latitude);
        user.setAddress(address);
        userRepository.save(user);
        return userDtoMapper.fromModeltoDTO(user);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
