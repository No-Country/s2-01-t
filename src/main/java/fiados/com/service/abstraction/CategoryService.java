package fiados.com.service.abstraction;

import fiados.com.models.entity.Category;
import fiados.com.models.request.CategoryRequest;
import fiados.com.models.response.CategoryResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    public ResponseEntity<?> addCategory(CategoryRequest category);
    public List<CategoryResponse> findAll();
    public ResponseEntity<?> update(Long id, CategoryRequest category);
    public CategoryResponse findById(Long id);
    void delete(Long id);
}
