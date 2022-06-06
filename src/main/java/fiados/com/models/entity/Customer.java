package fiados.com.models.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToMany
    @JoinTable(
            name = "customer_points",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "point_id"))
    protected List<Point> points = new ArrayList<>();

    public void addPoint(Point point){
        points.add(point);
    }

}
