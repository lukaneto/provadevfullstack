package com.vivo.luiz.provadevfullstack.repository;

import com.vivo.luiz.provadevfullstack.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> , JpaSpecificationExecutor<Cliente> {
    Optional<Cliente> findById(Long id);
    Optional<Cliente> findByCpf(String cpf);

}
