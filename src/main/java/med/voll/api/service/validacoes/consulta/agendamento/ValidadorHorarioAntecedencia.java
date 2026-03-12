package med.voll.api.service.validacoes.consulta.agendamento;

import med.voll.api.dto.consulta.request.DadosAgendamentoConsulta;
import med.voll.api.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    @Override
    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        var dataConsulta = dadosAgendamentoConsulta.data();
        var dataAtual = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(dataAtual, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }

    }
}
