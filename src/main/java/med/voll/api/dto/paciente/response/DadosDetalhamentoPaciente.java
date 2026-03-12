package med.voll.api.dto.paciente.response;

import med.voll.api.entity.Endereco;
import med.voll.api.entity.Paciente;

public record DadosDetalhamentoPaciente(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Boolean ativo,
        Endereco endereco
) {

    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getAtivo(), paciente.getEndereco());
    }
}
