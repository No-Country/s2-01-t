
package fiados.com.models.entity;

import fiados.com.models.enums.EnumCondition;
import fiados.com.models.enums.EnumRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class User {

    private String firstName;

    private String lastName;

    @NotBlank(message = "Email cannot be empty.")
    @Column(unique = true, nullable = false)
    private String email; // es el username

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, max = 250, message = "Password should have at least 8 characters")
    private String password;

    private EnumRoles role;

    private boolean softDelete;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @Column(name = "role_id")
    private Set<Role> roles;

    private List<Debts> debts;
}
