package br.com.treinamento.curso3.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.treinamento.curso3.entities.Curso;
import br.com.treinamento.curso3.repository.ICursoRepository;
import br.com.treinamento.curso3.response.CursoGetResponse;

@Service
public class CursoService {
	@Autowired
	ICursoRepository cursoRepository;

	@PersistenceContext
	EntityManager em;


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
	  public List<Curso> filtroComposto(String descricao, LocalDate dataAbertura, LocalDate dataFechamento) {

			CriteriaBuilder criteria = em.getCriteriaBuilder();
			CriteriaQuery<Curso> criteriaQuery = criteria.createQuery(Curso.class);

			Root<Curso> curso = criteriaQuery.from(Curso.class);
			List<Predicate> predList = new ArrayList<>();

			if (descricao != null) {
				Predicate descricaoPredicate = criteria.equal(curso.get("descricao"), descricao);
				predList.add(descricaoPredicate);
			}

			if (dataAbertura != null) {
				Predicate dataIniPredicate = criteria.greaterThanOrEqualTo(curso.get("dataAbertura"), dataAbertura);
				predList.add(dataIniPredicate);
			}

			if (dataFechamento != null) {
				Predicate dataTerPredicate = criteria.lessThanOrEqualTo(curso.get("dataFechamento"), dataFechamento);
				predList.add(dataTerPredicate);
			}

			Predicate[] predicateArray = new Predicate[predList.size()];

			predList.toArray(predicateArray);

			criteriaQuery.where(predicateArray);

			TypedQuery<Curso> query = em.createQuery(criteriaQuery);

			return query.getResultList();
		} 
	

		}
	

	
	


