package br.com.fiap.dao;

import br.com.fiap.to.ConsultaTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    private Connection conn = ConnectionFactory.getConnection();

    public ConsultaDAO() throws SQLException, ClassNotFoundException {}

    // CREATE
    public void cadastrarConsulta(ConsultaTO c) throws SQLException {
        String sql = "INSERT INTO consulta (cpf_paciente, crm_medico, data_hora, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getCpfPaciente());
            stmt.setString(2, c.getCrmMedico());
            stmt.setTimestamp(3, Timestamp.valueOf(c.getDataHora()));
            stmt.setString(4, c.getStatus());
            stmt.executeUpdate();
        }
    }

    // READ - listar todos
    public List<ConsultaTO> listarConsultas() throws SQLException {
        List<ConsultaTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM consulta";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new ConsultaTO(
                        rs.getInt("id"),
                        rs.getString("cpf_paciente"),
                        rs.getString("crm_medico"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        rs.getString("status")
                ));
            }
        }
        return lista;
    }

    // READ - filtrar por paciente
    public List<ConsultaTO> listarConsultasPaciente(String cpfPaciente) throws SQLException {
        List<ConsultaTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM consulta WHERE cpf_paciente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpfPaciente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new ConsultaTO(
                            rs.getInt("id"),
                            rs.getString("cpf_paciente"),
                            rs.getString("crm_medico"),
                            rs.getTimestamp("data_hora").toLocalDateTime(),
                            rs.getString("status")
                    ));
                }
            }
        }
        return lista;
    }

    // READ - filtrar por médico
    public List<ConsultaTO> listarConsultasMedico(String crmMedico) throws SQLException {
        List<ConsultaTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM consulta WHERE crm_medico = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, crmMedico);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new ConsultaTO(
                            rs.getInt("id"),
                            rs.getString("cpf_paciente"),
                            rs.getString("crm_medico"),
                            rs.getTimestamp("data_hora").toLocalDateTime(),
                            rs.getString("status")
                    ));
                }
            }
        }
        return lista;
    }

    // UPDATE - status da consulta
    public boolean atualizarConsulta(ConsultaTO c) throws SQLException {
        String sql = "UPDATE consulta SET cpf_paciente = ?, crm_medico = ?, data_hora = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getCpfPaciente());
            stmt.setString(2, c.getCrmMedico());
            stmt.setTimestamp(3, Timestamp.valueOf(c.getDataHora()));
            stmt.setString(4, c.getStatus());
            stmt.setInt(5, c.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean deletarConsulta(int id) throws SQLException {
        String sql = "DELETE FROM consulta WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    // CANCELAR (atualiza status)
    public boolean cancelarConsulta(int id) throws SQLException {
        String sql = "UPDATE consulta SET status = 'cancelada' WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}
