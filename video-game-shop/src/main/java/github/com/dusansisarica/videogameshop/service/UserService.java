package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.dto.*;
import github.com.dusansisarica.videogameshop.mapper.AddressDtoMapper;
import github.com.dusansisarica.videogameshop.mapper.UserDtoMapper;
import github.com.dusansisarica.videogameshop.model.*;
import github.com.dusansisarica.videogameshop.repository.CityRepository;
import github.com.dusansisarica.videogameshop.repository.CountryRepositroy;
import github.com.dusansisarica.videogameshop.repository.ShopRepository;
import github.com.dusansisarica.videogameshop.repository.UserRepository;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private CountryRepositroy countryRepositroy;
    @Autowired
    private CityRepository cityRepository;

    public UserDto save(RegistrationDto registrationDto, String siteUrl) throws UnsupportedEncodingException, MessagingException {
        User newUser = new User(passwordEncoder.encode(registrationDto.passwordFirst), registrationDto.email, false);
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findByName("ROLE_USER"));
        newUser.setRoles(roles);
        String randomCode = RandomString.make(64);
        newUser.setVerificationCode(randomCode);
        newUser.setAddress(new Address( "", null, null, null));
        userRepository.save(newUser);
        emailService.sendVerificationEmail(newUser, siteUrl);
        return UserDtoMapper.fromModeltoDTO(newUser);
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

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public User findUserByEmailAndPassword(LoginDto loginDto) {
        User user = userRepository.findByEmailAndPassword(loginDto.email, loginDto.password);
        if (user == null || !user.isVerified()) return null;
        return user;
    }

    public UserDetailDto getUserDetails(String email) {
        User user = userRepository.findByEmail(email);
        UserDetailDto userDto = new UserDetailDto();
        if (user.getAddress().getCity() != null){
            userDto.address = AddressDtoMapper.fromModeltoDTO(user.getAddress());
        }
        if (user.getName() != null && user.getSurname() != null){
            userDto.name = user.getName();
            userDto.surname = user.getSurname();
        }
        userDto.email = user.getEmail();
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

    public UserPagination deleteUser(Integer id, PaginationDto paginationDto) {
        User user = userRepository.findById(id).orElse(null);
        user.setDeleted(true);
        userRepository.save(user);
        return findAll(paginationDto);
    }

    public UserDto changeDetails(UserDetailDto userDetail) {
        User user = userRepository.findByEmail(userDetail.email);
        user.setName(userDetail.name);
        user.setSurname(userDetail.surname);
        Country country = countryRepositroy.findByName(userDetail.getAddress().getCity().getCountryDto().name);
        if (country == null) {
            country = new Country(userDetail.getAddress().getCity().getCountryDto().name);
        }
        City city = cityRepository.findByCityNameAndCountryName(userDetail.getAddress().getCity().name, country.getName());
        if (city == null) {
            city = new City(userDetail.getAddress().getCity().name, country);
        }
        countryRepositroy.save(country);
        cityRepository.save(city);

        Address address = new Address(userDetail.address.address, city, userDetail.address.longitude, userDetail.address.latitude);
        user.setAddress(address);
        userRepository.save(user);
        return UserDtoMapper.fromModeltoDTO(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserDto employUser(EmployeeDto employeeDto) {
        User user = this.userRepository.findById(employeeDto.getUserId()).orElse(null);
        user.setShop(shopRepository.findById(employeeDto.shopId).orElse(null));
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findByName("ROLE_STAFF"));
        user.setRoles(roles);
        this.userRepository.save(user);
        return UserDtoMapper.fromModeltoDTO(user);
    }

    public UserDto unemployUser(EmployeeDto employeeDto) {
        User user = this.userRepository.findById(employeeDto.getUserId()).orElse(null);
        user.setShop(null);
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findByName("ROLE_USER"));
        user.setRoles(roles);
        this.userRepository.save(user);
        return UserDtoMapper.fromModeltoDTO(user);
    }

    public UserPagination getAllWithPagination(List<UserDetailDto> users, PaginationDto paginationDto) {
        int startIndex = (paginationDto.pageNumber - 1) * paginationDto.pageSize;
        int endIndex = startIndex + paginationDto.pageSize;
        if (endIndex > users.size()) endIndex = users.size();
        if (startIndex > users.size()) users = new ArrayList<>();
        return new UserPagination(users.subList(startIndex, endIndex), users.size());
    }

    public UserPagination findAll(PaginationDto paginationDto) {
        List<UserDetailDto> users = new ArrayList<>();
        List<User> allUsers = userRepository.findAllByDeletedFalse();
        allUsers = allUsers.stream()
                .filter(user -> paginationDto.searchQuery == null || paginationDto.searchQuery.isEmpty() ||
                        user.getEmail().toLowerCase().contains(paginationDto.searchQuery.toLowerCase()))
                .collect(Collectors.toList());
        for (User u : allUsers) {
            UserDetailDto user = new UserDetailDto(u.getID(), u.getEmail(), u.getName(), u.getSurname(), AddressDtoMapper.fromModeltoDTO(u.getAddress()));
            user.setRole(u.getRoles().get(0));
            users.add(user);
        }
        return getAllWithPagination(users, paginationDto);
    }

    public UserPagination findAllUsers(PaginationDto paginationDto) {
        List<UserDetailDto> users = new ArrayList<>();
        List<User> allUsers = userRepository.findAllByDeletedFalse();
        for (User u : allUsers) {
            if (u.getRoles().get(0) == roleService.findByName("ROLE_USER") || u.getRoles().get(0) == roleService.findByName("ROLE_STAFF")){
                UserDetailDto user = new UserDetailDto(u.getID(), u.getEmail(), u.getName(), u.getSurname(), AddressDtoMapper.fromModeltoDTO(u.getAddress()));
                user.setRole(u.getRoles().get(0));
                users.add(user);
            }
        }
        users = users.stream().filter(user -> paginationDto.searchQuery == null || paginationDto.searchQuery.isEmpty() ||
                                 user.getEmail().toLowerCase().contains(paginationDto.searchQuery.toLowerCase()))
                                .collect(Collectors.toList());
        System.out.println(users.size());
        return getAllWithPagination(users, paginationDto);
    }

    public UserDto registerManager(UserDto userDto) {
        if (!emailExists(userDto.email)){
            User newUser = new User(passwordEncoder.encode(userDto.password), userDto.email, true);
            List<Role> roles = new ArrayList<>();
            roles.add(roleService.findByName("ROLE_MANAGER"));
            newUser.setRoles(roles);
            newUser.setAddress(new Address( "", null, null, null));
            userRepository.save(newUser);
            return userDto;
        }
        return userDto;
    }
}
