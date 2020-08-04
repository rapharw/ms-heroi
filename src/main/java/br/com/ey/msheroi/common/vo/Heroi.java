package br.com.ey.msheroi.common.vo;

import br.com.ey.msheroi.common.enums.Situacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "HEROIS")
@NoArgsConstructor
public class Heroi implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name= "id_universo", nullable = false)
    private Universo universo;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "situacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable( name = "poderes_herois",
                joinColumns = @JoinColumn(name="id_heroi", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name="id_poder", referencedColumnName = "id")
    )
    private Set<Poder> poderes;

    @Builder
    public Heroi(String nome, Universo universo, Situacao situacao){
        this.nome = nome;
        this.universo = universo;
        this.situacao = situacao;
    }

    @Transient
    @JsonIgnore
    public boolean isAtivo(){
        return this.situacao != null && Situacao.ATIVO == situacao;
    }

    public Heroi adicionaUmPoder(Poder poder){
        if(poderes == null)
            poderes = new HashSet<>();
        poderes.add(poder);
        return this;
    }

    public Heroi removeUmPoder(Poder poder){
        if(poderes != null){
            poderes.removeIf(p -> p.getId().equals(poder.getId()));
        }
        return this;
    }

    public String getDataCadastro(){
        if(this.dataCadastro != null)
            return this.dataCadastro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return "-";
    }

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
    }

    public String toJson(GsonBuilder gsonBuilder){
        Gson gson = gsonBuilder .setDateFormat("dd/MM/yyyy").create();
        return gson.toJson(this);
    }
}
