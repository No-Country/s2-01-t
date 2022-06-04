package fiados.com.models.entity;

import fiados.com.models.enums.EnumCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@SQLDelete(sql = "UPDATE trade SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Trade extends User{
    
    @NotBlank
    @NotEmpty(message = "You must entnomer company name")
    @Size(min = 3, max = 100, message = "Company name must be between 3 and 100 characters long")
    private String company_name;
     
    @OneToMany(mappedBy = "trade")
    private Set<Debt> debts = new HashSet<>();

    private EnumCondition status;
    @OneToMany (mappedBy = "trade", cascade = CascadeType.ALL)
    private List<Branch> branchList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "trade_points",
            joinColumns = @JoinColumn(name = "trade_id"),
            inverseJoinColumns = @JoinColumn(name = "point_id"))
    protected List<Point> points = new ArrayList<>();

    public void addPoint(Point point){
        points.add(point);
    }

    public void addBranch(Branch branch){
        branchList.add(branch);
    }

}
