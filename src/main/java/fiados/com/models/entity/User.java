
package fiados.com.models.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;



@NoArgsConstructor
@Getter
@Setter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;
    @NotBlank(message = "Email cannot be empty.")
   // @Column(unique = true, nullable = false)
    private String email; // es el username

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, max = 250, message = "Password should have at least 8 characters")
    private String password;

    private String role;

    private String dni;

    private String city;

    private String direction;

    private boolean softDelete;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @Column(name = "role_id")
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
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
        return !this.softDelete;
    }
}
