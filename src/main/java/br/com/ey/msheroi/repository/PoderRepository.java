package br.com.ey.msheroi.repository;

import br.com.ey.msheroi.common.vo.Poder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoderRepository extends JpaRepository<Poder, Integer> {
}