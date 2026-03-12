package med.voll.api.dto.consulta.request;

import jakarta.validation.constraints.NotNull;
import med.voll.api.entity.consulta.MotivoCancelamento;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo
) {
}
