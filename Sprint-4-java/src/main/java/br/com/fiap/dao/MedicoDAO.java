package br.com.fiap.dao;

import br.com.fiap.to.MedicoTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {
    private Connection conn = ConnectionFactory.getConnection();

    public MedicoDAO() throws SQLException, ClassNotFoundException {}

    // CREATE - Cadastrar médico
    public void cadastrarMedico(MedicoTO medico) throws SQLException {
        String sql = "INSERT INTO medico (crm, nome, especialidade, senha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medico.getCrm());
            stmt.setString(2, medico.getNome());
            stmt.setString(3, medico.getEspecialidade());
            stmt.setString(4, medico.getSenha());
            stmt.executeUpdate();
        }
    }

    // READ - Listar todos os médicos
    public List<MedicoTO> listarMedicos() throws SQLException {
        List<MedicoTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM medico";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new MedicoTO(
                        rs.getString("crm"),
                        rs.getString("nome"),
                        rs.getString("especialidade"),
                        rs.getString("senha")
                ));
            }
        }
        return lista;
    }

    // READ - Buscar por CRM
    public MedicoTO buscarPorCRM(String crm) throws SQLException {
        String sql = "SELECT * FROM medico WHERE crm = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, crm);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MedicoTO(
                            rs.getString("crm"),
                            rs.getString("nome"),
                            rs.getString("especialidade"),
                            rs.getString("senha")
                    );
                }
            }
        }
        return null;
    }

    // UPDATE - Atualizar dados do médico
    public boolean atualizarMedico(MedicoTO medico) throws SQLException {
        String sql = "UPDATE medico SET nome = ?, especialidade = ?, senha = ? WHERE crm = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEspecialidade());
            stmt.setString(3, medico.getSenha());
            stmt.setString(4, medico.getCrm());
            return stmt.executeUpdate() > 0;
        }
    }

    // DELETE - Remover médico
    public boolean deletarMedico(String crm) throws SQLException {
        String sql = "DELETE FROM medico WHERE crm = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, crm);
            return stmt.executeUpdate() > 0;
        }
    }
}
