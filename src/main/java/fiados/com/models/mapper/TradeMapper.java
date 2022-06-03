package fiados.com.models.mapper;

import fiados.com.models.entity.Trade;
import fiados.com.models.response.BranchResponse;
import fiados.com.models.response.TradeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TradeMapper {

    @Autowired
    private BranchMapper branchMapper;
    public TradeResponse entity2DTO(Trade trade, boolean loadBranch) {
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
        return response;
    }
}
