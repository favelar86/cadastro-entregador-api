package br.com.ada.ifome.ifome.entregador;

import br.com.ada.ifome.ifome.controller.EntregadorController;
import br.com.ada.ifome.ifome.model.Endereco;
import br.com.ada.ifome.ifome.model.Entregador;
import br.com.ada.ifome.ifome.service.EntregadorService;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class EntregadorControllerTest {

    @InjectMocks
    private EntregadorController entregadorController;

    @Mock
    private EntregadorService entregadorService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(entregadorController).build();
    }

    @Test
    public void cadastrarEntregador() throws Exception {
        Entregador entregador = new Entregador();
        entregador.setId(3L);
        entregador.setCpf("11111111111");
        entregador.setNome("Fabiano");
        entregador.setEmail("fabiano@mail.com");
        entregador.setTamanhoCamisa("M");

        var endereco = new Endereco();
        endereco.setId(3L);
        endereco.setLogradouro("São Paulo-SP");
        endereco.setNumero(300L);
        endereco.setCep("99999999");
        endereco.setComplemento("apartamento");

        entregador.setEndereco(endereco);

        Mockito.when(entregadorService.salvar(any())).thenReturn(entregador);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/entregadores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(entregador)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
