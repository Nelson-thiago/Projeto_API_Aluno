package com.api.api_aluno.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;

import com.api.api_aluno.domain.Entity.Request;

public interface RequestRepository extends JpaRepository<Request,Long>{

    @Query("SELECT r FROM Request r WHERE r.requestId = :requestId")
    Request findRequestById(Long requestId);

}