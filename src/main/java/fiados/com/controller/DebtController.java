package fiados.com.controller;

import fiados.com.models.response.DebtResponse;
import fiados.com.service.abstraction.DebtsService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debts")
public class DebtController {
    
    private DebtsService debtService;
    
    @GetMapping("/list")
    public List<DebtResponse> debtCustomer(){
        System.out.println("LLega al contoller");
        return debtService.findAllDebt();
    }
}
