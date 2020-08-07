package br.com.ey.msheroi.controllers.admin;

import br.com.ey.msheroi.controllers.CommonController;
import br.com.ey.msheroi.model.universo.facade.UniversoFacade;
import br.com.ey.msheroi.common.vo.Universo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AdminUniversoController.ROTA_UNIVERSOS)
public class AdminUniversoController extends CommonController {

    protected static final String ROTA_UNIVERSOS = ROTA_ADMIN + "/universos";
    private static final String ATUALIZACAO_OK = "Universo atualizado com sucesso";

    @Autowired
    protected UniversoFacade universoFacade;

    @PutMapping("/{id}")
    public ResponseEntity<Long> atualizaUniverso(@PathVariable(value = "id") Integer id, @RequestBody Universo universo){
        return ok(universoFacade.alteraUniverso(id, universo).getId(), ATUALIZACAO_OK);
    }
}
