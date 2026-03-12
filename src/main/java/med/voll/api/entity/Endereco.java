package med.voll.api.entity;

import jakarta.persistence.Embeddable;
import med.voll.api.dto.endereco.DadosEndereco;

@Embeddable
public class Endereco {

     private String logradouro;
     private String bairro;
     private String cep;
     private String numero;
     private String complemento;
     private String cidade;
     private String uf;

    protected Endereco() {
    }

    public Endereco(String logradouro, String uf, String cidade, String complemento, String numero, String cep, String bairro) {
        this.logradouro = logradouro;
        this.uf = uf;
        this.cidade = cidade;
        this.complemento = complemento;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public void atualizarInformacoes(DadosEndereco dadosEndereco) {
        if (dadosEndereco.logradouro() != null) {
            this.logradouro = dadosEndereco.logradouro();
        }
        if (dadosEndereco.bairro() != null) {
            this.bairro = dadosEndereco.bairro();
        }
        if (dadosEndereco.cep() != null) {
            this.cep = dadosEndereco.cep();
        }
        if (dadosEndereco.uf() != null) {
            this.uf = dadosEndereco.uf();
        }
        if (dadosEndereco.cidade() != null) {
            this.cidade = dadosEndereco.cidade();
        }
        if (dadosEndereco.numero() != null) {
            this.numero = dadosEndereco.numero();
        }
        if (dadosEndereco.complemento() != null) {
            this.complemento = dadosEndereco.complemento();
        }
    }

}
