package fiados.com.models.mapper;

import fiados.com.models.entity.Branch;
import fiados.com.models.entity.Trade;
import fiados.com.models.request.BranchRequest;
import fiados.com.models.response.BranchResponse;
import fiados.com.models.response.TradeBranchResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        response.setId(branch.getId());
        response.setDescriptions(branch.getDescriptions());
        response.setDireccion(branch.getDireccion());
        return response;
    }
    public BranchResponse entity2DTO(Branch branch) {
        BranchResponse response = new BranchResponse();
        response.setId(branch.getId());
        response.setDireccion(branch.getDireccion());
        response.setDescriptions(branch.getDescriptions());
        TradeBranchResponse trade = new TradeBranchResponse();
        trade.setEmail(branch.getTrade().getEmail());
        trade.setDni(branch.getTrade().getDni());
        trade.setRole(branch.getTrade().getRole());
        trade.setAdress(branch.getTrade().getAdress());
        trade.setCountry(branch.getTrade().getCountry());
        trade.setCity(branch.getTrade().getCity());
        trade.setId(branch.getTrade().getId());
        response.setTrade(trade);
        return response;
    }
    public List<BranchResponse> entityList2DTO(List<Branch> branchList) {
        List<BranchResponse> responseList = new ArrayList<>();
        BranchResponse response;
        for (Branch b: branchList){
            response = new BranchResponse();
            response.setId(b.getId());
            response.setDireccion(b.getDireccion());
            response.setDescriptions(b.getDescriptions());
            TradeBranchResponse trade = new TradeBranchResponse();
            trade.setEmail(b.getTrade().getEmail());
            trade.setDni(b.getTrade().getDni());
            trade.setRole(b.getTrade().getRole());
            trade.setAdress(b.getTrade().getAdress());
            trade.setCountry(b.getTrade().getCountry());
            trade.setCity(b.getTrade().getCity());
            trade.setId(b.getTrade().getId());
            response.setTrade(trade);
            responseList.add(response);
        }
        return responseList;
    }

    public void entityRefreshValues(Branch branch, BranchRequest request) {
        branch.setDireccion(request.getDireccion());
        branch.setDescriptions(request.getDescriptions());
    }

    public List<BranchResponse> entitySet2DtoList(List<Branch> branchList) {
        List<BranchResponse> responses = new ArrayList<>();
        for (Branch b: branchList){
            responses.add(branchEntity2DTO(b));
        }
        return responses;
    }
}
