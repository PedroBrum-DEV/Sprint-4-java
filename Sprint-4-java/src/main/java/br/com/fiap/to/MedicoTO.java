package br.com.fiap.to;

import java.io.Serializable;

public class MedicoTO implements Serializable {
    private String crm;
    private String nome;
    private String especialidade;
    private String senha;

    public MedicoTO(String crm, String nome, String especialidade, String senha) {
        this.crm = crm;
        this.nome = nome;
        this.especialidade = especialidade;
        this.senha = senha;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
