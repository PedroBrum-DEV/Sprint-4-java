package br.com.fiap.bo;

import br.com.fiap.dao.PacienteDAO;
import br.com.fiap.to.PacienteTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteBO {

    private PacienteDAO pacienteDAO;

    public ArrayList<PacienteTO> findAll() throws SQLException, ClassNotFoundException {
        pacienteDAO= new PacienteDAO();

        return pacienteDAO.findAll();
    }

    public PacienteBO() throws SQLException, ClassNotFoundException {
        this.pacienteDAO = new PacienteDAO();
    }

    // Cadastrar paciente
    public void cadastrarPaciente(PacienteTO paciente) throws SQLException {
        pacienteDAO.cadastrarPaciente(paciente);
    }

    // Login
//    public PacienteTO login(String cpf, String senha) throws SQLException {
//        PacienteTO p = pacienteDAO.buscarPaciente(cpf);
//        if (p != null && p.getSenha().equals(senha)) {
//            return p;
//        }
//        return null;
//    }

    // Listar pacientes
    public List<PacienteTO> listarPacientes() throws SQLException {
        return pacienteDAO.findAll();
    }
}
