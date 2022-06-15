package fiados.com.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Builder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@ApiModel("Model Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @ApiModelProperty("own text of the message")
    @NotBlank
    @NotEmpty(message = "Agrega un comentario")
    @Size(min = 3, max = 100, message = "Comment must be between 3 and 100 characters long")
    private String comment;
    @ApiModelProperty("message date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime date;
    @ApiModelProperty("Customer who receives or sends feedback")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
     @ApiModelProperty("Trade who receives or sends feedback")
    @ManyToOne(fetch = FetchType.LAZY)
    private Trade trade;

}
