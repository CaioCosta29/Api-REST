package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.consulta.request.DadosAgendamentoConsulta;
import med.voll.api.dto.consulta.request.DadosCancelamentoConsulta;
import med.voll.api.dto.consulta.response.DadosDetalhamentoConsulta;
import med.voll.api.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoConsulta> agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dadosAgendamentoConsulta, UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoConsulta dadosDetalhamentoConsulta = consultaService.fazerAgendamento(dadosAgendamentoConsulta);

        var uri = uriBuilder.path("/consulta/{id}").buildAndExpand(dadosDetalhamentoConsulta.id()).toUri();

        return ResponseEntity.created(uri).body(dadosDetalhamentoConsulta);
    }

    @DeleteMapping
    public ResponseEntity cancelarConsulta(@RequestBody @Valid DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        consultaService.cancelarConsulta(dadosCancelamentoConsulta);

        return ResponseEntity.noContent().build();
    }
}
