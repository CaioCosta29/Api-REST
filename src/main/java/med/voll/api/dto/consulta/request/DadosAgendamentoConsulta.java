package med.voll.api.dto.consulta.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.entity.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade


) {
}
