package fiados.com.models.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("Category Model")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Nombre Obligatorio")
    @ApiModelProperty("Name of the category")
    @Column(name = "name", nullable = false, updatable = true, unique = true)
    private String name;
    @ApiModelProperty("Description of the category")    
    private String description;
    @Column(name = "soft_deleted")
    @ApiModelProperty("Logical erase")
    private boolean soft_deleted = Boolean.FALSE;

    public boolean isEnabled() {
        return !this.soft_deleted;
    }

}
