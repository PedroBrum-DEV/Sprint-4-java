package br.com.fiap.bo;

import br.com.fiap.dao.MedicoDAO;
import br.com.fiap.to.MedicoTO;

import java.sql.SQLException;
import java.util.List;

public class MedicoBO {
    private MedicoDAO medicoDAO;

    public MedicoBO() throws SQLException, ClassNotFoundException {
        medicoDAO = new MedicoDAO();
    }

    // CREATE
    public void cadastrarMedico(MedicoTO medico) throws SQLException {
        medicoDAO.cadastrarMedico(medico);
    }

    // READ - Listar todos
    public List<MedicoTO> listarMedicos() throws SQLException {
        return medicoDAO.listarMedicos();
    }

    // READ - Buscar por CRM
    public MedicoTO buscarPorCRM(String crm) throws SQLException {
        return medicoDAO.buscarPorCRM(crm);
    }

    // UPDATE
    public boolean atualizarMedico(MedicoTO medico) throws SQLException {
        return medicoDAO.atualizarMedico(medico);
    }

    // DELETE
    public boolean deletarMedico(String crm) throws SQLException {
        return medicoDAO.deletarMedico(crm);
    }

    // LISTAR POR ESPECIALIDADE
    public List<MedicoTO> listarMedicosPorEspecialidade(String especialidade) throws SQLException {
        return medicoDAO.listarMedicos().stream()
                .filter(m -> m.getEspecialidade().equalsIgnoreCase(especialidade))
                .toList();
    }
}
