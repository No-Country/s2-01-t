package fiados.com.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
public class Comment {//comentarios

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @NotBlank(message = "Comment cannot be empty.")
    @Size(min = 8, max = 250, message = "Comment should have at least 8-250 characters")
    private String msj;    
    
    @JsonFormat(pattern="dd/MM/yyyy")    
    private LocalDate date;
    
    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "comment")  
    private List<Customer> customer;
}
