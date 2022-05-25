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
@PrimaryKeyJoinColumn(name = "trade_Id")
public class Trade extends User{    

    @OneToMany
    @JoinColumn(name = "address_id")
    private Set<Address> addressSet = new HashSet<>();

    @OneToMany(mappedBy = "trade")
    private Set<Debt> debts = new HashSet<>();

}
