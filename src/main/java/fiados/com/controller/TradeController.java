package fiados.com.controller;

import fiados.com.models.entity.User;
import fiados.com.service.abstraction.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/v1/trade")
@CrossOrigin(origins = "*")
public class TradeController {

    @Autowired
    private TradeService tradeService;
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleted(@PathVariable Long id){
        tradeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/me")
    public ResponseEntity<User> getInfoUser(){
        return new ResponseEntity<>(tradeService.getInfoUser(), HttpStatus.OK);
    }

}
