package fiados.com.models.entity;

import fiados.com.models.enums.EnumCondition;
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

    //@OneToOne(mappedBy = "trade")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "point_id", referencedColumnName = "point_id")
    private Point point;
    private EnumCondition status;
    @OneToMany(mappedBy = "trade", cascade = CascadeType.ALL)
    private Branch brach;
}
