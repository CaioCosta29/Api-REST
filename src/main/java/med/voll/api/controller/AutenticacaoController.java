package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.autenticacao.DadosAutenticacao;
import med.voll.api.entity.Usuario;
import med.voll.api.security.DadosTokenJWT;
import med.voll.api.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dadosAutenticacao) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);


        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
