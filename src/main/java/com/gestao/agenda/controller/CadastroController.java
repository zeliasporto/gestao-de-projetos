package com.gestao.agenda.controller;

import com.gestao.agenda.model.Consulta;
import com.gestao.agenda.model.Medico;
import com.gestao.agenda.model.Paciente;
import com.gestao.agenda.model.Secretarie;
import com.gestao.agenda.service.CadastroService;
import com.gestao.agenda.service.SecretarieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {
    private final static String LOGIN_INVALIDO = "login não reconhecido :(";
    private final static String CONSULTA_AGENDADA = "consulta agendada!! :)";
    private final static String MEDICO_CADASTRADO = "medico cadastrado :)";
    private final static String PACIENTE_CADASTRADO = "paciente cadastrado :)";
    private final static String SECRETARIE_CADASTRADO = "secretarie cadastrado :)";
    private final SecretarieService secretarieService;
    private final CadastroService cadastroService;

    @Autowired
    public CadastroController(SecretarieService secretarieService, CadastroService cadastroService) {
        this.secretarieService=secretarieService;
        this.cadastroService=cadastroService;
    }

    @GetMapping("/consulta")
    public ResponseEntity addConsulta(@RequestBody Consulta consulta, @RequestParam String login){
        try {
            this.secretarieService.getSecretarie(login).orElseThrow(() -> new Exception(LOGIN_INVALIDO));
            this.cadastroService.addConsulta(consulta);
            return ResponseEntity.ok(CONSULTA_AGENDADA);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/medico")
    public ResponseEntity addMedico(@RequestBody Medico medico, @RequestParam String login){
        try {
            this.secretarieService.getSecretarie(login).orElseThrow(() -> new Exception(LOGIN_INVALIDO));
            this.cadastroService.addMedico(medico);
            return ResponseEntity.ok(MEDICO_CADASTRADO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/paciente")
    public ResponseEntity addPaciente(@RequestBody Paciente paciente, @RequestParam String login){
        try {
            this.secretarieService.getSecretarie(login).orElseThrow(() -> new Exception(LOGIN_INVALIDO));
            this.cadastroService.addPaciente(paciente);
            return ResponseEntity.ok(PACIENTE_CADASTRADO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/secretarie")
    public ResponseEntity addSecretarie(@RequestBody Secretarie secretarie, @RequestParam String login){
        try {
            this.secretarieService.getSecretarie(login).orElseThrow(() -> new Exception(LOGIN_INVALIDO));
            this.cadastroService.addSecretarie(secretarie);
            return ResponseEntity.ok(SECRETARIE_CADASTRADO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
