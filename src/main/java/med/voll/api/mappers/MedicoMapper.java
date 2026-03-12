package med.voll.api.mappers;

import med.voll.api.dto.medico.request.DadosCadastroMedico;
import med.voll.api.dto.medico.response.DadosDetalhamentoMedico;
import med.voll.api.dto.medico.response.DadosListagemMedico;
import med.voll.api.entity.Endereco;
import med.voll.api.entity.medico.Medico;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {

    public Medico deDtoParaEntidade(DadosCadastroMedico dadosCadastroMedico) {
        Medico medico = new Medico(
                dadosCadastroMedico.nome(),
                dadosCadastroMedico.email(),
                dadosCadastroMedico.telefone(),
                dadosCadastroMedico.crm(),
                dadosCadastroMedico.especialidade(),
                new Endereco(
                        dadosCadastroMedico.endereco().logradouro(),
                        dadosCadastroMedico.endereco().uf(),
                        dadosCadastroMedico.endereco().cidade(),
                        dadosCadastroMedico.endereco().complemento(),
                        dadosCadastroMedico.endereco().numero(),
                        dadosCadastroMedico.endereco().cep(),
                        dadosCadastroMedico.endereco().bairro()
                )
                );
        return medico;
    }

    public DadosListagemMedico deEntidadeParaDto(Medico medico) {
        DadosListagemMedico dadosListagemMedico = new DadosListagemMedico(medico);

        return dadosListagemMedico;
    }

    public DadosDetalhamentoMedico deEntidadeParaDetalhamentoDto(Medico medico) {
        return new DadosDetalhamentoMedico(medico);
    }
}
