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
@DiscriminatorValue("PJ")
public class ClientePJ extends Cliente {
    @Column(name = "razao_social", nullable = false, unique = true)
    private String razaoSocial;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Column(nullable = false, unique = true, length = 18)
    private String cnpj;

    @Column(name = "inscricao_municipal")
    private String inscricaoEstadual;
    
    @Column(name = "data_fundacao")
    private LocalDate dataFundacao;
}