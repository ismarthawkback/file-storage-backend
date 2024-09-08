package in.damodar.filestorage.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {
    public UserDetails userDetails;
    public UserPrincipalAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        //TODO Auto-generated constructor stub
    }

    public UserPrincipalAuthenticationToken(UserDetails userDetails) {
        this(userDetails.getAuthorities());
        this.userDetails = userDetails;
    }

    

    @Override
    public Object getCredentials() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCredentials'");
    }

    @Override
    public Object getPrincipal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrincipal'");
    }
    
}
