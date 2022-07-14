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
	public void deletar(Curso curso) {
		validaDataExclusao(curso);
		cursoRepository.delete(curso);
	}

	private void validaDataExclusao(Curso curso) {
		if(LocalDate.now().isAfter(curso.getDataTermino())) {
			throw new RuntimeException("Curso Encerrado, não é possivel a exclusão");	
		}
		
	}
	private void validaData(Curso curso) {
		if (curso.getDataInicio().isAfter(curso.getDataTermino())) {
			throw new RuntimeException("Data Invalida");

		}
		
		List<Curso> cursosBuscados = cursoRepository.getAllBetweenDates(curso.getDataInicio(),
                curso.getDataTermino());
        if (cursosBuscados.size() > 0) {
            throw new RuntimeException("Existe(m) curso(s) planejados(s) dentro do período informado.");
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

	
	


