package br.com.treinamento.curso3.controller;

import java.io.IOException;
import java.time.LocalDate;
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
import br.com.treinamento.curso3.entities.Log;
import br.com.treinamento.curso3.repository.ICursoRepository;
import br.com.treinamento.curso3.request.CursoPostRequest;
import br.com.treinamento.curso3.request.CursoPutRequest;
import br.com.treinamento.curso3.response.CursoGetResponse;
import br.com.treinamento.curso3.service.CursoService;
import io.swagger.annotations.ApiOperation;

@Controller
@RestController
@Transactional
public class CursoController {
	@Autowired
	private ICursoRepository cursoRepository;
	// metodo para
	private static final String ENDPOINT = "api/cursos";

	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@ApiOperation("Serviço para cadastro de Curso")
	@CrossOrigin
	public ResponseEntity<String> post(@RequestBody CursoPostRequest request) throws IOException {
		Log.escreverLog1();
		
		try {
			
			Curso curso = new Curso();
			if (curso.getDataInicio().isAfter(curso.getDataTermino())) {
				throw new RuntimeException("Data Invalida");
			}else {
			curso.setDescricao(request.getDescricao());
			curso.setDataInicio(request.getDataInicio());
			curso.setDataTermino(request.getDataTermino());
			cursoRepository.save(curso);

			return ResponseEntity.status(HttpStatus.OK).body("Curso cadastrado com sucesso.");

		}} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro:" + e.getMessage());
		}
	}

	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	@ApiOperation("Serviço para edição de Curso")
	@CrossOrigin
	public ResponseEntity<String> put(@RequestBody CursoPutRequest request) throws IOException {
		Log.escreverLog2();
		try {

			Optional<Curso> item = cursoRepository.findById(request.getIdCurso());
			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não encontrado");
			} else {
				Curso curso = item.get();
				curso.setDescricao(request.getDescricao());
				curso.setDataInicio(request.getDataInicio());
				curso.setDataTermino(request.getDataTermino());

				cursoRepository.save(curso);
				return ResponseEntity.status(HttpStatus.OK).body("Curso atualizado com sucesso");

			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro:" + e.getMessage());
		}
	}

	@RequestMapping(value = ENDPOINT + "/{idCurso}", method = RequestMethod.DELETE)
	@ApiOperation("Serviço para exclusão de curso")
	@CrossOrigin
	public ResponseEntity<String> delete(@PathVariable("idCurso") Integer idCurso) throws IOException {
		Log.escreverLog4();
		try {

			Optional<Curso> item = cursoRepository.findById(idCurso);

			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não encontrado");
			} else {
				Curso curso = item.get();
				cursoRepository.delete(curso);
				return ResponseEntity.status(HttpStatus.OK).body("Curso excluido com sucesso");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro:" + e.getMessage());
		}

	}

	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	@ApiOperation("Serviço para consulta de Curso")
	@CrossOrigin
	public ResponseEntity<List<Curso>> get() throws IOException {

		List<Curso> cursos = cursoRepository.findAll();

		return ResponseEntity.ok(cursos);
		
	}

	@RequestMapping(value = ENDPOINT + "/{idCategoria}", method = RequestMethod.GET)
	@ApiOperation("Serviço para consulta de curso pela categoria")
	@CrossOrigin
	public ResponseEntity<List<Curso>> getById(@PathVariable("idCategoria") Integer idCategoria) {
		
		List<Curso> curso = cursoRepository.findByIdCategoria(idCategoria);

		return ResponseEntity.ok(curso);
	}
}
