package fiados.com.models.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import lombok.Builder;

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
}
