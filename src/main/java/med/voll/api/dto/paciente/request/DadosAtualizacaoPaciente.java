package med.voll.api.dto.paciente.request;

import med.voll.api.dto.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        String nome,
        String telefone,
        DadosEndereco dadosEndereco
) {
}
