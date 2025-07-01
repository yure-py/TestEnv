package api.erp.solarerp.domain.model.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("PF")
public class ClientePF extends Cliente {
    @Column(name = "nome_completo", length = 150, nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;
    
    @Column(length = 20, nullable = false)
    private String rg;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
}