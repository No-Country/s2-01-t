package fiados.com.service.abstraction;

import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Debt;
import fiados.com.models.request.DebtRequest;
import fiados.com.models.response.DebtResponse;
import fiados.com.models.response.TradeDebtResponce;
import java.util.List;

public interface DebtsService {
    List<Debt> findByCustomer(Customer customer); //id Customer
    TradeDebtResponce findByTrade(String id);
    Debt add(DebtRequest debtrequest);
    List<DebtResponse> findAllDebt();
}
