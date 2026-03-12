package med.voll.api.entity.medico;

import jakarta.persistence.*;
import med.voll.api.dto.medico.request.DadosAtualizacaoMedico;
import med.voll.api.entity.Endereco;

@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    protected Medico() {

    }

    public Medico(String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.crm = crm;
        this.ativo = true;
        this.especialidade = especialidade;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCrm() {
        return crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dadosAtualizacaoMedico) {
        if (dadosAtualizacaoMedico.nome() != null) {
            this.nome = dadosAtualizacaoMedico.nome();
        }

        if (dadosAtualizacaoMedico.telefone() != null) {
            this.telefone = dadosAtualizacaoMedico.telefone();
        }

        if (dadosAtualizacaoMedico.endereco() != null) {
            this.endereco.atualizarInformacoes(dadosAtualizacaoMedico.endereco());
        }
    }

    public void desativarMedico() {
        this.ativo = false;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", crm='" + crm + '\'' +
                ", ativo=" + ativo +
                ", especialidade=" + especialidade +
                ", endereco=" + endereco +
                '}';
    }
}
