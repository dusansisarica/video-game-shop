package github.com.dusansisarica.videogameshop.security.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserTokenState {

    private String accessToken;
    private Long expiresIn;
    //private List<Role> role;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
        //this.role = null;
    }
    public UserTokenState(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }


//    public UserTokenState(String accessToken, long expiresIn, List<Role> role) {
//        this.accessToken = accessToken;
//        this.expiresIn = expiresIn;
//        this.role = role;
//    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

}
