package wirecardchallenge.payments.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wirecardchallenge.Views;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static wirecardchallenge.WirecardChallengeExceptions.invalidClientCnpj;
import static wirecardchallenge.WirecardChallengeExceptions.invalidClientName;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "clients")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private Integer id;

    @NotNull
    @Size(min=1,max=50)
    @JsonView(Views.Detail.class)
    private String name;

    @NotNull
    @Size(min=1, max=50)
    @JsonView(Views.Detail.class)
    private String cnpj;

    public void validateMe(){
        if(name == null) {
            throw invalidClientName;
        }
        if(cnpj == null) {
            throw invalidClientCnpj;
        }
    }
}
