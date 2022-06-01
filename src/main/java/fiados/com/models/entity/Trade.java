package fiados.com.models.entity;

import fiados.com.models.enums.EnumCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@SQLDelete(sql = "UPDATE trade SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Trade extends User{


    @OneToMany(mappedBy = "trade")
    private Set<Debt> debts = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "point_id", referencedColumnName = "point_id")
    private Point point;

    private EnumCondition status;

    @OneToMany (mappedBy = "trade", cascade = CascadeType.ALL)
    private List<Branch> brach;
}
