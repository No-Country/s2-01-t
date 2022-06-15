package fiados.com.controller;

import fiados.com.models.request.BranchRequest;
import fiados.com.models.response.BranchResponse;
import fiados.com.service.abstraction.BranchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path ="/api/v1/branch")
@CrossOrigin("*")
@Api(value = "Merchant Branch Controller", description = "This method manages the branches of the merchant")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping("")
    @ApiOperation(value = "Method to create a branch", notes = "Return branch salved " )
    public ResponseEntity<BranchResponse> save(@Valid @RequestBody BranchRequest request){
        BranchResponse response = branchService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Search id branch method", notes = "Return all info branch " )
    public ResponseEntity<BranchResponse> getById(@PathVariable Long id){
        BranchResponse response = branchService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("/all")
    @ApiOperation(value = "Search list branch method", notes = "Return list branch " )
    public ResponseEntity<List<BranchResponse>> getAll(){
        List<BranchResponse> responses = branchService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Method that searches by id and removes", notes = "the metone returns nothing" )
    public ResponseEntity<Void> delete(@PathVariable Long id){
        branchService.deleted(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Method Update merchant locations", notes = "the metone returns nothing" )
    public ResponseEntity<BranchResponse> update(@PathVariable Long id, @RequestBody BranchRequest request){
        BranchResponse response = branchService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
