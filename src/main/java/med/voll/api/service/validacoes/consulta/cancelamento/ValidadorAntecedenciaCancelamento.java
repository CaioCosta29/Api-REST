package med.voll.api.service.validacoes.consulta.cancelamento;

import med.voll.api.dto.consulta.request.DadosCancelamentoConsulta;
import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorAntecedenciaCancelamento implements ValidadorCancelamentoConsulta {
    private final ConsultaRepository consultaRepository;

    public ValidadorAntecedenciaCancelamento(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Override
    public void validar(DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        var consulta = consultaRepository.getReferenceById(dadosCancelamentoConsulta.idConsulta());
        var dataAtual = LocalDateTime.now();
        var dataConsulta = consulta.getData();

        var diferenca = Duration.between(dataAtual, dataConsulta).toHours();

        if (diferenca < 24) {
            throw new ValidacaoException("Consulta só pode ser cancelada com antecedência mínima de 24 horas.");
        }
    }
}
