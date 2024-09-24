
import org.example.Main;
import org.example.controller.UsuarioController;
import org.example.domain.UsuarioDTO;
import org.example.domain.UsuarioEntity;
import org.example.domain.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = Main.class)
@Transactional
public class UsuarioControllerTest {


    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testCadastrarUsuario() {
        // Cria um novo usuário
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("guilherme");
        usuarioDTO.setSenha("888");

        // Chama o método do controlador diretamente
        UsuarioEntity novoUsuario = usuarioController.cadastrarUsuario(usuarioDTO).getBody();

        // Verifica se o usuário foi salvo corretamente
        assertEquals("guilherme", novoUsuario.getNome());
        assertEquals("888", novoUsuario.getSenha());

        // Verifica se o usuário está no banco de dados
        assertEquals(1, usuarioRepository.count()); // Deve haver 1 usuário
    }

    @Test
    public void testConsultaPorId() {
        // Cria um novo usuário
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("guilherme");
        usuarioDTO.setSenha("888");

        // Salva o usuário no repositório
        UsuarioEntity novoUsuario = usuarioController.cadastrarUsuario(usuarioDTO).getBody();

        // Obtém o ID do usuário recém-criado
        Long usuarioId = novoUsuario.getId();

        // Chama o método de consulta por ID
        ResponseEntity<UsuarioEntity> response = usuarioController.consultaPorId(usuarioId);

        // Verifica se a resposta está OK e se os dados do usuário estão corretos
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("guilherme", response.getBody().getNome());
        assertEquals("8882", response.getBody().getSenha());
    }

    @Test
    public void testAtualizarUsuario() {
        // Primeiro, cria um usuário para poder atualizá-lo depois
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("guilherme");
        usuarioDTO.setSenha("888");

        // Salva o usuário
        UsuarioEntity novoUsuario = usuarioController.cadastrarUsuario(usuarioDTO).getBody();
        assertNotNull(novoUsuario);
        Long usuarioId = novoUsuario.getId();

        // Agora atualizamos o usuário
        UsuarioDTO usuarioAtualizadoDTO = new UsuarioDTO();
        usuarioAtualizadoDTO.setNome("guilherme atualizado");
        usuarioAtualizadoDTO.setSenha("999");

        UsuarioEntity usuarioAtualizado = usuarioController.atualizarUsuario(usuarioId, usuarioAtualizadoDTO).getBody();

        // Verifica se o nome e a senha foram atualizados corretamente
        assertNotNull(usuarioAtualizado);
        assertEquals("guilherme atualizado", usuarioAtualizado.getNome());
        assertEquals("999", usuarioAtualizado.getSenha());

        // Verifica se ainda há apenas um usuário no repositório
        assertEquals(1, usuarioRepository.count());

    }
}
