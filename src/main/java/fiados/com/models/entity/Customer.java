package fiados.com.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
public class Customer extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @OneToMany(mappedBy = "costumer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comments> comments = new ArrayList<>();


}
