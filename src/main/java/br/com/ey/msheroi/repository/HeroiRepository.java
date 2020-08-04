package br.com.ey.msheroi.repository;

import br.com.ey.msheroi.common.enums.Situacao;
import br.com.ey.msheroi.common.vo.Heroi;
import br.com.ey.msheroi.common.vo.Universo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroiRepository extends JpaRepository<Heroi, Long> {

    List<Heroi> findAllBySituacao(Situacao situacao);

    List<Heroi> findAllByUniverso(Universo universo);
}
