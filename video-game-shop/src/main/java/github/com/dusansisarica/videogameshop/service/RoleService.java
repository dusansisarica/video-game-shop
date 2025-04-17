package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.model.Role;
import github.com.dusansisarica.videogameshop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
