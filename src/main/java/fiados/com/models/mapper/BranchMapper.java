package fiados.com.models.mapper;

import fiados.com.models.entity.Branch;
import fiados.com.models.entity.Trade;
import fiados.com.models.request.BranchRequest;
import fiados.com.models.response.BranchResponse;
import fiados.com.models.response.TradeBranchResponse;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper {
    public Branch branchDTO2Entity(BranchRequest request, Trade trade) {
        Branch branch = new Branch();
        branch.setDescriptions(request.getDescriptions());
        branch.setDireccion(request.getDireccion());
        branch.setTrade(trade);
        return branch;
    }
    public BranchResponse branchEntity2DTO(Branch branch) {
        BranchResponse response = new BranchResponse();
        response.setDescriptions(branch.getDescriptions());
        response.setDireccion(branch.getDireccion());
        return response;
    }
    public BranchResponse entity2DTO(Branch branch) {
        BranchResponse response = new BranchResponse();
        response.setDireccion(branch.getDireccion());
        response.setDescriptions(branch.getDescriptions());
        TradeBranchResponse trade = new TradeBranchResponse();
        trade.setEmail(branch.getTrade().getEmail());
        trade.setDni(branch.getTrade().getDni());
        trade.setRole(branch.getTrade().getRole());
        response.setTrade(trade);
        return response;
    }
}
