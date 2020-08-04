package br.com.ey.msheroi.controllers.api;

import br.com.ey.msheroi.controllers.CommonController;
import br.com.ey.msheroi.common.enums.Situacao;
import br.com.ey.msheroi.model.heroi.facade.HeroiFacade;
import br.com.ey.msheroi.common.vo.Heroi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(HeroiController.ROTA_HEROIS)
public class HeroiController extends CommonController {

    protected static final String ROTA_HEROIS = ROTA_API + "/herois";
    private static final String CADASTRO_OK = "Heroi cadastrado com sucesso";
    private static final String ATUALIZACAO_OK = "Heroi atualizado com sucesso";

    @Autowired
    protected HeroiFacade heroiFacade;

    @PostMapping
    public ResponseEntity<Long> cadastraHeroi(@RequestBody Heroi heroi) {
        return ok(heroiFacade.criaHeroi(heroi).getId(), CADASTRO_OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> atualizaHeroi(@PathVariable(value = "id") Long id, @RequestBody Heroi heroi){
        return ok(heroiFacade.alteraHeroi(id, heroi).getId(), ATUALIZACAO_OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Heroi> buscaHeroi(@PathVariable(value = "id") Long id){
        return ok(heroiFacade.buscaHeroiPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Heroi>> listaHerois(@PathParam(value = "situacao") Situacao situacao){
        return ok(heroiFacade.buscaHerois(situacao));
    }
}