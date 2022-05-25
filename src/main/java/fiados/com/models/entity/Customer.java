package fiados.com.models.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@PrimaryKeyJoinColumn(name = "customer_Id")
public class Customer extends User{

    @OneToMany(mappedBy = "customer")
    private Set<Debt> debts = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
  
   
}
