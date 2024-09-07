package in.damodar.filestorage.nativeuser;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "users")
@Entity
@Data
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String username;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.PERSIST, optional = false, targetEntity = Divsion.class)
    private Divsion division;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return division.getContentTypes()
        .stream()
        .map(contentType -> new SimpleGrantedAuthority("ACCESS_" + contentType)).toList();
    }
}
