package br.com.fiap.to;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDateTime;

public class ConsultaTO {
    private int id;
    private String cpfPaciente;
    private String crmMedico;
    @FutureOrPresent
    private LocalDateTime dataHora;
    private String status;

    public ConsultaTO() {}

    public ConsultaTO(int id, String cpfPaciente, String crmMedico, LocalDateTime dataHora, String status) {
        this.id = id;
        this.cpfPaciente = cpfPaciente;
        this.crmMedico = crmMedico;
        this.dataHora = dataHora;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCpfPaciente() { return cpfPaciente; }
    public void setCpfPaciente(String cpfPaciente) { this.cpfPaciente = cpfPaciente; }

    public String getCrmMedico() { return crmMedico; }
    public void setCrmMedico(String crmMedico) { this.crmMedico = crmMedico; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
