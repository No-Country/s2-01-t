package fiados.com.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import fiados.com.models.enums.EnumCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import javax.validation.constraints.DecimalMin;
import lombok.Builder;

@SQLDelete(sql = "UPDATE branch SET soft_delete=true debt_id=?")
@Where(clause = "soft_delete=false")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Builder
public class Debt {//deudas

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debt_id")
    private Long id;
     
    private double totalAmount;//cantidad total

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime date;

    @Enumerated(value = EnumType.STRING)
    private EnumCondition conditions;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Trade trade;

}
