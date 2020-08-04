package br.com.ey.msheroi.controllers.api;

import br.com.ey.msheroi.controllers.CommonController;
import br.com.ey.msheroi.model.poder.facade.PoderFacade;
import br.com.ey.msheroi.common.vo.Poder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(PoderController.ROTA_PODERES)
public class PoderController extends CommonController {

    protected static final String ROTA_PODERES = ROTA_API + "/poderes";

    @Autowired
    protected PoderFacade poderFacade;

    @GetMapping("/{id}")
    public ResponseEntity<Poder> buscaPoder(@PathVariable(value = "id") Integer id){
        return ok(poderFacade.buscaPoderPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Poder>> listaPoderes(){
        return ok(poderFacade.buscaPoderes());
    }
}