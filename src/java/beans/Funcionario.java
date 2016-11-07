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
public class Funcionario extends Bairro  {
    private int funcionario_Codigo;
    private String funcionario_Nome;
    private String funcionario_Cpf;
    private String funcionario_Sexo;
    private String funcionario_Fone;
    private String funcionario_Rua;
    private String funcionario_Casa;
    private String funcionario_Email;

    public int getFuncionario_Codigo() {
        return funcionario_Codigo;
    }

    public void setFuncionario_Codigo(int funcionario_Codigo) {
        this.funcionario_Codigo = funcionario_Codigo;
    }

    public String getFuncionario_Nome() {
        return funcionario_Nome;
    }

    public void setFuncionario_Nome(String funcionario_Nome) {
        this.funcionario_Nome = funcionario_Nome;
    }

    public String getFuncionario_Cpf() {
        return funcionario_Cpf;
    }

    public void setFuncionario_Cpf(String funcionario_Cpf) {
        this.funcionario_Cpf = funcionario_Cpf;
    }

    public String getFuncionario_Fone() {
        return funcionario_Fone;
    }

    public void setFuncionario_Fone(String funcionario_Fone) {
        this.funcionario_Fone = funcionario_Fone;
    }

    public String getFuncionario_Rua() {
        return funcionario_Rua;
    }

    public void setFuncionario_Rua(String funcionario_Rua) {
        this.funcionario_Rua = funcionario_Rua;
    }

    public String getFuncionario_Casa() {
        return funcionario_Casa;
    }

    public void setFuncionario_Casa(String funcionario_Casa) {
        this.funcionario_Casa = funcionario_Casa;
    }

    public String getFuncionario_Email() {
        return funcionario_Email;
    }

    public void setFuncionario_Email(String funcionario_Email) {
        this.funcionario_Email = funcionario_Email;
    }

    @Override
    public int getBairro_Codigo() {
        return super.getBairro_Codigo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBairro_Codigo(int bairro_Codigo) {
        super.setBairro_Codigo(bairro_Codigo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getBairro_Descricao() {
        return super.getBairro_Descricao(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBairro_Descricao(String bairro_Descricao) {
        super.setBairro_Descricao(bairro_Descricao); //To change body of generated methods, choose Tools | Templates.
    }

    public String getFuncionario_Sexo() {
        return funcionario_Sexo;
    }

    public void setFuncionario_Sexo(String funcionario_Sexo) {
        this.funcionario_Sexo = funcionario_Sexo;
    }

    
  
}
