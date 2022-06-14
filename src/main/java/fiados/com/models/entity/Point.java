package fiados.com.models.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
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
@Getter
@Setter
@Builder
@Entity
@ApiModel("Qualification model")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ApiModelProperty("Client id")
    private Long idCostumer;
    @ApiModelProperty("Merchant id")
    private Long idTrade;
    @ApiModelProperty("Qualification")
    @Range(min = 1, max = 5, message = "El rango de calificación es de 1 a 5")
    private int point;
    @ApiModelProperty("Logical erase")
    @Column(name = "soft_delete")
    private boolean softDelete = Boolean.FALSE;
    @ApiModelProperty("Shop relationship")
    @ManyToMany(mappedBy = "points")
    private List<Trade> tradeList = new ArrayList<>();
    @ApiModelProperty("Customer relationship")
    @ManyToMany(mappedBy = "points")
    private List<Customer> customerList = new ArrayList<>();
}
