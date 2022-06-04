package fiados.com.controller;

import fiados.com.models.entity.User;
import fiados.com.models.response.TradeResponse;
import fiados.com.service.abstraction.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/trade")
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
    @GetMapping("/{id}")
    public ResponseEntity<TradeResponse> getById(@PathVariable Long id){
        TradeResponse response = tradeService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TradeResponse>> getAll(){
        List<TradeResponse> responseList = tradeService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

}
