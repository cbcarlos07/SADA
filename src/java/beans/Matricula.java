/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;

/**
 *
 * @author Gysa
 */
public class Matricula {
    private int mat_Codigo;
    private Date mat_Data_Inicio;
    private Date mat_Date_Vencimento;
    private String mat_Status;
    private Aluno mat_Aluno;
    private Funcionario mat_Funcionario;

    public int getMat_Codigo() {
        return mat_Codigo;
    }

    public void setMat_Codigo(int mat_Codigo) {
        this.mat_Codigo = mat_Codigo;
    }

    public Date getMat_Data_Inicio() {
        return mat_Data_Inicio;
    }

    public void setMat_Data_Inicio(Date mat_Data_Inicio) {
        this.mat_Data_Inicio = mat_Data_Inicio;
    }

    public Date getMat_Date_Vencimento() {
        return mat_Date_Vencimento;
    }

    public void setMat_Date_Vencimento(Date mat_Date_Vencimento) {
        this.mat_Date_Vencimento = mat_Date_Vencimento;
    }

    public String getMat_Status() {
        return mat_Status;
    }

    public void setMat_Status(String mat_Status) {
        this.mat_Status = mat_Status;
    }

    public Aluno getMat_Aluno() {
        return mat_Aluno;
    }

    public void setMat_Aluno(Aluno mat_Aluno) {
        this.mat_Aluno = mat_Aluno;
    }

    public Funcionario getMat_Funcionario() {
        return mat_Funcionario;
    }

    public void setMat_Funcionario(Funcionario mat_Funcionario) {
        this.mat_Funcionario = mat_Funcionario;
    }
    
    
    
    
    
}
