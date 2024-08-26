package com.api.api_aluno.domain.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.api_aluno.domain.Entity.Aluno;
import com.api.api_aluno.domain.Enumeration.Status;
import com.api.api_aluno.domain.Repository.AlunoRepository;
import com.api.api_aluno.domain.dto.AlunoDto;
import com.api.api_aluno.domain.dto.ResponseDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlunoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseDto responseDto;

    AlunoRepository alunoRepository;

    public ResponseDto saveAluno(Aluno aluno) {
        responseDto.setId(alunoRepository.save(aluno).getMatricula());
        responseDto.setMenssage("Aluno incluído com sucesso...");
        responseDto.setStatus(Status.SUCCESS.value());
        return responseDto;
    }

    public List<AlunoDto> getAllAlunos() {
        List<AlunoDto> listAllAlunosDto = alunoRepository.findAll().stream()
                .map(aluno -> modelMapper.map(aluno, AlunoDto.class))
                .collect(Collectors.toList());
        return listAllAlunosDto;
    }

    public List<AlunoDto> getAllAlunosOrderByName() {
        List<AlunoDto> listAllAlunosDto = alunoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome")).stream()
                .map(aluno -> modelMapper.map(aluno, AlunoDto.class))
                .collect(Collectors.toList());
        return listAllAlunosDto;
    }

    public AlunoDto getAlunoByMatricula(Long matricula) {
        return modelMapper.map(alunoRepository.findById(matricula).orElse(null), AlunoDto.class);
    }

 
/*    
    public ResponseDto updateAluno(Aluno aluno) {
        responseDto.setId(aluno.getMatricula());
        if (aluno.getMatricula() != null && alunoRepository.existsById(aluno.getMatricula())) {
            alunoRepository.save(aluno);
            responseDto.setMenssage("Aluno alterado com sucesso...");
            responseDto.setStatus(Status.SUCCESS.value());
        } else {
            responseDto.setMenssage("Matrícula do Aluno inválida...");
            responseDto.setStatus(Status.ERROR.value());
        }
        return responseDto;
    }
*/

    public ResponseDto updateAluno(Long matricula, Aluno aluno) {
        responseDto.setId(matricula);
        if (matricula != null && alunoRepository.existsById(matricula)) {
            aluno.setMatricula(matricula); // Garantir que a matrícula do objeto está correta
            alunoRepository.save(aluno);
            responseDto.setMenssage("Aluno alterado com sucesso...");
            responseDto.setStatus(Status.SUCCESS.value());
        } else {
            responseDto.setMenssage("Matrícula do Aluno inválida...");
            responseDto.setStatus(Status.ERROR.value());
        }
        return responseDto;
    }

    public ResponseDto deleteAluno(Long matricula) {
        responseDto.setId(matricula);
        if (matricula != null && alunoRepository.existsById(matricula)) {
            alunoRepository.deleteById(matricula);
            responseDto.setMenssage("Aluno deletado com sucesso...");
            responseDto.setStatus(Status.SUCCESS.value());
        } else {
            responseDto.setMenssage("Matrícula do Aluno inválida...");
            responseDto.setStatus(Status.ERROR.value());
        }
        return responseDto;
    }

    public AlunoDto getAlunoByEmail(String email) {
        return modelMapper.map(alunoRepository.findAlunoByEmail(email), AlunoDto.class);
    }

    public List<AlunoDto> getAlunosByName(String nome) {
        List<AlunoDto> alunos = new ArrayList<>();
        alunoRepository.findAlunoByName(nome).forEach(aluno -> alunos.add(modelMapper.map(aluno, AlunoDto.class)));
        return alunos;
    }
}
