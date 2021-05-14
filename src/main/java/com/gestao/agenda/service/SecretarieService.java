package com.gestao.agenda.service;

import com.gestao.agenda.model.Medico;
import com.gestao.agenda.model.Paciente;
import com.gestao.agenda.model.Secretarie;
import com.gestao.agenda.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecretarieService {
    private static final String SENHA_INVALIDA = "senha e/ou login inválido(s)";
    private final Sql sql;

    @Autowired
    public SecretarieService(Sql sql){
        this.sql=sql;
    }

    public void login(String login, String senha) throws Exception {
        if(sql.login(login, senha)!=1){
            throw new Exception(SENHA_INVALIDA);
        }
    }

    public Optional<Secretarie> getSecretarie(String login){
        sql.getSecretarie(login);
        return null;
    }

    public List<Paciente> getAllPacientes(){
        return sql.getAllPacientes();
    }

    public List<Medico> getAllMedicos(String idSecretarie){
        return sql.getAllMedicos(idSecretarie);
    }
}
