package fiados.com.models.mapper;

import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Debt;
import fiados.com.models.entity.Trade;
import fiados.com.models.request.TradeRequest;
import fiados.com.models.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TradeMapper {

    @Autowired
    private PointMapper pointMapper;
    @Autowired
    private BranchMapper branchMapper;

    @Autowired
    private DebtMapper debtMapper;
    public TradeResponse entity2DTO(Trade trade, boolean loadBranch, boolean loadPoint, boolean loadDebt) {
        TradeResponse response = new TradeResponse();
        response.setAdress(trade.getAdress());
        response.setCity(trade.getCity());
        response.setCountry(trade.getCountry());
        response.setDni(trade.getDni());
        response.setEmail(trade.getEmail());
        response.setId(trade.getId());
        response.setRole(trade.getRole());
        response.setFirstName(trade.getFirstName());
        response.setCompany_name(trade.getCompany_name());
        response.setLastName(trade.getLastName());
        if (loadBranch){
            List<BranchResponse> branchResponseList = branchMapper.entitySet2DtoList(trade.getBranchList());
            response.setBranchResponseList(branchResponseList);
        }
        if (loadPoint){
           List<PointResponse> pointResponses = pointMapper.entitySet2DtoList(trade.getPoints());
           response.setPointResponses(pointResponses);
        }
        if(loadDebt){
            List<DebtResponse> debtResponses = debtMapper.entitySet2DtoList(trade.getDebts());
            response.setDebtResponseList(debtResponses);
        }
        return response;
    }

    public List<TradeResponse> entitySet2DTOList(List<Trade> tradeList) {
        List<TradeResponse> responses = new ArrayList<>();
        tradeList.forEach(trade -> {
            responses.add(entity2DTO(trade,true,true, true));
        });
        return responses;
    }

    public void tradeRefreshValues(Trade trade, TradeRequest request) {
        trade.setCompany_name(request.getCompany_name());
        trade.setAdress(request.getAdress());
        trade.setCity(request.getCity());
        trade.setDni(request.getDni());
        trade.setCountry(request.getCountry());
        trade.setEmail(request.getEmail());
        trade.setFirstName(request.getFirstName());
        trade.setLastName(request.getLastName());
    }
    public TradeUpdateResponse tradeEntity2DTO(Trade trade) {
        TradeUpdateResponse response = new TradeUpdateResponse();
        response.setId(trade.getId());
        response.setAdress(trade.getAdress());
        response.setCity(trade.getCity());
        response.setCountry(trade.getCountry());
        response.setEmail(trade.getEmail());
        response.setFirstName(trade.getFirstName());
        response.setDni(trade.getDni());
        response.setLastName(trade.getLastName());
        response.setCompany_name(trade.getCompany_name());
        return response;
    }
    
    public DebtCustomerResponse tradeToDebt(Trade trade, Customer customer, Debt debt){
      return  DebtCustomerResponse.builder()
                .id(debt.getId())
                .trade_id(trade.getId())
                .company(trade.getCompany_name())
                .date(debt.getDate().toLocalDate())
                .hour(debt.getDate().toLocalTime())
                .totalAmount(debt.getTotalAmount())
                .conditions(debt.getConditions())
                .customer_id(debt.getCustomer().getId())
                .first_name(debt.getCustomer().getFirstName())
                .last_name(debt.getCustomer().getLastName())
                .build();
    }
    
}
