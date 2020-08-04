package br.com.ey.msheroi.controllers.admin;

import br.com.ey.msheroi.controllers.CommonController;
import br.com.ey.msheroi.model.poder.facade.PoderFacade;
import br.com.ey.msheroi.common.vo.Poder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AdminPoderController.ROTA_PODERES)
public class AdminPoderController extends CommonController {

    protected static final String ROTA_PODERES = ROTA_ADMIN + "/poderes";
    private static final String CADASTRO_OK = "Poder cadastrado com sucesso";
    private static final String ATUALIZACAO_OK = "Poder atualizado com sucesso";
    private static final String REMOCAO_OK = "Poder removido com sucesso";

    @Autowired
    protected PoderFacade poderFacade;

    @PostMapping
    public ResponseEntity<Long> cadastraPoder(@RequestBody Poder poder) {
        return ok(poderFacade.criaPoder(poder).getId(), CADASTRO_OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> atualizaPoder(@PathVariable(value = "id") Integer id, @RequestBody Poder poder){
        return ok(poderFacade.alteraPoder(id, poder).getId(), ATUALIZACAO_OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePoder(@PathVariable(value = "id") Integer id){
        poderFacade.removePoder(id);
        return ok(REMOCAO_OK);
    }
}