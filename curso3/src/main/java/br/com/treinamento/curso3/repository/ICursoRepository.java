package br.com.treinamento.curso3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.treinamento.curso3.entities.Curso;

@Repository
public interface ICursoRepository extends JpaRepository<Curso, Integer> {
	@Query(value = "Select * from curso where id_categoria=?1", nativeQuery = true)
	List<Curso> findByIdCategoria(Integer idCategoria);

	

}
