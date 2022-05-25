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
@PrimaryKeyJoinColumn(name = "customer_Id")
public class Customer extends User{

    @OneToMany(mappedBy = "customer")
    private Set<Debt> debts = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "comment_customer", joinColumns ={@JoinColumn(name = "id_customer")},
    inverseJoinColumns = {@JoinColumn(name = "id_comment")}) 
    private List<Comment> comment;
}
