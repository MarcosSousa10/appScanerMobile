package com.othon.carvalho.repository.auth;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.othon.carvalho.model.auth.Produto;


public interface Repositoryoo extends CrudRepository<Produto,Long>{
    @Query(value = "SELECT * FROM software_autoservico where usuario=?1 and senha=?2", nativeQuery = true)
    Optional<Produto> Login(String usuario ,  String senha);
    @Query(value = "SELECT * FROM software_autoservico where usuario=?1",nativeQuery =true)
    Optional<Produto> consultarUsuarioJaCadastrado(String usuario);
}
