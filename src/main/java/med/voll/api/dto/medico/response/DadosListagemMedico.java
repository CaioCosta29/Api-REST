package med.voll.api.dto.medico.response;

import med.voll.api.entity.medico.Especialidade;
import med.voll.api.entity.medico.Medico;

public record DadosListagemMedico(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {

    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
