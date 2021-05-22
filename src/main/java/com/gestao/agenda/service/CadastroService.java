package com.gestao.agenda.service;

import com.gestao.agenda.model.Consulta;
import com.gestao.agenda.model.Medico;
import com.gestao.agenda.model.Paciente;
import com.gestao.agenda.model.Secretarie;
import com.gestao.agenda.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroService {
    private static final String HORARIO_INDISPONIVEL = "esse horário não está disponível...";
    private static final String CRM_INDISPONIVEL = "esse crm já está cadastrado no sistema";
    private static final String CPF_INDISPONIVEL = "esse cpf já está cadastrado no sistema";
    private final Sql sql;

    @Autowired
    public CadastroService(Sql sql){
        this.sql=sql;
    }

    public void addMedico(Medico medico) throws Exception {
        crmDisponivel(medico.getCrm());
        sql.cadastrarMedico(medico);
    }

    public void addPaciente(Paciente paciente) throws Exception{
        cpfPacienteDisponivel(paciente.getCpf());
        sql.cadastrarPaciente(paciente);
    }

    public void addSecretarie(Secretarie secretarie) throws Exception{
        cpfDisponivel(secretarie.getCpf());
        sql.cadastrarSecretarie(secretarie);
    }

    public void addConsulta(Consulta consulta) throws Exception {
        horarioDisponivel(consulta.getDia(), consulta.getHorario(), consulta.getIdMedico());
        sql.cadastrarConsulta(consulta);
    }

    public void horarioDisponivel(String dia, String horario, int idMedico) throws Exception {
        if(sql.horarioDisponivel(dia, horario, idMedico)){
            throw new Exception(CRM_INDISPONIVEL);
        }
    }

    public void crmDisponivel(String crm) throws Exception {
        if(sql.crmDisponivel(crm)>0){
            throw new Exception(CRM_INDISPONIVEL);
        }
    }

    public void cpfPacienteDisponivel(String cpf) throws Exception {
        if(sql.cpfPacienteDisponivel(cpf)>0){
            throw new Exception(CPF_INDISPONIVEL);
        }
    }

    public void cpfDisponivel(String cpf) throws Exception {
        if(sql.cpfDisponivel(cpf)>0){
            throw new Exception(CPF_INDISPONIVEL);
        }
    }
}
