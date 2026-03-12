package med.voll.api.service.validacoes.consulta.agendamento;

import med.voll.api.dto.consulta.request.DadosAgendamentoConsulta;
import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsulta implements ValidadorAgendamentoDeConsulta {
    private final ConsultaRepository consultaRepository;

    public ValidadorMedicoComOutraConsulta(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Override
    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndData(dadosAgendamentoConsulta.idMedico(), dadosAgendamentoConsulta.data());

        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Este médico possui uma consulta neste horário");
        }
    }
}
