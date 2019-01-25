package wirecardchallenge.payments.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "buyers")
@EqualsAndHashCode
public class Buyer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Size(min=1,max=50)
    private String name;

    @NotNull
    @Size(min=1,max=50)
    private String email;

    @NotNull
    @Column(unique = true)
    @Size(min=1,max=14)
    private String cpf;


}
