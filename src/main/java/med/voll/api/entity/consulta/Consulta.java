package med.voll.api.entity.consulta;

import jakarta.persistence.*;
import med.voll.api.entity.Paciente;
import med.voll.api.entity.medico.Medico;

import java.time.LocalDateTime;

@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private LocalDateTime data;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivo;

    public Consulta() {
    }

    public Consulta(Medico medico, Paciente paciente, LocalDateTime data) {
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDateTime getData() {
        return data;
    }

    public MotivoCancelamento getMotivo() {
        return motivo;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", medico=" + medico +
                ", paciente=" + paciente +
                ", data=" + data +
                '}';
    }

    public void cancelar(MotivoCancelamento motivoCancelamento) {
        this.motivo = motivoCancelamento;
    }
}
