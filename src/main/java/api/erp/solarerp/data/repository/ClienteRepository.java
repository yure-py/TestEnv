package api.erp.solarerp.data.repository;

import api.erp.solarerp.domain.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM ClientePF c WHERE c.cpf = :cpf")
    boolean existsBycpf(@Param("cpf") String cpf);
}
