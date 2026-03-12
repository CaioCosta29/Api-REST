package med.voll.api.dto.medico.response;

import med.voll.api.entity.Endereco;
import med.voll.api.entity.medico.Especialidade;
import med.voll.api.entity.medico.Medico;

public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Boolean ativo,
        Especialidade especialidade,
        Endereco endereco

) {
    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getAtivo(), medico.getEspecialidade(), medico.getEndereco());
    }
}
