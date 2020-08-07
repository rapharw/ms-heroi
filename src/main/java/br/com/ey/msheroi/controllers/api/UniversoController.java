package br.com.ey.msheroi.controllers.api;

import br.com.ey.msheroi.controllers.CommonController;
import br.com.ey.msheroi.model.universo.facade.UniversoFacade;
import br.com.ey.msheroi.common.vo.Universo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UniversoController.ROTA_UNIVERSOS)
public class UniversoController extends CommonController {

    protected static final String ROTA_UNIVERSOS = ROTA_API + "/universos";

    @Autowired
    protected UniversoFacade universoFacade;

    @GetMapping("/{id}")
    public ResponseEntity<Universo> buscaUniverso(@PathVariable(value = "id") Integer id){
        return ok(universoFacade.buscaUniversoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Universo>> listaUniversos(){
        return ok(universoFacade.buscaUniversos());
    }

}
