package br.com.fiap.to;

import java.time.LocalDate;

public class PacienteTO {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String senha;

    public PacienteTO(String nome, String cpf, LocalDate dataNascimento, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getSenha() { return senha; }

    public void setNome(String nome) { this.nome = nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setSenha(String senha) { this.senha = senha; }
}
