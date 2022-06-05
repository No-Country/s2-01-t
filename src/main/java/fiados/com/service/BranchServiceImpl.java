package fiados.com.service;

import fiados.com.models.entity.Branch;
import fiados.com.models.entity.Category;
import fiados.com.models.entity.Trade;
import fiados.com.models.mapper.BranchMapper;
import fiados.com.models.mapper.CategoryMapper;
import fiados.com.models.request.BranchRequest;
import fiados.com.models.response.BranchResponse;
import fiados.com.models.response.CategoryResponse;
import fiados.com.repository.BranchRepository;
import fiados.com.repository.TradeRepository;
import fiados.com.service.abstraction.BranchService;
import fiados.com.service.abstraction.CategoryService;
import fiados.com.service.abstraction.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;
    // Crea una sucursal y guarda en trade la relacion de dicha sucursal y asigna categoria
    @Override
    public BranchResponse save(BranchRequest request) {
        Trade trade = tradeService.getInfoUser();
        Category category = categoryService.findByIdCategory(request.getIdCategory());
        Branch entity = branchMapper.branchDTO2Entity(request, trade);
        entity.setCategory(category);//Asigno categoria a branch
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

    @Override
    public void deleted(Long id) {
        Branch branch = getBranch(id);
        branch.setSoftDelete(true);
        branchRepository.save(branch);
    }
    @Override
    public Branch getBranch(Long id) {
        Optional<Branch> branch = branchRepository.findById(id);
        if(branch.isEmpty() || branch.get().isSoftDelete()){
            throw new RuntimeException("Branch not found");
        }
        return branch.get();
    }
    @Override
    public BranchResponse update(Long id, BranchRequest request) {
        Optional<Branch> branch = Optional.of(branchRepository.findById(id).orElseThrow());
        branchMapper.entityRefreshValues(branch.get(), request);
        Branch branchSaved = branchRepository.save(branch.get());
        return branchMapper.entity2DTO(branchSaved);
    }


}
