package fiados.com.controller;

import fiados.com.service.abstraction.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Access;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleted(@PathVariable Long id){
        tradeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
