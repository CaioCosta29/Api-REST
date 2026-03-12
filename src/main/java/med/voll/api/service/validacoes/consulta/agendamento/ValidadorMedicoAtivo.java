package med.voll.api.service.validacoes.consulta.agendamento;

import med.voll.api.dto.consulta.request.DadosAgendamentoConsulta;
import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.repository.MedicoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {
    private final MedicoRepository medicoRepository;

    public ValidadorMedicoAtivo(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        if (dadosAgendamentoConsulta.idMedico() == null) {
            return;
        }

        var medico = medicoRepository.getReferenceById(dadosAgendamentoConsulta.idMedico());

        if (!medico.getAtivo()) {
            throw new ValidacaoException("Esse médico está inativo");
        }
    }
}
