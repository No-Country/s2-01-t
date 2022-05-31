package fiados.com.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
   
    @NotBlank
    @NotEmpty(message = "Agrega un comentario")
    @Size(min = 3, max = 100, message = "Comment must be between 3 and 100 characters long")
    private String comment;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime date;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    
    
}
