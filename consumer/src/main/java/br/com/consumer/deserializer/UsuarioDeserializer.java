package br.com.consumer.deserializer;

import br.com.consumer.dto.UsuarioDTO;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

/**
 * 
 * Enilson Filho
 */
public class UsuarioDeserializer extends ObjectMapperDeserializer<UsuarioDTO> {
    public UsuarioDeserializer() {
        super(UsuarioDTO.class);
    }
}
