package com.gestao.agenda.sql;

import com.gestao.agenda.model.Consulta;
import com.gestao.agenda.model.Medico;
import com.gestao.agenda.model.Paciente;
import com.gestao.agenda.model.Secretarie;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sql {
    public List<Medico> getAllMedicos(String idSecretarie){
        String query = "select * from ResoMedSec where id_secretaria='"+idSecretarie+"'";
        //mudar o retorno
        return null;
    }

    public List<Paciente> getAllPacientes(){
        String query = "select * from Paciente";
        return null;
    }

    public List<Consulta> getAllConsultas(String id_medico, String dia){
        String query = "select * from Consulta where id_medico='"+id_medico+"' and dia='"+dia+"'";
        return null;
    }

    public void cadastrarMedico(Medico medico){
        String sql = "insert into Medico (id_medico, nome_medico, cpf, crm, email, telefone, senha) values "+
                     "("+medico.getId()+", "+medico.getNome()+", "+medico.getCpf()+", "+medico.getCrm()+", "+medico.getEmail()+", "+medico.getTelefone()+", "+medico.getSenha()+")";
    }

    public void cadastrarPaciente(Paciente paciente){
        String sql = "insert into Medico (id_paciente, nome_paciente, cpf_paciente, plano_paciente, idade_paciente, telefone_paciente) values "+
                "("+paciente.getId()+", "+paciente.getNome()+", "+paciente.getCpf()+", "+paciente.getPlano()+", "+paciente.getIdade()+", "+paciente.getTelefone()+")";
    }

    public void cadastrarSecretarie(Secretarie secretarie){
        String sql = "insert into Medico (id_secretaria, nome_secretaria, cpf, email, idade_paciente, telefone, senha) values "+
                "("+secretarie.getId()+", "+secretarie.getNome()+", "+secretarie.getCpf()+", "+secretarie.getEmail()+", "+secretarie.getIdade()+", "+secretarie.getTelefone()+", "+secretarie.getSenha()+")";
    }

    public boolean horarioDisponivel(String dia, String horario, int idMedico){
        String sql = "select (dia='"+dia+"'\n" +
                     "and horario='"+horario+"'\n" +
                     "and idMedico="+idMedico+"\n" +
                     "and cancelada=0) from Consulta";
        return true;
    }

    public int crmDisponivel(String crm){
        String sql = "select (crm='"+crm+"') from Medico";
        return 0;
    }

    public int cpfPacienteDisponivel(String cpf){
        String sql = "select (cpf_paciente='"+cpf+"') from Paciente";
        return 0;
    }

    public void cadastrarConsulta(Consulta consulta){
        String sql = "insert into Consulta (id_consulta, dia, horario, cancelada, id_medico, id_paciente) values "+
                "("+consulta.getId()+", "+consulta.getDia()+", "+consulta.getHorario()+", "+consulta.getCancelada()+", "+consulta.getIdMedico()+", "+consulta.getIdPaciente()+")";
    }

    public int cpfDisponivel(String cpf){
        String sql = "select (cpf='"+cpf+"') from Secretaria";
        return 0;
    }

    public int login(String login, String senha){
        String sql = "select ((email='"+login+"' or cpf='"+login+"') and senha='"+senha+"') from Secretaria";
        return 0;
    }

    public Secretarie getSecretarie(String login){
        String sql = "select * from Secretaria where (email='"+login+"' or cpf='"+login+"')";
        return null;
    }

    public int loginMedico(String login, String senha){
        String sql = "select ((email='"+login+"' or cpf='"+login+"') and senha='"+senha+"') from Medico";
        return 0;
    }

    public Secretarie getMedico(String login){
        String sql = "select * from Medico where (email='"+login+"' or cpf='"+login+"')";
        return null;
    }

    public void updateCancelada(String idConsulta) {
        String sql = "update Consulta set cancelada=true where id='"+idConsulta+"'";
    }
}
