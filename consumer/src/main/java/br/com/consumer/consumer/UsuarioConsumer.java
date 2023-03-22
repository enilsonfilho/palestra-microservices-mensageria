package br.com.consumer.consumer;

import java.util.concurrent.CompletableFuture;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import br.com.consumer.dto.UsuarioDTO;
import br.com.consumer.entity.Usuario;

/**
 * 
 * Enilson Filho
 */
@ApplicationScoped
public class UsuarioConsumer {
    
    @Incoming("usuario")
    public void readUsuarios(UsuarioDTO usuarioDTO) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            salvarUsuario(usuarioDTO);
        });

        future.whenComplete((result, error) ->{
            if (error == null) {
                System.out.println("Usuário " + usuarioDTO.getNome() + " foi cadastro no banco de dados com sucesso!");
            } else {
                System.out.println("Problemas ao cadastrar usuário: Error: " + error.getMessage());
            }
        });
    }

    @Transactional
    public void salvarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setEstado(usuarioDTO.getEstado());
        usuario.setCidade(usuarioDTO.getCidade());
        usuario.setBairro(usuarioDTO.getBairro());
        Usuario.persist(usuario);
    }

}
