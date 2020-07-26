package br.com.ey.msheroi.repository;

import br.com.ey.msheroi.enums.TipoSituacaoEnum;
import br.com.ey.msheroi.vo.Heroi;
import br.com.ey.msheroi.vo.Universo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroiRepository extends JpaRepository<Heroi, Long> {

    List<Heroi> findAllBySituacao(TipoSituacaoEnum tipoSituacaoEnum);

    List<Heroi> findAllByUniverso(Universo universo);
}
