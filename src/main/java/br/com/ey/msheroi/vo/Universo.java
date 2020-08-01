package br.com.ey.msheroi.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UNIVERSOS")
public class Universo implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Integer id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "universo")
    @Transient
    @JsonIgnore
    private Set<Heroi> herois;
}
