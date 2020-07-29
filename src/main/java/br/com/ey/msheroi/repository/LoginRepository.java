package br.com.ey.msheroi.repository;

import br.com.ey.msheroi.vo.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

    Login findByUsername(String username);
}
