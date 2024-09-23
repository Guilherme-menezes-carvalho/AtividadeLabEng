package org.example.controller;

import org.example.domain.UsuarioDTO;
import org.example.domain.UsuarioEntity;
import org.example.domain.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioEntity> consultaPorId(@PathVariable Long id){
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> cadastrarUsuario(@RequestBody UsuarioDTO usuario){

        UsuarioEntity novoUsuario = repository.save(usuario.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
}

