package com.crud.promacaoWeb.core.servico;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.promacaoWeb.core.model.Usuario;
import com.crud.promacaoWeb.core.model.dto.UsuarioDTO;
import com.crud.promacaoWeb.core.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio repositorio;

	public List<Usuario> listarUsuarios() {
		return repositorio.findAll();
	}

	public Usuario buscarUsuario(Long id) throws Exception {
		Optional<Usuario> usuario = repositorio.findById(id);

		if (usuario.isEmpty()) {
			throw new Exception("Usuario não encontrado!");
		}

		return usuario.get();
	}

	public Usuario incluirUsuario(UsuarioDTO usuarioDTO) throws Exception {
		Optional<Usuario> usuarioExistente = repositorio.findByEmail(usuarioDTO.getEmail());
		if (usuarioExistente.isPresent()) {
			throw new Exception("Usuario com esse email já cadastrado!");
		}

		return repositorio.save(conventerDTO(usuarioDTO));
	}

	public Usuario alterarUsuario(Long id, UsuarioDTO usuarioDTO) throws Exception {
		
		Optional<Usuario> usuarioExistente = repositorio.findById(id);
		
		if (usuarioExistente.isEmpty()) {
			throw new Exception("Usuario não encontrado!");
		}
		
		if (usuarioExistente.get().getEmail() != usuarioDTO.getEmail() && repositorio.existsByEmail(usuarioDTO.getEmail())) {
			throw new Exception("Usuario com esse email já cadastrado!");
		}
		
		Usuario usuario = conventerDTO(usuarioDTO);
		usuario.setId(id);
		
		
		return repositorio.save(usuario);
		
	}
	
	public void excluirUsuario(Long id) throws Exception {
		if (!repositorio.existsById(id)) {
			throw new Exception("Usuario não encontrado!");
		}
		
		repositorio.deleteById(id);
	}

	private Usuario conventerDTO(UsuarioDTO usuarioDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuarioDTO, Usuario.class);
	}
}
