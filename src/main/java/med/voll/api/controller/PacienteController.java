package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.paciente.request.DadosAtualizacaoPaciente;
import med.voll.api.dto.paciente.request.DadosCadastroPaciente;
import med.voll.api.dto.paciente.response.DadosDetalhamentoPaciente;
import med.voll.api.dto.paciente.response.DadosListagemPaciente;
import med.voll.api.entity.Paciente;
import med.voll.api.service.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dadosCadastroPaciente, UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoPaciente dadosDetalhamentoPaciente = pacienteService.cadastrarPaciente(dadosCadastroPaciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(dadosDetalhamentoPaciente.id()).toUri();
        
        return ResponseEntity.created(uri).body(dadosDetalhamentoPaciente);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = pacienteService.obterTodosOsPacientes(paginacao);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> detalharMedico(@PathVariable Long id) {
        DadosDetalhamentoPaciente dadosDetalhamentoPaciente = pacienteService.obterPacientePeloId(id);

        return ResponseEntity.ok(dadosDetalhamentoPaciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> atualizarPaciente(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizacaoPaciente dadosAtualizacaoPaciente
    ) {
        DadosDetalhamentoPaciente dadosDetalhamentoPaciente = pacienteService.atualizarPaciente(id, dadosAtualizacaoPaciente);

        return ResponseEntity.ok(dadosDetalhamentoPaciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPaciente(@PathVariable Long id) {
        pacienteService.excluirPaciente(id);

        return ResponseEntity.noContent().build();
    }
}
