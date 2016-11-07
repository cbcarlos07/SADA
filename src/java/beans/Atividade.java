/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author carlos.brito
 */
public class Atividade extends Nivel{
    private int atividade_Codigo;
    private String atividade_Descricao;
    private String atividade_Nome;
    
    @Override
    public int getNivel_Codigo() {
        return nivel_Codigo;
    }

    @Override
    public void setNivel_Codigo(int nivel_Codigo) {
        this.nivel_Codigo = nivel_Codigo;
    }

    public int getAtividade_Codigo() {
        return atividade_Codigo;
    }

    public void setAtividade_Codigo(int atividade_Codigo) {
        this.atividade_Codigo = atividade_Codigo;
    }

    public String getAtividade_Descricao() {
        return atividade_Descricao;
    }

    public void setAtividade_Descricao(String atividade_Descricao) {
        this.atividade_Descricao = atividade_Descricao;
    }

    public String getAtividade_Nome() {
        return atividade_Nome;
    }

    public void setAtividade_Nome(String atividade_Nome) {
        this.atividade_Nome = atividade_Nome;
    }
    
    
    
}
