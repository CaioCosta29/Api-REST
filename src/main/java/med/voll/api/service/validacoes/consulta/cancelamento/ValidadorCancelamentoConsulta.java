package med.voll.api.service.validacoes.consulta.cancelamento;

import med.voll.api.dto.consulta.request.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoConsulta {
    void validar(DadosCancelamentoConsulta dadosCancelamentoConsulta);
}
