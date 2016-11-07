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
public class Aluno extends Bairro  {
    private int aluno_Codigo;
    private String aluno_Nome;
    private String aluno_Cpf;
    private String aluno_Sexo;
    private String aluno_Fone;
    private String aluno_Rua;
    private String aluno_Casa;
    private String aluno_Email;

    public int getAluno_Codigo() {
        return aluno_Codigo;
    }

    public void setAluno_Codigo(int aluno_Codigo) {
        this.aluno_Codigo = aluno_Codigo;
    }

    public String getAluno_Nome() {
        return aluno_Nome;
    }

    public void setAluno_Nome(String aluno_Nome) {
        this.aluno_Nome = aluno_Nome;
    }

    public String getAluno_Cpf() {
        return aluno_Cpf;
    }

    public void setAluno_Cpf(String aluno_Cpf) {
        this.aluno_Cpf = aluno_Cpf;
    }

    public String getAluno_Fone() {
        return aluno_Fone;
    }

    public void setAluno_Fone(String aluno_Fone) {
        this.aluno_Fone = aluno_Fone;
    }

    public String getAluno_Rua() {
        return aluno_Rua;
    }

    public void setAluno_Rua(String aluno_Rua) {
        this.aluno_Rua = aluno_Rua;
    }

    public String getAluno_Casa() {
        return aluno_Casa;
    }

    public void setAluno_Casa(String aluno_Casa) {
        this.aluno_Casa = aluno_Casa;
    }

    public String getAluno_Email() {
        return aluno_Email;
    }

    public void setAluno_Email(String aluno_Email) {
        this.aluno_Email = aluno_Email;
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

    public String getAluno_Sexo() {
        return aluno_Sexo;
    }

    public void setAluno_Sexo(String aluno_Sexo) {
        this.aluno_Sexo = aluno_Sexo;
    }

    
  
}
