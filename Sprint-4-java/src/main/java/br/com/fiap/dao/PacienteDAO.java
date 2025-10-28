package br.com.fiap.dao;

import br.com.fiap.to.PacienteTO;

import java.sql.*;
import java.util.ArrayList;

public class PacienteDAO {
    private Connection conn = ConnectionFactory.getConnection();

    public PacienteDAO() throws SQLException, ClassNotFoundException {}

    // CREATE
    public void cadastrarPaciente(PacienteTO p) throws SQLException {
        String sql = "INSERT INTO paciente (cpf, nome, data_nascimento, senha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getCpf());
            stmt.setString(2, p.getNome());
            if (p.getDataNascimento() != null)
                stmt.setDate(3, Date.valueOf(p.getDataNascimento()));
            else
                stmt.setNull(3, Types.DATE);
            stmt.setString(4, p.getSenha());
            stmt.executeUpdate();
        }
    }

    // READ - listar todos
    public ArrayList<PacienteTO> findAll() throws SQLException {
        ArrayList<PacienteTO> lista = new ArrayList<>();
        String sql = "SELECT cpf, nome, data_nascimento, senha FROM paciente";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new PacienteTO(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("senha")
                ));
            }
        }
        return lista;
    }

    // READ - buscar por CPF
    public PacienteTO buscarPorCPF(String cpf) throws SQLException {
        String sql = "SELECT * FROM paciente WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PacienteTO(
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getDate("data_nascimento").toLocalDate(),
                            rs.getString("senha")
                    );
                }
            }
        }
        return null;
    }

    // UPDATE
    public boolean atualizarPaciente(PacienteTO p) throws SQLException {
        String sql = "UPDATE paciente SET nome = ?, data_nascimento = ?, senha = ? WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setDate(2, Date.valueOf(p.getDataNascimento()));
            stmt.setString(3, p.getSenha());
            stmt.setString(4, p.getCpf());
            return stmt.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean deletarPaciente(String cpf) throws SQLException {
        String sql = "DELETE FROM paciente WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            return stmt.executeUpdate() > 0;
        }
    }
}
