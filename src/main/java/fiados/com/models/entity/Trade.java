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


    @OneToMany(mappedBy = "trade")
    private Set<Debt> debts = new HashSet<>();

    @OneToOne(mappedBy = "trade")
    private Point point;

}
