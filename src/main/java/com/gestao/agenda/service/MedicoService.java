package com.gestao.agenda.service;

import com.gestao.agenda.model.Consulta;
import com.gestao.agenda.model.Medico;
import com.gestao.agenda.model.Paciente;
import com.gestao.agenda.model.Secretarie;
import com.gestao.agenda.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    private static final String SENHA_INVALIDA = "senha e/ou login inválido(s)";
    private final Sql sql;

    @Autowired
    public MedicoService(Sql sql){
        this.sql=sql;
    }

    public void loginMedico(String login, String senha) throws Exception {
        if(sql.loginMedico(login, senha)!=1){
            throw new Exception(SENHA_INVALIDA);
        }
    }

    public Optional<Secretarie> getMedico(String login){
        sql.getMedico(login);
        return null;
    }

    public List<Consulta> getAllConsultas(String idMedico, String dia){
        return sql.getAllConsultas(idMedico, dia);
    }
}
