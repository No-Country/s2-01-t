package fiados.com.service.abstraction;

import fiados.com.models.response.CustomerDebtResponse;
import fiados.com.models.response.TradeDebtResponce;

public interface DebtsService {
    CustomerDebtResponse findCustomer(String id); //id Customer
    TradeDebtResponce findTrade(String id);
}
