package br.com.producer.producer;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import br.com.producer.dto.UsuarioDTO;

/**
 * 
 * Enilson Filho
 */
@Path("/api-producer")
public class UsuarioProducer {
    
    @Inject
    @Channel("usuario")
    private Emitter<UsuarioDTO> emitterUsuario;

    @POST
    @Path("/usuario")
    public Response publicarMensagem(UsuarioDTO usuarioDTO) {
        emitterUsuario.send(usuarioDTO);
        return Response.ok("Seu cadastro foi realizado").build();
    }

}
