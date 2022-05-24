package fiados.com.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    private String street;

    private String city;

    private String Country;

    @OneToOne(mappedBy = "address",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Customer customer;

    @OneToOne(mappedBy = "address",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Branch branch;


}
