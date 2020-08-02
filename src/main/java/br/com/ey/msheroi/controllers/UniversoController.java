package br.com.ey.msheroi.controllers;

import br.com.ey.msheroi.facade.UniversoFacade;
import br.com.ey.msheroi.vo.Universo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(br.com.ey.msheroi.controllers.UniversoController.ROTA_UNIVERSOS)
public class UniversoController extends CommonController{

    protected static final String ROTA_UNIVERSOS = ROTA_ADMIN + "/universos";
    private static final String ATUALIZACAO_OK = "Universo atualizado com sucesso";

    @Autowired
    protected UniversoFacade universoFacade;

    @PutMapping("/{id}")
    public ResponseEntity<Long> atualizaUniverso(@PathVariable(value = "id") Integer id, @RequestBody Universo universo){
        return ok(universoFacade.alteraUniverso(id, universo).getId(), ATUALIZACAO_OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Universo> buscaUniverso(@PathVariable(value = "id") Integer id){
        return ok(universoFacade.buscaUniversoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Universo>> listaUniversos(){
        return ok(universoFacade.buscaUniversos());
    }

}
