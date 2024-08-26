package com.api.api_aluno.domain.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.api_aluno.domain.Entity.Request;
import com.api.api_aluno.domain.Enumeration.Status;
import com.api.api_aluno.domain.Repository.RequestRepository;
import com.api.api_aluno.domain.dto.RequestDto;
import com.api.api_aluno.domain.dto.ResponseDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestService {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseDto responseDto;

    RequestRepository requestRepository;

    @SuppressWarnings("null")
    public ResponseDto saveRequest(Request request) {
        responseDto.setId(requestRepository.save(request).getRequestId());
        responseDto.setMenssage("Requisição salva com sucesso...");
        responseDto.setStatus(Status.SUCCESS.value());
        return responseDto;
    }

    public List<RequestDto> getAllRequest() {
        List<RequestDto> listAllRequestDto = requestRepository.findAll(Sort.by(Sort.Direction.ASC, "numeroRequisicao")).stream()
                .map(Request -> modelMapper.map(Request, RequestDto.class))
                .collect(Collectors.toList());
        return listAllRequestDto;
    }

    @SuppressWarnings("null")
    public RequestDto getRequestById(Long numeroRequisicao) {
        return modelMapper.map(requestRepository.findRequestById(numeroRequisicao), RequestDto.class);
    }


}
