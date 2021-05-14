package com.gestao.agenda.controller;

import com.gestao.agenda.model.Secretarie;
import com.gestao.agenda.service.MedicoService;
import com.gestao.agenda.service.SecretarieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secretarie")
public class SecretarieController {
    private final static String SECRETARIE_NAO_ENCONTRADO = "secretarie não cadastrado :(";
    private final static String LOGIN_EFETUADO = "login afetuado!!";
    private final SecretarieService secretarieService;

    @Autowired
    public SecretarieController(SecretarieService secretarieService) {
        this.secretarieService=secretarieService;
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody String login, @RequestBody String senha){
        try {
            Secretarie secretarie = this.secretarieService.getSecretarie(login)
                    .orElseThrow(() -> new Exception(SECRETARIE_NAO_ENCONTRADO));
            this.secretarieService.login(secretarie.getId(), senha);
            return ResponseEntity.ok(LOGIN_EFETUADO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/medicos")
    public ResponseEntity getAllMedicos(@RequestParam String idSecretaria){
        try {
            Secretarie secretarie = this.secretarieService.getSecretarie(idSecretaria)
                    .orElseThrow(() -> new Exception(SECRETARIE_NAO_ENCONTRADO));

            return ResponseEntity.ok(this.secretarieService.getAllMedicos(secretarie.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/pacientes")
    public ResponseEntity getAllPacientes(@RequestParam String idSecretaria){
        try {
            Secretarie secretarie = this.secretarieService.getSecretarie(idSecretaria)
                    .orElseThrow(() -> new Exception(SECRETARIE_NAO_ENCONTRADO));

            return ResponseEntity.ok(this.secretarieService.getAllPacientes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
