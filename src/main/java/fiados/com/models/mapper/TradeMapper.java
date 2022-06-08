package fiados.com.models.mapper;

import fiados.com.models.entity.Trade;
import fiados.com.models.request.TradeRequest;
import fiados.com.models.response.BranchResponse;
import fiados.com.models.response.PointResponse;
import fiados.com.models.response.TradeResponse;
import fiados.com.models.response.TradeUpdateResponse;
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
    public TradeResponse entity2DTO(Trade trade, boolean loadBranch, boolean loadPoint) {
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
        return response;
    }

    public List<TradeResponse> entitySet2DTOList(List<Trade> tradeList) {
        List<TradeResponse> responses = new ArrayList<>();
        tradeList.forEach(trade -> {
            responses.add(entity2DTO(trade,true,true));
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
    
}
