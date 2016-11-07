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
public class Usuario {
    private int usuario_Codigo;
    private String usuario_Login;
    private String usuario_Senha;
    private String usuario_Tipo;
    private int usuario_Aluno;
    private int usuario_Funcionario;
    private String str_usuario_Aluno;
    private String str_usuario_Func;
    
    

    public int getUsuario_Codigo() {
        return usuario_Codigo;
    }

    public void setUsuario_Codigo(int usuario_Codigo) {
        this.usuario_Codigo = usuario_Codigo;
    }

    public String getUsuario_Login() {
        return usuario_Login;
    }

    public void setUsuario_Login(String usuario_Login) {
        this.usuario_Login = usuario_Login;
    }

    public String getUsuario_Senha() {
        return usuario_Senha;
    }

    public void setUsuario_Senha(String usuario_Senha) {
        this.usuario_Senha = usuario_Senha;
    }

    public String getUsuario_Tipo() {
        return usuario_Tipo;
    }

    public void setUsuario_Tipo(String usuario_Tipo) {
        this.usuario_Tipo = usuario_Tipo;
    }

    public int getUsuario_Aluno() {
        return usuario_Aluno;
    }

    public void setUsuario_Aluno(int usuario_Aluno) {
        this.usuario_Aluno = usuario_Aluno;
    }

    public int getUsuario_Funcionario() {
        return usuario_Funcionario;
    }

    public void setUsuario_Funcionario(int usuario_Funcionario) {
        this.usuario_Funcionario = usuario_Funcionario;
    }

    public String getStr_usuario_Aluno() {
        return str_usuario_Aluno;
    }

    public void setStr_usuario_Aluno(String str_usuario_Aluno) {
        this.str_usuario_Aluno = str_usuario_Aluno;
    }

    public String getStr_usuario_Func() {
        return str_usuario_Func;
    }

    public void setStr_usuario_Func(String str_usuario_Func) {
        this.str_usuario_Func = str_usuario_Func;
    }

    
    
}
