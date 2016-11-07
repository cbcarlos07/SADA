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
public class Nivel {
    int nivel_Codigo;
    private String nivel_Descricao;

    
    
    public int getNivel_Codigo() {
        return nivel_Codigo;
    }

    public void setNivel_Codigo(int nivel_Codigo) {
        this.nivel_Codigo = nivel_Codigo;
    }

    public String getNivel_Descricao() {
        return nivel_Descricao;
    }

    public void setNivel_Descricao(String nivel_Descricao) {
        this.nivel_Descricao = nivel_Descricao;
    }
    
}
