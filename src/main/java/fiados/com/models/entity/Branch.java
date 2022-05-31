package fiados.com.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Branch {//sucursal

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long id;
    @NotBlank
    private String descriptions;
    @NotBlank
    private String direccion;
    @JoinColumn(name = "trade_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Trade trade;



}
