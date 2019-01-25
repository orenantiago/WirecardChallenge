package wirecardchallenge.payments.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Table(name = "cards")
@EqualsAndHashCode
public class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private Integer number;

    @NotNull
    @Size(min=1,max=50)
    private String holderName;

    @NotNull
    private Integer cvv;

    @NotNull
    private Date expiration;
}
