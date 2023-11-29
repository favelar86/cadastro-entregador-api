package br.com.ada.ifome.ifome.service;

import br.com.ada.ifome.ifome.exceptions.CpfInvalidoException;
import br.com.ada.ifome.ifome.exceptions.EntregadorInvalidoException;
import br.com.ada.ifome.ifome.model.Entregador;
import br.com.ada.ifome.ifome.repository.EntregadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntregadorService {

    private final EntregadorRepository entregadorRepository;

    public Entregador salvar(Entregador entregador) {
        if (entregador == null) {
            throw new EntregadorInvalidoException();
        }
        var isCpfValido = this.validaCpf(entregador.getCpf());
        if (!isCpfValido) {
            throw new CpfInvalidoException();
        }
        return entregadorRepository.save(entregador);
    }

    private boolean validaCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf.length() == 11;
    }
}
