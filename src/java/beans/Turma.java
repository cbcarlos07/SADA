/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Time;
import java.util.Date;



/**
 *
 * @author carlos.brito
 */
public class Turma  {
    private int turma_Codigo;
    private Time turma_Horario;
    private String turma_Observacao;
    private Date turma_Data;
    private String turma_Descricao;
    private Funcionario funcionario;
    private Atividade atividade;

    public int getTurma_Codigo() {
        return turma_Codigo;
    }

    public void setTurma_Codigo(int turma_Codigo) {
        this.turma_Codigo = turma_Codigo;
    }

 

    public String getTurma_Observacao() {
        return turma_Observacao;
    }

    public void setTurma_Observacao(String turma_Observacao) {
        this.turma_Observacao = turma_Observacao;
    }

    public Date getTurma_Data() {
        return turma_Data;
    }

    public void setTurma_Data(Date turma_Data) {
        this.turma_Data = turma_Data;
    }

    public String getTurma_Descricao() {
        return turma_Descricao;
    }

    public void setTurma_Descricao(String turma_Descricao) {
        this.turma_Descricao = turma_Descricao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public Time getTurma_Horario() {
        return turma_Horario;
    }

    public void setTurma_Horario(Time turma_Horario) {
        this.turma_Horario = turma_Horario;
    }
    
    
            
}
