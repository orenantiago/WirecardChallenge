package wirecardchallenge.payments.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Size(min=1,max=50)
    private String name;

    @NotNull
    @Size(min=1, max=50)
    private String cnpj;
}
