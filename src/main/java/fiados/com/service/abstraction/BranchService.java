package fiados.com.service.abstraction;

import fiados.com.models.request.BranchRequest;
import fiados.com.models.response.BranchResponse;

public interface BranchService {
    BranchResponse save(BranchRequest request);
    BranchResponse getById(Long id);
}
