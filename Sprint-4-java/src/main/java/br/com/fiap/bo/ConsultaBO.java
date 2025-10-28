package br.com.fiap.bo;

import br.com.fiap.to.ConsultaTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaBO {

    private List<ConsultaTO> consultas = new ArrayList<>();

    // Validar se o horário da consulta é futuro
    public boolean validarDataHora(ConsultaTO consulta) {
        LocalDateTime agora = LocalDateTime.now();
        return consulta.getDataHora() != null && consulta.getDataHora().isAfter(agora);
    }

    // Validar status (ex: "Agendada", "Concluída", "Cancelada")
    public boolean validarStatus(ConsultaTO consulta) {
        String status = consulta.getStatus();
        return status != null && (status.equalsIgnoreCase("Agendada") ||
                status.equalsIgnoreCase("Concluída") ||
                status.equalsIgnoreCase("Cancelada"));
    }

    // Cadastrar consulta
    public boolean cadastrarConsulta(ConsultaTO consulta) {
        if(validarDataHora(consulta) && validarStatus(consulta)) {
            consultas.add(consulta);
            System.out.println("Consulta cadastrada com sucesso! ID: " + consulta.getId());
            return true;
        } else {
            System.out.println("Falha ao cadastrar consulta. Verifique data/hora ou status.");
            return false;
        }
    }

    // Listar consultas
    public List<ConsultaTO> listarConsultas(String cpf) {
        return consultas;
    }

    // Cancelar consulta
    public boolean cancelarConsulta(int id) {
        for(ConsultaTO c : consultas) {
            if(c.getId() == id) {
                c.setStatus("Cancelada");
                System.out.println("Consulta ID " + id + " cancelada.");
                return true;
            }
        }
        System.out.println("Consulta ID " + id + " não encontrada.");
        return false;
    }
}

