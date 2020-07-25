package br.com.ey.msheroi.vo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PODERES")
public class Poder implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Integer id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToMany(mappedBy="poderes")
    @Transient
    private Set<Heroi> herois;
}
