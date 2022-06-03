package fiados.com.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Point {// puntos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Long id;
    private Long idCostumer;
    private Long idTrade;
    @Range (min = 1, max = 5, message = "El rango de calificación es de 1 a 5")
    private int point;

    @OneToOne(mappedBy = "point")
    private Customer customer;

    @OneToOne(mappedBy = "point")    
    private Trade trade;
}
