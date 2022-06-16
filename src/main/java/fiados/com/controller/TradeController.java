package fiados.com.controller;

import fiados.com.models.entity.Trade;
import fiados.com.models.request.PointRequest;
import fiados.com.models.request.TradeDebtRequest;
import fiados.com.models.request.TradeRequest;
import fiados.com.models.response.DebtCustomerResponse;
import fiados.com.models.response.PointResponse;
import fiados.com.models.response.TradeResponse;
import fiados.com.models.response.TradeUpdateResponse;
import fiados.com.service.abstraction.TradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/trade")

@Api(value = "Controller to manage trade", description = "Controllers related to trade services")
public class TradeController {

    @Autowired
    private TradeService tradeService;
    
    @ApiOperation(value = "Method that searches and delete for a trade by id", notes = "Return void")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleted(@PathVariable Long id) {
        tradeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/me")
    public ResponseEntity<Trade> getInfoUser() {
        return new ResponseEntity<>(tradeService.getInfoUser(), HttpStatus.OK);
    }
    @ApiOperation(value = "Method that searches for a trade by id", notes = "Return info trade")
    @GetMapping("/{id}")
    public ResponseEntity<TradeResponse> getById(@PathVariable Long id) {
        TradeResponse response = tradeService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @ApiOperation(value = "Method to search for a list of trade", notes = "Return list trade")
    @GetMapping("/all")
    public ResponseEntity<List<TradeResponse>> getAll() {
        List<TradeResponse> responseList = tradeService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }
    @ApiOperation(value = "Method that searches for a trade by id udpdate as info", notes = "Return info trade updated")
    @PutMapping("/{id}")
    public ResponseEntity<TradeUpdateResponse> update(@PathVariable Long id, @RequestBody TradeRequest request) {
        TradeUpdateResponse response = tradeService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Busca por nombre y ciudad
    @GetMapping("/filter")
    public ResponseEntity<List<TradeResponse>> findByFirstNameAndCity(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String city
    ) {
        List<TradeResponse> tradeResponses = tradeService.findByFirstNameAndCity(firstName, city);
        return ResponseEntity.ok().body(tradeResponses);
    }
    @ApiOperation(value = "Method to generate the debt of a client according to its id", notes = "Return ticket and Debts")
    @PostMapping("/trade_debt")
    public ResponseEntity<DebtCustomerResponse> tradeDebt(@RequestBody TradeDebtRequest request) {
        return ResponseEntity.ok().body(tradeService.tradeDebtCustomer(request));
    }
    @ApiOperation(value = "Method where merchants rates the customer", notes = "Return Trade/Point/Customer")
    @PostMapping("/addPoint")
    private ResponseEntity<PointResponse> addPointCostumer(@Valid @RequestBody PointRequest request) {
        PointResponse response = tradeService.addPointCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
