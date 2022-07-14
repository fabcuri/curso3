package br.com.treinamento.curso3.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import br.com.treinamento.curso3.entities.Curso;

@Repository
public interface ICursoRepository extends JpaRepository<Curso, Integer> {
	
	
	@Query(value = "from Curso c where c.dataInicio BETWEEN :startDate AND :endDate")
    public List<Curso> getAllBetweenDates(@Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate);
	
	@Query(value = "Select * from curso where idCurso=?1", nativeQuery = true)
	List<Curso> findByIdCurso(Integer idCurso);
	

}
