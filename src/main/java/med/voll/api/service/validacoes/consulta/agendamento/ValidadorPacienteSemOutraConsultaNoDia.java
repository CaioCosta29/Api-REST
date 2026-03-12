package med.voll.api.service.validacoes.consulta.agendamento;

import med.voll.api.dto.consulta.request.DadosAgendamentoConsulta;
import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {
    private final ConsultaRepository consultaRepository;

    public ValidadorPacienteSemOutraConsultaNoDia(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Override
    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        var primeiroHorario = dadosAgendamentoConsulta.data().withHour(7);
        var ultimoHorario  = dadosAgendamentoConsulta.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dadosAgendamentoConsulta.idPaciente(), primeiroHorario, ultimoHorario);

        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Este paciente não pode mais marcar consultas para hoje");
        }
    }
}
