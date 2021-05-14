package com.gestao.agenda.controller;

import com.gestao.agenda.model.Secretarie;
import com.gestao.agenda.service.MedicoService;
import com.gestao.agenda.service.SecretarieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medico")
public class MedicoController {
    private final static String MEDICO_NAO_ENCONTRADO = "medico não cadastrado :(";
    private final static String LOGIN_EFETUADO = "login afetuado!!";
    private final MedicoService medicoService;
    private final SecretarieService secretarieService;

    @Autowired
    public MedicoController(MedicoService medicoService, SecretarieService secretarieService){
        this.medicoService=medicoService;
        this.secretarieService=secretarieService;
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody String login, @RequestBody String senha){
        try {
            this.secretarieService.getSecretarie(login)
                    .orElseThrow(() -> new Exception(MEDICO_NAO_ENCONTRADO));
            this.medicoService.loginMedico(login, senha);
            return ResponseEntity.ok(LOGIN_EFETUADO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/consultas")
    public ResponseEntity getAllConsultas(@RequestParam String idMedico, @RequestParam String dia){
        try {
            Secretarie secretarie = this.secretarieService.getSecretarie(idMedico)
                    .orElseThrow(() -> new Exception(MEDICO_NAO_ENCONTRADO));

            return ResponseEntity.ok(this.medicoService.getAllConsultas(secretarie.getId(), dia));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
