package com.api.api_aluno.domain.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.api_aluno.domain.Entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("SELECT a FROM Aluno a WHERE a.matricula = ?1")
    Aluno findAlunoByMatricula(Long matricula);

    @Query("SELECT a FROM Aluno a WHERE a.nome like ?1")
    List<Aluno> findAlunoByName(String nome);

    @Query("SELECT a FROM Aluno a WHERE a.email = ?1")
    Aluno findAlunoByEmail(String email);
}



