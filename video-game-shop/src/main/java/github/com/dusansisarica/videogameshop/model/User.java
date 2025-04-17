package github.com.dusansisarica.videogameshop.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @Column(name = "verification_code", length = 64)
    private String verificationCode;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private boolean verified;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @Column
    private String name;
    @Column
    private String surname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column
    private boolean deleted = false;

    public User() {
    }

    public User(int ID, String password, String email) {
        this.ID = ID;
        this.password = password;
        this.email = email;
    }

    public User(String password, String email, boolean verified) {
        this.password = password;
        this.email = email;
        this.verified = verified;
    }

    public User(Integer ID, String verificationCode, String password, String email, boolean verified, List<Role> roles) {
        this.ID = ID;
        this.verificationCode = verificationCode;
        this.password = password;
        this.email = email;
        this.verified = verified;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
