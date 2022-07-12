package br.com.treinamento.curso3.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinamento.curso3.entities.Curso;
import br.com.treinamento.curso3.repository.ICursoRepository;
import br.com.treinamento.curso3.response.CursoGetResponse;

@Service
public class CursoService {
	@Autowired
	ICursoRepository cursoRepository;

	@Transactional
	public void cadastrar(Curso curso) {
		validaData(curso);
		validaDataInicio(curso);
		cursoRepository.save(curso);
	}
	@Transactional
	public void excluir(Curso curso) {
		validaDataExclusao(curso);
		cursoRepository.save(curso);
	}

	private void validaDataExclusao(Curso curso) {
		// TODO Auto-generated method stub
		
	}
	private void validaData(Curso curso) {
		if (curso.getDataInicio().isAfter(curso.getDataTermino())) {
			throw new RuntimeException("Data Invalida");

		}
	}
	private void validaDataInicio(Curso curso) {
		if(curso.getDataInicio().isBefore(LocalDate.now())) {
			throw new RuntimeException("Data invalida");
		}
	}
	private void cursoJaExistente(Curso curso) {
	
		}
	}
	
	


