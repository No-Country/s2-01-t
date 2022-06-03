package fiados.com.service;

import fiados.com.models.response.CustomerDebtResponse;
import fiados.com.models.response.TradeDebtResponce;
import fiados.com.service.abstraction.DebtsService;
import org.springframework.stereotype.Service;

@Service
public class DebtsServiceImpl implements DebtsService {

    @Override
    public CustomerDebtResponse findCustomer(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TradeDebtResponce findTrade(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
