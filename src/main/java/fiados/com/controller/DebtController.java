package fiados.com.controller;

import fiados.com.models.response.DebtResponse;
import fiados.com.models.response.DebtTotalResponse;
import fiados.com.service.abstraction.DebtsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return debtService.findAllDebt();
    }
    @GetMapping("/debt_total/{id}")
    public List<DebtTotalResponse> debtTotalCustomer(@PathVariable Long id){        
        return debtService.findDebtTotalResponse(id);
    }
}
