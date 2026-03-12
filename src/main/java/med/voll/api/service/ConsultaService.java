package med.voll.api.service;

import med.voll.api.dto.consulta.request.DadosAgendamentoConsulta;
import med.voll.api.dto.consulta.request.DadosCancelamentoConsulta;
import med.voll.api.entity.consulta.Consulta;
import med.voll.api.entity.medico.Medico;
import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.service.validacoes.consulta.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.service.validacoes.consulta.cancelamento.ValidadorCancelamentoConsulta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final List<ValidadorAgendamentoDeConsulta> validadoresAgendamento;
    private final List<ValidadorCancelamentoConsulta> validadoresCancelamento;

    public ConsultaService(ConsultaRepository consultaRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository, List<ValidadorAgendamentoDeConsulta> validadoresAgendamento, List<ValidadorCancelamentoConsulta> validadoresCancelamento) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.validadoresAgendamento = validadoresAgendamento;
        this.validadoresCancelamento = validadoresCancelamento;
    }

    @Transactional
    public void fazerAgendamento(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        if (!pacienteRepository.existsById(dadosAgendamentoConsulta.idPaciente())) {
            throw new ValidacaoException("ID do paciente não existe");
        }

        if (dadosAgendamentoConsulta.idMedico() != null && !medicoRepository.existsById(dadosAgendamentoConsulta.idMedico())) {
            throw new ValidacaoException("ID do médico não existe");
        }

        validadoresAgendamento.forEach(validador -> validador.validar(dadosAgendamentoConsulta));

        var medico = escolherMedico(dadosAgendamentoConsulta);
        var paciente = pacienteRepository.getReferenceById(dadosAgendamentoConsulta.idPaciente());

        var consulta = new Consulta(medico, paciente, dadosAgendamentoConsulta.data());

        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        if (dadosAgendamentoConsulta.idMedico() != null) {
            return medicoRepository.getReferenceById(dadosAgendamentoConsulta.idMedico());
        }

        if (dadosAgendamentoConsulta.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido");
        }

        var medico = medicoRepository.escolherMedicoAleatorioLivreNaData(dadosAgendamentoConsulta.especialidade(), dadosAgendamentoConsulta.data());

        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data");
        }

        return medico;
    }

    @Transactional
    public void cancelarConsulta(DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        if (!consultaRepository.existsById(dadosCancelamentoConsulta.idConsulta())) {
            throw new ValidacaoException("Id da consulta não existe");
        }

        validadoresCancelamento.forEach(v -> v.validar(dadosCancelamentoConsulta));

        var consulta = consultaRepository.getReferenceById(dadosCancelamentoConsulta.idConsulta());

        consulta.cancelar(dadosCancelamentoConsulta.motivo());
    }


}
