package fiados.com.service;

import fiados.com.models.entity.Category;
import fiados.com.models.mapper.CategoryMapper;
import fiados.com.models.request.CategoryRequest;
import fiados.com.models.response.CategoryResponse;
import fiados.com.repository.CategoryRepository;
import fiados.com.service.abstraction.CategoryService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private static final String ERROR_FIND_ID = "Category not found";
    private static final String ERROR_CONECTION = "Error al intentar conectar con la BD";
    private static final String ERROR_NOT_LIST_CATEGORY = "No se encontro categorias";
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional
    @Override
    public ResponseEntity<?> addCategory(
            CategoryRequest category) {
        ResponseEntity<?> controlFieldsEmpty = controlFieldsEmpty(category);
        if (controlFieldsEmpty != null) {
            return controlFieldsEmpty;
        }
        try {
            categoryRepository.save(categoryMapper.requestDtoEntity(category));
            return new ResponseEntity<>("Category created succesfully!",
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ups something was wrong..!",
                    HttpStatus.CONFLICT);
        }
    }

    private ResponseEntity<?> controlFieldsEmpty(CategoryRequest category) {
        final ResponseEntity<?> messageFieldsEmpty
                = new ResponseEntity<>("The fields Name can't be empty",
                        HttpStatus.NOT_ACCEPTABLE);
        return (category.getName() == null
                || category.getName().trim().isEmpty()) ? messageFieldsEmpty : null;
    }   

    @Transactional
    @Override
    public ResponseEntity<?> update(Long id, CategoryRequest entity) {
         Category  entityById = categoryRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,ERROR_FIND_ID));        
      
        try {
            categoryRepository.save(categoryMapper.DtoEntity(entityById,entity));
            return new ResponseEntity<>("Category updated succesfully!",
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ups something was wrong..!",
                    HttpStatus.CONFLICT);
        }
    }

    @Transactional
    @Override
    public CategoryResponse findById(Long id) {
        try {
            Category entityById = categoryRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,ERROR_FIND_ID));        
            CategoryResponse entityResponse = categoryMapper.categoryDto(entityById);
                return entityResponse;
          
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(ERROR_CONECTION);
        }
    }
   
    @Transactional
    @Override
    public List<CategoryResponse> findAll() {
        return listZizeCategory(categoryRepository.findAll());
    }

  

    public List<CategoryResponse> listZizeCategory(List<Category> entities) {
        List<CategoryResponse> listResponse = new ArrayList<>();
        if (entities.size() == 0) {
            throw new EntityNotFoundException(ERROR_NOT_LIST_CATEGORY);
        }
        for (Category entity : entities) {
            listResponse.add(categoryMapper.categoryDto(entity));
        }
        return listResponse;
    }

    @Transactional
    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,ERROR_FIND_ID));
        category.setSoft_deleted(true);      
        categoryRepository.save(category);
    }


}
