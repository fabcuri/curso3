package br.com.treinamento.curso3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.treinamento.curso3.entities.Categoria;

@Repository
public interface ICategoriaRepository extends CrudRepository<Categoria, Integer> {

}
