package fiados.com.models.entity;

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
    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;
}
