
package fiados.com.models.request;

import fiados.com.models.entity.Customer;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {  

    @NotEmpty(message = "Nombre Obligatorio")
    @Column(name = "name", nullable = false, updatable = true, unique = true)  
    private String name;
    @Column(name = "decription")
    private String description;
    private Customer customer;
 
}
