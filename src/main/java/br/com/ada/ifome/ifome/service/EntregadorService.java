package br.com.ada.ifome.ifome.service;

import br.com.ada.ifome.ifome.exceptions.CpfExistenteException;
import br.com.ada.ifome.ifome.exceptions.CpfInvalidoException;
import br.com.ada.ifome.ifome.exceptions.EntregadorInvalidoException;
import br.com.ada.ifome.ifome.exceptions.EntregadorNaoEncontradoException;
import br.com.ada.ifome.ifome.model.Entregador;
import br.com.ada.ifome.ifome.repository.EnderecoRepository;
import br.com.ada.ifome.ifome.repository.EntregadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EntregadorService {

    private final EntregadorRepository entregadorRepository;
    private final EnderecoRepository enderecoRepository;

    public Entregador salvar(Entregador entregador) {

        if (entregador == null) {
            throw new EntregadorInvalidoException();
        }

        var isCpfValido = this.validaCpf(entregador.getCpf());
        if (!isCpfValido) {
            throw new CpfInvalidoException();
        }

        var entregadorEncontrado = this.entregadorRepository.findByCpf(entregador.getCpf());
        if (entregadorEncontrado.isPresent()) {
            throw new CpfExistenteException();
        }

        var endereco = enderecoRepository.save(entregador.getEndereco());
        entregador.setEndereco(endereco);

        return entregadorRepository.save(entregador);
    }

    public Entregador remover(Long id) {
        var entregador = entregadorRepository.findById(id);
        if (entregador.isEmpty()) {
            throw new EntregadorNaoEncontradoException();
        }
        entregadorRepository.deleteById(id);
        return entregador.get();
    }

    private boolean validaCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf.length() == 11;
    }

    private boolean validaNumeroConta(String conta) {
        conta = conta.replaceAll("[^0-9]", "");
        return conta.length() == 20;
    }
}
