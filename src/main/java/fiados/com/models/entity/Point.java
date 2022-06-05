package fiados.com.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;

@SQLDelete(sql = "UPDATE point SET soft_delete=true id=?")
@Where(clause = "soft_delete=false")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Point {// puntos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCostumer;

    private Long idTrade;

    @Range (min = 1, max = 5, message = "El rango de calificación es de 1 a 5")
    private int point;

    @Column(name = "soft_delete")
    private boolean softDelete = Boolean.FALSE;

    @ManyToMany(mappedBy = "points")
    private List<Trade> tradeList = new ArrayList<>();

    @ManyToMany(mappedBy = "points")
    private List<Customer> customerList = new ArrayList<>();
}
