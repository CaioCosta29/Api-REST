package med.voll.api.service;

import med.voll.api.dto.medico.request.DadosAtualizacaoMedico;
import med.voll.api.dto.medico.response.DadosDetalhamentoMedico;
import med.voll.api.dto.medico.response.DadosListagemMedico;
import med.voll.api.entity.medico.Medico;
import med.voll.api.dto.medico.request.DadosCadastroMedico;
import med.voll.api.mappers.MedicoMapper;
import med.voll.api.repository.MedicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;

    public MedicoService(MedicoRepository medicoRepository, MedicoMapper medicoMapper) {
        this.medicoRepository = medicoRepository;
        this.medicoMapper = medicoMapper;
    }

    @Transactional
    public DadosDetalhamentoMedico cadastrarMedico(DadosCadastroMedico dadosCadastroMedico) {
        Medico medico = medicoMapper.deDtoParaEntidade(dadosCadastroMedico);
        medicoRepository.save(medico);

        return medicoMapper.deEntidadeParaDetalhamentoDto(medico);
    }

    @Transactional(readOnly = true)
    public Page<DadosListagemMedico> obterTodosOsMedicos(Pageable paginacao) {
        Page<DadosListagemMedico> listaMedicos = medicoRepository.findAllByAtivoTrue(paginacao).map(medicoMapper::deEntidadeParaDto);
        return listaMedicos;
    }

    @Transactional(readOnly = true)
    public DadosDetalhamentoMedico obterMedicoPeloId(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);

        return medicoMapper.deEntidadeParaDetalhamentoDto(medico);
    }

    @Transactional
    public DadosDetalhamentoMedico atualizarMedico(Long id, DadosAtualizacaoMedico dadosAtualizacaoMedico) {
        var medico = medicoRepository.getReferenceById(id);

        medico.atualizarInformacoes(dadosAtualizacaoMedico);

        return medicoMapper.deEntidadeParaDetalhamentoDto(medico);
    }

    @Transactional
    public void excluirMedico(Long id) {
        var medico = medicoRepository.getReferenceById(id);

        medico.desativarMedico();
    }
}
