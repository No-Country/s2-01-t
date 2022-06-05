package fiados.com.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


import javax.persistence.*;

@SQLDelete(sql = "UPDATE branch SET soft_delete=true branch_id=?")
@Where(clause = "soft_delete=false")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Branch {//sucursal

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long id;
    //@NotBlank
    private String descriptions;
    //@NotBlank
    private String direccion;

    @JoinColumn(name = "trade_id")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Trade trade;

    @Column(name = "soft_delete")
    private boolean softDelete = Boolean.FALSE;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;


}
