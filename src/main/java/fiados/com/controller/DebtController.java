package fiados.com.controller;

import fiados.com.models.request.DebRequestTotal;
import fiados.com.models.response.DebResponseTotal;
import fiados.com.models.response.DebtResponse;
import fiados.com.service.abstraction.DebtsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debts")
@CrossOrigin(origins = "*")
@Api(value = "Debt Controller", description = "Controllers with respect to Client/Merchant debts")
public class DebtController {
    
    @Autowired
    private DebtsService debtService;

    @GetMapping("/list")
    @ApiOperation(value = "Search all debts of the client", notes = "Return list debts" )    
    public List<DebtResponse> debtCustomer(){      
        return debtService.findAllDebt();
    }

    @GetMapping("/total") 
    @ApiOperation(value = "Returns the total debt per merchant", notes = "Return total debts" ) 
    
    public ResponseEntity<DebResponseTotal> getTotal(@RequestBody DebRequestTotal requestTotal){
        DebResponseTotal responseTotal = debtService.getTotal(requestTotal);
        return ResponseEntity.status(HttpStatus.OK).body(responseTotal);
    }

}
