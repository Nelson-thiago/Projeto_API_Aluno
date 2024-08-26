package com.api.api_aluno.domain.Entity;

import java.sql.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    Long requestId;
    @Column(name = "matricula")
    @NotNull(message = "Matrícula é obrigatória")
    Long matricula;
    @Column(name = "nome")
    @NotBlank(message = "Nome é obrigatório")
    @Length(message = "Nome com no máximo 50 caracteres", max = 50)
    String nome;
    @Column(name = "idade")
    @NotNull(message = "Idade é obrigatória")
    Integer idade;
    @Column(name = "email")
    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "Deve ser um endereço de e-mail bem formado")
    @Length(message = "E-mail com no máximo 50 caracteres", max = 50)
    String email;
    @Column(name = "data_cadastro")
    @NotNull(message = "Data de cadastro é obrigatória")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Manaus")
    Date dataCadastro;
    @Column(name = "status")
    @NotNull(message = "Status é obrigatório")
    Integer status;
}
