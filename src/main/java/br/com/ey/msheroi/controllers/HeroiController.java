package br.com.ey.msheroi.controllers;

import br.com.ey.msheroi.enums.TipoSituacaoEnum;
import br.com.ey.msheroi.service.HeroiService;
import br.com.ey.msheroi.vo.Heroi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(HeroiController.ROTA_HEROIS)
public class HeroiController extends CommonController{

    protected static final String ROTA_HEROIS = "/herois";
    private static final String CADASTRO_OK = "Heroi cadastrado com sucesso";
    private static final String ATUALIZACAO_OK = "Heroi atualizado com sucesso";

    @Autowired
    protected HeroiService heroiService;

    @PostMapping
    public ResponseEntity cadastraHeroi(@RequestBody Heroi heroi) {
        return ok(heroiService.criaHeroi(heroi).getId(), CADASTRO_OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizaHeroi(@PathVariable(value = "id") Long id, @RequestBody Heroi heroi){
        return ok(heroiService.alteraHeroi(id, heroi).getId(), ATUALIZACAO_OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscaHeroi(@PathVariable(value = "id") Long id){
        return ok(heroiService.buscaHeroiPorId(id));
    }

    @GetMapping
    public ResponseEntity listaHerois(@PathParam(value = "situacao") TipoSituacaoEnum situacao){
        return ok(heroiService.buscaHerois(situacao));
    }
}