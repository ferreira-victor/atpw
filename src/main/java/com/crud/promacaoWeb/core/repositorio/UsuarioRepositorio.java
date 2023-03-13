package com.crud.promacaoWeb.core.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.promacaoWeb.core.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByEmail(String email);
	public boolean existsByEmail(String email);
	
}
