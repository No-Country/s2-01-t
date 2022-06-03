package fiados.com.service;

import fiados.com.models.entity.Branch;
import fiados.com.models.entity.Trade;
import fiados.com.models.mapper.BranchMapper;
import fiados.com.models.request.BranchRequest;
import fiados.com.models.response.BranchResponse;
import fiados.com.repository.BranchRepository;
import fiados.com.repository.TradeRepository;
import fiados.com.service.abstraction.BranchService;
import fiados.com.service.abstraction.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchMapper branchMapper;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private TradeRepository tradeRepository;

    // Crea una sucursal y guarda en trade la relacion de dicha sucursal
    @Override
    public BranchResponse save(BranchRequest request) {
        Trade trade = tradeService.getInfoUser();
        Branch entity = branchMapper.branchDTO2Entity(request, trade);
        trade.addBranch(entity);// guardo branch en trade
        Branch branchSaved = branchRepository.save(entity);
        tradeRepository.save(trade); // le agrega al comerciante logueado la sucursal
        return branchMapper.branchEntity2DTO(branchSaved);
    }
    @Override
    public BranchResponse getById(Long id) {
        Branch branch = branchRepository.findById(id).orElseThrow();
        return branchMapper.entity2DTO(branch);
    }

    @Override
    public List<BranchResponse> getAll() {
        List<Branch> branchList = branchRepository.findAll();
        return branchMapper.entityList2DTO(branchList);
    }


}
