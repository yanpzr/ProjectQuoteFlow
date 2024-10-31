package com.workspacepi.apiquoteflow.adapters.http.usuarios;

import com.workspacepi.apiquoteflow.application.usuarios.UsuarioAutenticationDTO;
import com.workspacepi.apiquoteflow.application.usuarios.UsuariosCreateCommand;
import com.workspacepi.apiquoteflow.application.usuarios.UsuariosUpdateCommand;
import com.workspacepi.apiquoteflow.domain.usuarios.Usuarios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    private final UsuariosHandler usuariosHandler;

    public UsuariosController(UsuariosHandler usuariosHandler) {
        this.usuariosHandler = usuariosHandler;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public ResponseEntity<List<Usuarios>> findAll() {
        return usuariosHandler.findAll();
    }

    @GetMapping(value = "/{id_usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Usuarios>> findById(@PathVariable String id_usuario) throws Exception {
        return usuariosHandler.findById(id_usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UsuarioAutenticationDTO data) {
        var usernamePassord = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassord);

        UsuarioAutenticationDTO response = new UsuarioAutenticationDTO(data.getEmail(), data.getSenha());
        return ResponseEntity.ok("Login realizado com sucesso!");
    }

    @PostMapping("/registrar")
    public ResponseEntity<Optional<Usuarios>> cadastrarUsuario(@RequestBody UsuariosCreateCommand usuarioCreateCommand) throws Exception {
        return usuariosHandler.cadastrarUsuario(usuarioCreateCommand);
    }

    @PutMapping("/{id_usuario}")
    public ResponseEntity<Optional<Usuarios>> modificarUsuario(@RequestBody UsuariosUpdateCommand usurioUpdateCommand, @PathVariable String id_usuario) throws Exception {
        return usuariosHandler.modificarUsuario(usurioUpdateCommand, id_usuario);
    }

    @DeleteMapping("/{id_usuario}")
    public ResponseEntity<String> deleteUsuarioById(@PathVariable String id_usuario) throws Exception {
        return usuariosHandler.deleteUsuarioById(id_usuario);
    }
}
