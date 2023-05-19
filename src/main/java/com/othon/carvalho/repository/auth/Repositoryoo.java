package com.othon.carvalho.repository.auth;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.othon.carvalho.model.auth.Produto;


public interface Repositoryoo extends CrudRepository<Produto,Long>{
    @Query(value = "SELECT * FROM software_autoservico where usuario=?1 and senha=?2 and status = 'true'", nativeQuery = true)
    Optional<Produto> Login(String usuario ,  String senha);
    @Query(value = "SELECT * FROM software_autoservico where usuario=?1",nativeQuery =true)
    Optional<Produto> consultarUsuarioJaCadastrado(String usuario);
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE software_autoservico SET vesao = ?1 WHERE software_autoservico.id = ?2", nativeQuery = true)
    void validarVersao(String versao, String id);
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "INSERT INTO software_autoservico (senha, status, usuario) VALUES (:senha, :status, :usuario)", nativeQuery = true)
    void post(@Param("senha") String senha, @Param("status") String status, @Param("usuario") String usuario);
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE software_autoservico SET dt_versao = ?1 WHERE software_autoservico.id = ?2", nativeQuery = true)
    void data(String data, String id);
    
    // 2023-05-19 15:42:25
    @Query(value = "SELECT * FROM software_autoservico where vesao > ?1", nativeQuery = true)
    List<Produto> versao(String versao);
    @Query(value = "SELECT MAX(vesao) AS vesao,id,dt_versao,usuario,senha,status FROM software_autoservico WHERE vesao > ?1", nativeQuery = true)
    Optional<Produto> ver(String versao);
}
