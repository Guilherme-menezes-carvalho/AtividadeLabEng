package org.example.controller;

import org.example.domain.UsuarioDTO;
import org.example.domain.UsuarioEntity;
import org.example.domain.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioEntity> consultaPorId(@PathVariable Long id){
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioEntity> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO){

        UsuarioEntity novoUsuario = repository.save(usuarioDTO.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioEntity> atualizarUsuario(@PathVariable Long id,
                                                          @RequestBody UsuarioDTO usuarioDTO){

        Optional<UsuarioEntity> usuarioExistente = repository.findById(id);

        if (usuarioExistente.isPresent()) {
            UsuarioEntity usuario = usuarioExistente.get();
            usuario.setNome(usuarioDTO.getNome());
            usuario.setSenha(usuarioDTO.getSenha());
            repository.save(usuario);
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
