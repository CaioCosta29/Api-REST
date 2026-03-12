package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.consulta.request.DadosAgendamentoConsulta;
import med.voll.api.dto.consulta.request.DadosCancelamentoConsulta;
import med.voll.api.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<Void> agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        consultaService.fazerAgendamento(dadosAgendamentoConsulta);
        System.out.println(dadosAgendamentoConsulta);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity cancelarConsulta(@RequestBody @Valid DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        consultaService.cancelarConsulta(dadosCancelamentoConsulta);

        return ResponseEntity.noContent().build();
    }
}
