package fiados.com.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Trade extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    private Long id;

    @OneToMany
    @JoinColumn(name = "address_id")
    private Set<Address> addressSet = new HashSet<>();
}
