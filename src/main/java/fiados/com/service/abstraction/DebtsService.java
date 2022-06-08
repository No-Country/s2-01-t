package fiados.com.service.abstraction;

import fiados.com.models.entity.Debt;
import fiados.com.models.request.DebtRequest;
import fiados.com.models.response.CustomerDebtResponse;
import fiados.com.models.response.TradeDebtResponce;

public interface DebtsService {
    CustomerDebtResponse findByCustomer(String id); //id Customer
    TradeDebtResponce findByTrade(String id);
    Debt add(DebtRequest debtrequest);
}
