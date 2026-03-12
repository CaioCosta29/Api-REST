package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.medico.request.DadosAtualizacaoMedico;
import med.voll.api.dto.medico.response.DadosDetalhamentoMedico;
import med.voll.api.dto.medico.response.DadosListagemMedico;
import med.voll.api.dto.medico.request.DadosCadastroMedico;
import med.voll.api.service.MedicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoMedico> cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico, UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoMedico dadosDetalhamentoMedico = medicoService.cadastrarMedico(dadosCadastroMedico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(dadosDetalhamentoMedico.id()).toUri();

        return ResponseEntity.created(uri).body(dadosDetalhamentoMedico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = medicoService.obterTodosOsMedicos(paginacao);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> detalharMedico(@PathVariable Long id) {
        DadosDetalhamentoMedico dadosDetalhamentoMedico = medicoService.obterMedicoPeloId(id);

        return ResponseEntity.ok(dadosDetalhamentoMedico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> atualizarMedico(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico) {

        DadosDetalhamentoMedico dadosDetalhamentoMedico = medicoService.atualizarMedico(id, dadosAtualizacaoMedico);

        return ResponseEntity.ok(dadosDetalhamentoMedico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMedico(@PathVariable Long id) {
        medicoService.excluirMedico(id);

        return ResponseEntity.noContent().build();
    }
}
