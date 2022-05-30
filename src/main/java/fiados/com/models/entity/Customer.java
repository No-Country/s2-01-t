package fiados.com.models.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
public class Customer extends User{


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Debt> debts = new HashSet<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Comment> comments ;

    @OneToOne(mappedBy = "customer")
    private Point point;
}
