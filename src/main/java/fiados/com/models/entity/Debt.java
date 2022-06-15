package fiados.com.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import fiados.com.models.enums.EnumCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.Builder;

@SQLDelete(sql = "UPDATE debt SET soft_delete=true id=?")
@Where(clause = "soft_delete=false")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ApiModel("Model for debts")
public class Debt {//deudas

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty("Partial debt generated in a trade")
    private double totalAmount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime date;
    @ApiModelProperty("Status of the debt Activated/canceled")
    @Enumerated(value = EnumType.STRING)
    private EnumCondition conditions;
    @ApiModelProperty("A client object")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @ApiModelProperty("A shop object")
    @ManyToOne(fetch = FetchType.LAZY)
    private Trade trade;
    @Column(name = "soft_delete")
    @ApiModelProperty("Logical erase property")
    private boolean softDelete = Boolean.FALSE;
}
