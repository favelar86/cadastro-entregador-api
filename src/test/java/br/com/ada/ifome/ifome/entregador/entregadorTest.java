package br.com.ada.ifome.ifome.entregador;

import br.com.ada.ifome.ifome.exceptions.CpfInvalidoException;
import br.com.ada.ifome.ifome.exceptions.EntregadorInvalidoException;
import br.com.ada.ifome.ifome.model.Entregador;
import br.com.ada.ifome.ifome.repository.EntregadorRepository;
import br.com.ada.ifome.ifome.service.EntregadorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class entregadorTest {

    @Mock
    private EntregadorRepository entregadorRepository;

    @InjectMocks
    private EntregadorService entregadorService;

    @Test
    public void testaEntregadorNull() {
        assertThrows(EntregadorInvalidoException.class, () -> entregadorService.salvar(null));
    }

    @Test
    public void entregadorCpfInvalidoComLetra() {
        var entregador = new Entregador();
        entregador.setCpf("1234567891e");
        assertThrows(CpfInvalidoException.class, () -> entregadorService.salvar(entregador));
    }

    @Test
    public void entregadorCpfInvalidoCom12Digitos() {
        var entregador = new Entregador();
        entregador.setCpf("123456789123");
        assertThrows(CpfInvalidoException.class, () -> entregadorService.salvar(entregador));
    }

    @Test
    public void entregadorComCpfValido() {
        var entregador = new Entregador();
        entregador.setCpf("04455566633");
        when(entregadorRepository.save(any())).thenReturn(entregador);
        var entregadorSalvo = entregadorService.salvar(entregador);

        assertNotNull(entregadorSalvo);
        verify(entregadorRepository, Mockito.times(1)).save(entregador);
    }
}
