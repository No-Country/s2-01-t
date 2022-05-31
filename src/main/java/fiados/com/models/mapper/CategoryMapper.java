package fiados.com.models.mapper;


import fiados.com.models.entity.Category;
import fiados.com.models.request.CategoryRequest;
import fiados.com.models.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    
    public Category requestDtoEntity(CategoryRequest request)  {
        return Category.builder()
                .name(request.getName())
                .description(request.getDescription())                
                .build();
    }

    public CategoryResponse categoryDto(Category category){
           return CategoryResponse.builder()
                   .id(category.getId())
                   .name(category.getName())
                   .description(category.getDescription())                   
                   .build();
    }
    public Category DtoEntity(Category entityById,CategoryRequest request){
        entityById.setName(request.getName());
        entityById.setDescription(request.getDescription());
        return entityById;
    }
 
}
