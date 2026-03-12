package med.voll.api.mappers;

import med.voll.api.dto.paciente.request.DadosCadastroPaciente;
import med.voll.api.dto.paciente.response.DadosDetalhamentoPaciente;
import med.voll.api.dto.paciente.response.DadosListagemPaciente;
import med.voll.api.entity.Endereco;
import med.voll.api.entity.Paciente;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {
    public Paciente deDtoParaEntidade(DadosCadastroPaciente dadosCadastroPaciente) {
        Paciente paciente = new Paciente(
                dadosCadastroPaciente.nome(),
                dadosCadastroPaciente.email(),
                dadosCadastroPaciente.telefone(),
                dadosCadastroPaciente.cpf(),
                new Endereco(
                        dadosCadastroPaciente.endereco().logradouro(),
                        dadosCadastroPaciente.endereco().uf(),
                        dadosCadastroPaciente.endereco().cidade(),
                        dadosCadastroPaciente.endereco().complemento(),
                        dadosCadastroPaciente.endereco().numero(),
                        dadosCadastroPaciente.endereco().cep(),
                        dadosCadastroPaciente.endereco().bairro()

                )
        );
        return paciente;

    }

    public DadosListagemPaciente deEntidadeParaDto(Paciente paciente) {
        DadosListagemPaciente dadosListagemPaciente = new DadosListagemPaciente(paciente);

        return dadosListagemPaciente;
    }

    public DadosDetalhamentoPaciente deEntidadeParaDetalhamentoDto(Paciente paciente) {
        return new DadosDetalhamentoPaciente(paciente);
    }
}
