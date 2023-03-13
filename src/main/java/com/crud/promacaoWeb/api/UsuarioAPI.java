package com.crud.promacaoWeb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.promacaoWeb.core.servico.UsuarioServico;

import jakarta.validation.Valid;

import com.crud.promacaoWeb.core.model.*;
import com.crud.promacaoWeb.core.model.dto.UsuarioDTO;

@RestController
@RequestMapping("/usuarios")
public class UsuarioAPI {
	
	@Autowired
	private UsuarioServico servico;
	
	@GetMapping
	public List<Usuario> listarUsuario() {
		return servico.listarUsuarios();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaUsuario(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(servico.buscarUsuario(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> incluirUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(servico.incluirUsuario(usuarioDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alterarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioDTO usuarioDTO) {
		try {
			return ResponseEntity.ok(servico.alterarUsuario(id, usuarioDTO));
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirUsuario(@PathVariable Long id) {
		try {
			servico.excluirUsuario(id);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.noContent().build();
	}
	

}
