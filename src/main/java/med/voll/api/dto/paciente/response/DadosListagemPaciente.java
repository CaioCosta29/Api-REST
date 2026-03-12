package med.voll.api.dto.paciente.response;

import med.voll.api.entity.Paciente;

public record DadosListagemPaciente(
        Long id,
        String nome,
        String email,
        String cpf,
        Boolean ativo
) {
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getAtivo());
    }
}
