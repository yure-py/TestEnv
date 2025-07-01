package api.erp.solarerp.web.dto;

import api.erp.solarerp.web.validation.annotations.AdultAge;
import api.erp.solarerp.web.validation.annotations.BrazilianPhone;
import api.erp.solarerp.web.validation.annotations.NomeCompleto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientePfDTO {
    // Adicionado para identificar o cliente em atualizações, opcional na criação.
    private Long clienteID;

    // campos obrigatorios
    @NotBlank(message = "O nome não pode ser vazio ou nulo.")
    @Size(min = 4, max = 150, message = "O nome completo deve ter entre 4 e 150 caracteres.")
    @NomeCompleto
    private String nomeCompleto;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @AdultAge
    private LocalDate dataNascimento;

    @NotBlank(message = "O RG é obrigatório.")
    private String rg;

    @NotBlank(message = "O CPF é obrigatório.")
    @CPF(message = "O formato do CPF é inválido.")
    private String cpf;

    @NotBlank(message = "O telefone principal é obrigatório.")
    @BrazilianPhone(message = "O formato do telefone principal é inválido.")
    private String telefonePrincipal;

    // campos opcionais
    @Email(message = "O formato do e-mail é inválido.")
    private String email;

    @BrazilianPhone(message = "O formato do telefone secundário é inválido.")
    private String telefoneSecundario;

    @Pattern(regexp = "^[A-Za-z0-9À-ÖØ-öø-ÿ\\s.,-]*$", message = "Observações contêm caracteres inválidos.")
    @Size(max = 500, message = "As observações não podem exceder 500 caracteres.")
    private String observacoes;
}
