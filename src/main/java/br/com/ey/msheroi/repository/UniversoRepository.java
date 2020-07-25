package br.com.ey.msheroi.repository;

import br.com.ey.msheroi.vo.Universo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversoRepository extends JpaRepository<Universo, Integer> {


}
