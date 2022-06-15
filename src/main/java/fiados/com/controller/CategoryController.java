package fiados.com.controller;

import fiados.com.models.request.CategoryRequest;
import fiados.com.service.abstraction.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import javassist.NotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/category")
@CrossOrigin("*")
@Api(value = "Merchant Pathology Controller", description = "Controller for the processing of categories of one or several merchants")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @ApiOperation(value = "Method to create categories", notes = "Category salved")
    @PostMapping("")
    public ResponseEntity<?> createCategory(
            @RequestBody CategoryRequest category)
            throws URISyntaxException, NotFoundException {
        ResponseEntity<?> response = service.addCategory(category);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @ApiOperation(value = "Method to list all categories", notes = "Returns a list of categories")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @ApiOperation(value = "Method to search by id a category", notes = "Returns info category")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }

    }

    @ApiOperation(value = "Method to updated by id a category", notes = "Returns info category")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
            @RequestBody CategoryRequest entity)
            throws URISyntaxException, NotFoundException {
        ResponseEntity<?> response = service.update(id, entity);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @ApiOperation(value = "Method to delete by id a category", notes = "Returns void")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws EntityNotFoundException, NotFoundException {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
