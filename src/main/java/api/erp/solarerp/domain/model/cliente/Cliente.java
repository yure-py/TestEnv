package api.erp.solarerp.domain.model.cliente;

import api.erp.solarerp.domain.model.objectvalues.Phone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_clientes")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_cliente", discriminatorType = DiscriminatorType.STRING)
public abstract class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long clienteID;

    @Column(unique = true)
    private String email;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(
            name = "number", // nome do atributo no ObjectValue
            column = @Column(name = "telefone_principal", nullable = false, length = 25)
        )
    })
    private Phone telefonePrincipal;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(
            name = "number",
            column = @Column(name = "telefone_secundario", length = 25)
        )
    })
    private Phone telefoneSecundario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCliente status;
    
    @Column(columnDefinition = "TEXT")
    private String observacoes;

    // auditoria (campos temporarios mudar para uma classe abstrata)
    @CreatedDate
    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "motivo_desativacao")
    private String motivoDesativacao;

    @PrePersist
    protected void onCreate() {
        this.status = StatusCliente.ATIVO;
        this.criadoEm = LocalDateTime.now();
    }

    // o service chama esse metodo e então usa o save do repository para da um merge na entidade
    public void desativar(String motivo) {
        this.status = StatusCliente.INATIVO;
        this.motivoDesativacao = motivo;
    }
}