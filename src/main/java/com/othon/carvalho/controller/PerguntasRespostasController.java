package com.othon.carvalho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.othon.carvalho.model.app.Produto;
import com.othon.carvalho.repository.app.Repositoryo;
import com.othon.carvalho.repository.auth.Repositoryoo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/java")
@CrossOrigin("*")
public class PerguntasRespostasController {
	@Autowired
	private Repositoryo consulta;
	@Autowired
	private Repositoryoo login;

	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<?> findCode(@PathVariable String codigo) {
		Optional<Produto> list = consulta.findAll(codigo);
		return new ResponseEntity<>(list, HttpStatus.valueOf(202));

	}

	@GetMapping("/codauxiliar/{codigo}")
	public ResponseEntity<?> codauxiliar(@PathVariable String codigo) {
		Optional<Produto> list = consulta.find(codigo);
		return new ResponseEntity<>(list, HttpStatus.valueOf(202));

	}
    // {
  
    //     "usuario": "marcos",
    //     "status": "true",
    //     "senha": "1541sadad",
    //     "dt_versao": "2023-03-02",
    //     "vesao": 1
        
    //   }
    @GetMapping("/login/{usuario}/{senha}")
	public ResponseEntity<?> Login(@PathVariable String usuario,@PathVariable String senha) {
		Optional<com.othon.carvalho.model.auth.Produto> list = login.Login(usuario, senha);
		return new ResponseEntity<>(list, HttpStatus.valueOf(202));

	}
    @GetMapping("/validarCadastro/{usuario}")
	public ResponseEntity<?> Login(@PathVariable String usuario) {
		Optional<com.othon.carvalho.model.auth.Produto> list = login.consultarUsuarioJaCadastrado(usuario);
		return new ResponseEntity<>(list, HttpStatus.valueOf(202));

	}

	@PostMapping("/usuario")
    public ProdutoFormRequest salvarr (@RequestBody ProdutoFormRequest loginss){
        com.othon.carvalho.model.auth.Produto entidade=loginss.toModel();
		login.save(entidade);

        return ProdutoFormRequest.fromModel(entidade);
    }
    @GetMapping("/tudo")
    public Iterable<com.othon.carvalho.model.auth.Produto> obterProduto(){
        return login.findAll();
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ProdutoFormRequest> GetById(@PathVariable Long id){
        Optional<com.othon.carvalho.model.auth.Produto> produtoExistente= login.findById(id);
        if(produtoExistente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var produto= produtoExistente.map(ProdutoFormRequest::fromModel).get();
        return ResponseEntity.ok(produto);
    }
    //void esta retornando um responseentity sem corpo
    @PutMapping("/put/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody ProdutoFormRequest produto){
        Optional<com.othon.carvalho.model.auth.Produto> produtoExistente=login.findById(id);
        if(produtoExistente.isEmpty()){
       //public void =>     throw new ResponseStatusException(HttpStatus.NOT_FOUND);
         return ResponseEntity.notFound().build();
        }
        com.othon.carvalho.model.auth.Produto entidade=produto.toModel();
        entidade.setId(id);
        login.save(entidade);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        Optional<com.othon.carvalho.model.auth.Produto> produtoExistente=login.findById(id);
        if(produtoExistente.isEmpty()){
            return ResponseEntity.notFound().build(); 
        }
        login.delete((produtoExistente.get()));
        return ResponseEntity.noContent().build();

    }
}