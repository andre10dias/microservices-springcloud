package com.github.andre10dias.msclientes.application.representation;

import com.github.andre10dias.msclientes.domain.Cliente;
import jakarta.validation.constraints.*;

public record ClienteSaveRequest(
    @NotBlank(message = "CPF não pode estar vazio")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
    String cpf,
    
    @NotBlank(message = "Nome não pode estar vazio")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    String nome,
    
    @NotNull(message = "Idade não pode ser nula")
    @Min(value = 18, message = "Idade mínima é 18 anos")
    @Max(value = 120, message = "Idade máxima é 120 anos")
    Integer idade
) {
    public Cliente toModel() {
        return new Cliente(cpf, nome, idade);
    }

}
