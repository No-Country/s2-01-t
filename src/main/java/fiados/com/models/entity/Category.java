package fiados.com.models.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE Category SET soft_delete = true WHERE id=?")
@FilterDef(
        name = "deletedCategoryFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedCategoryFilter",
        condition = "deleted = :isDeleted"
)
@Entity
@Builder
public class Category  {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    @NotEmpty(message = "Nombre Obligatorio")
    @Column(name = "name", nullable = false, updatable = true, unique = true)
    private String name;

    @Column(name = "description")
    private String description;
    @Column(name = "soft_deleted")
    private boolean soft_deleted = Boolean.FALSE;

    public boolean isEnabled() {
        return !this.soft_deleted;
    }

    @OneToOne(mappedBy = "category")
    private Branch branch;
}
