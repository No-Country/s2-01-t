package fiados.com.controller;

import fiados.com.models.request.DebRequestTotal;
import fiados.com.models.response.DebResponseTotal;
import fiados.com.models.response.DebtResponse;
import fiados.com.service.abstraction.DebtsService;
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
public class DebtController {
    
    @Autowired
    private DebtsService debtService;
    
//    @GetMapping("/list")
//    public ResponseEntity<?> debtCustomer() {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(debtService.findAllDebt());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
    @GetMapping("/list")
    public List<DebtResponse> debtCustomer(){
        System.out.println("LLega al contoller");
        return debtService.findAllDebt();
    }

    @GetMapping("/total")
    public ResponseEntity<DebResponseTotal> getTotal(@RequestBody DebRequestTotal requestTotal){
        DebResponseTotal responseTotal = debtService.getTotal(requestTotal);
        return ResponseEntity.status(HttpStatus.OK).body(responseTotal);
    }

}
