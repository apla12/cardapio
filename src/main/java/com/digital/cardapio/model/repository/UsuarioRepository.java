package com.digital.cardapio.model.repository;

import com.digital.cardapio.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
}
