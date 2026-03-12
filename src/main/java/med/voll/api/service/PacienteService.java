package med.voll.api.service;

import med.voll.api.dto.paciente.request.DadosAtualizacaoPaciente;
import med.voll.api.dto.paciente.request.DadosCadastroPaciente;
import med.voll.api.dto.paciente.response.DadosDetalhamentoPaciente;
import med.voll.api.dto.paciente.response.DadosListagemPaciente;
import med.voll.api.entity.Paciente;
import med.voll.api.mappers.PacienteMapper;
import med.voll.api.repository.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteService(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }

    @Transactional
    public DadosDetalhamentoPaciente cadastrarPaciente(DadosCadastroPaciente dadosCadastroPaciente) {
        Paciente paciente = pacienteMapper.deDtoParaEntidade(dadosCadastroPaciente);
        pacienteRepository.save(paciente);

        return pacienteMapper.deEntidadeParaDetalhamentoDto(paciente);
    }

    @Transactional(readOnly = true)
    public Page<DadosListagemPaciente> obterTodosOsPacientes(Pageable paginacao) {
        Page<DadosListagemPaciente> pacientes = pacienteRepository.findAllByAtivoTrue(paginacao).map(pacienteMapper::deEntidadeParaDto);

        return pacientes;
    }

    @Transactional(readOnly = true)
    public DadosDetalhamentoPaciente obterPacientePeloId(Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);

        return pacienteMapper.deEntidadeParaDetalhamentoDto(paciente);
    }

    @Transactional
    public DadosDetalhamentoPaciente atualizarPaciente(Long id, DadosAtualizacaoPaciente dadosAtualizacaoPaciente) {
        var paciente = pacienteRepository.getReferenceById(id);

        paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

        return pacienteMapper.deEntidadeParaDetalhamentoDto(paciente);
    }

    @Transactional
    public void excluirPaciente(Long id) {
        var paciente = pacienteRepository.getReferenceById(id);

        paciente.desativarPaciente();
    }
}
