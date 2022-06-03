package fiados.com.service.abstraction;

import fiados.com.models.entity.Branch;
import fiados.com.models.request.BranchRequest;
import fiados.com.models.response.BranchResponse;

import java.util.List;

public interface BranchService {
    BranchResponse save(BranchRequest request);
    BranchResponse getById(Long id);
    List<BranchResponse> getAll();
    void deleted(Long id);
    Branch getBranch(Long id);
    BranchResponse update(Long id, BranchRequest request);
}
