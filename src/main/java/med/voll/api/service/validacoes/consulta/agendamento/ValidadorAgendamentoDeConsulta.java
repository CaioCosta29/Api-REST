package med.voll.api.service.validacoes.consulta.agendamento;

import med.voll.api.dto.consulta.request.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {
    void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta);
}
