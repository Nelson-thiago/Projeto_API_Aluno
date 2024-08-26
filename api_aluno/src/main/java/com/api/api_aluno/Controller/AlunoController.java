package com.api.api_aluno.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.api_aluno.domain.Entity.Aluno;
import com.api.api_aluno.domain.Service.AlunoService;
import com.api.api_aluno.domain.dto.AlunoDto;
import com.api.api_aluno.domain.dto.ResponseDto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto saveAluno(@Valid @RequestBody Aluno aluno) {
        aluno.setNome(aluno.getNome().toUpperCase());
        return alunoService.saveAluno(aluno);
    }

    @GetMapping(value = "/list")
    public List<AlunoDto> getAllAlunos() {
        return alunoService.getAllAlunosOrderByName();
    }

    @GetMapping(value = "/{matricula}")
    public AlunoDto getAlunoByMatricula(@Valid @PathVariable Long matricula) {
        return alunoService.getAlunoByMatricula(matricula);
    }

    @PutMapping(value = "/{matricula}")
    public ResponseDto updateAluno(@Valid @PathVariable Long matricula, @RequestBody Aluno aluno) {
        return alunoService.updateAluno(matricula, aluno);
    }    

    @DeleteMapping(value = "/{matricula}")
    public ResponseDto deleteAluno(@Valid @PathVariable Long matricula) {
        return alunoService.deleteAluno(matricula);
    }

    @GetMapping(value = "/nome/{nome}")
    public List<AlunoDto> getAlunoByName(@Valid @PathVariable String nome) {
        nome = "%" + nome + "%";
        return alunoService.getAlunosByName(nome);
    }

    @GetMapping(value = "/email/{email}")
    public AlunoDto getAlunoByEmail(@Valid @PathVariable String email) {
        return alunoService.getAlunoByEmail(email);
    }
}
