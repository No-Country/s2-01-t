package fiados.com.models.mapper;


import fiados.com.models.entity.Category;
import fiados.com.models.request.CategoryRequest;

import fiados.com.models.response.CategoryResponse;
import java.util.ArrayList;
import org.springframework.stereotype.Component;


@Component
public class CategoryMapper {
    
  
    //uso

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
 

}
