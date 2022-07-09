package br.com.treinamento.curso3.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import br.com.treinamento.curso3.entities.Categoria;
import br.com.treinamento.curso3.entities.Curso;
import br.com.treinamento.curso3.repository.ICategoriaRepository;
import br.com.treinamento.curso3.repository.ICursoRepository;
import br.com.treinamento.curso3.request.CategoriaPostRequest;
import br.com.treinamento.curso3.request.CategoriaPutRequest;
import br.com.treinamento.curso3.response.CategoriaGetResponse;
import br.com.treinamento.curso3.response.CursoCategoriaGetResponse;
import io.swagger.annotations.ApiOperation;

@Controller
@RestController
@Transactional
public class CategoriaController {
	@Autowired
	private ICategoriaRepository categoriaRepository;
	@Autowired
	private ICursoRepository cursoRepository;
	// metodo para
	private static final String ENDPOINT = "api/categorias";

	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@ApiOperation("Serviço para cadastro de Categorias")
	@CrossOrigin
	public ResponseEntity<String> post(@RequestBody CategoriaPostRequest request) {
		try {
			Categoria categoria = new Categoria();
			categoria.setTipo(request.getTipo());

			categoriaRepository.save(categoria);

			return ResponseEntity.status(HttpStatus.OK).body("Categoria cadastrada com sucesso.");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro:" + e.getMessage());
		}
	}

	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	@ApiOperation("Serviço para edição de Categorias")
	@CrossOrigin
	public ResponseEntity<String> put(@RequestBody CategoriaPutRequest request) {
		try {

			Optional<Categoria> item = categoriaRepository.findById(request.getIdCategoria());
			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Categoria não encontrada");
			} else {
				Categoria categoria = item.get();
				categoria.setTipo(request.getTipo());

				categoriaRepository.save(categoria);
				return ResponseEntity.status(HttpStatus.OK).body("Categoria atualizada com sucesso");

			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro:" + e.getMessage());
		}
	}

	@RequestMapping(value = ENDPOINT + "/{idCategoria}", method = RequestMethod.DELETE)
	@ApiOperation("Serviço para exclusão de categoria")
	@CrossOrigin
	public ResponseEntity<String> delete(@PathVariable("idCategoria") Integer idCategoria) {
		try {

			Optional<Categoria> item = categoriaRepository.findById(idCategoria);

			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Categoria não encontrada");
			} else {
				Categoria categoria = item.get();
				categoriaRepository.delete(categoria);
				return ResponseEntity.status(HttpStatus.OK).body("Categoria excluida com sucesso");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro:" + e.getMessage());
		}

	}

	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	@ApiOperation("Serviço para consulta de Categoria")
	@CrossOrigin
	public ResponseEntity<List<CategoriaGetResponse>> get() {
		List<CategoriaGetResponse> response = new ArrayList<CategoriaGetResponse>();
		for (Categoria categoria : categoriaRepository.findAll()) {
			CategoriaGetResponse item = new CategoriaGetResponse();
			item.setIdCategoria(categoria.getIdCategoria());
			item.setTipo(categoria.getTipo());

			response.add(item);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@RequestMapping(value = ENDPOINT + "/{idCategoria}", method = RequestMethod.GET)
	@ApiOperation("Serviço para consulta de categoria")
	@CrossOrigin
	public ResponseEntity<CursoCategoriaGetResponse> getById(@PathVariable("idCategoria") Integer idCategoria) {
		Optional<Categoria> item = categoriaRepository.findById(idCategoria);
		if (item.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			CursoCategoriaGetResponse response = new CursoCategoriaGetResponse();
			Categoria categoria = item.get();
			response.setTipo(categoria.getTipo());
			List<Curso> cursos = cursoRepository.findByIdCategoria(categoria.getIdCategoria());
			for (Curso curso : cursos) {
				response.getDescricaoCursos().add(curso.getDescricao());
				response.getDataInicio().add(curso.getDataInicio());
				response.getDataTermino().add(curso.getDataTermino());
			}
			return ResponseEntity.status(HttpStatus.OK).body(response);

		}
	}
}
