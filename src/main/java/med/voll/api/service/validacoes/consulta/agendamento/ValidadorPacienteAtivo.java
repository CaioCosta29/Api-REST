package med.voll.api.service.validacoes.consulta.agendamento;

import med.voll.api.dto.consulta.request.DadosAgendamentoConsulta;
import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.repository.PacienteRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {
    private final PacienteRepository pacienteRepository;

    public ValidadorPacienteAtivo(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        var paciente = pacienteRepository.getReferenceById(dadosAgendamentoConsulta.idPaciente());

        if (!paciente.getAtivo()) {
            throw new ValidacaoException("Esse paciente está inativo");
        }
    }
}
