package fiados.com.controller;

import fiados.com.models.entity.Branch;
import fiados.com.models.request.BranchRequest;
import fiados.com.models.response.BranchResponse;
import fiados.com.service.abstraction.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path ="/api/v1/branch")
@CrossOrigin("*")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping("")
    public ResponseEntity<BranchResponse> save(@Valid @RequestBody BranchRequest request){
        BranchResponse response = branchService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchResponse> getById(@PathVariable Long id){
        BranchResponse response = branchService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("/all")
    public ResponseEntity<List<BranchResponse>> getAll(){
        List<BranchResponse> responses = branchService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        branchService.deleted(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchResponse> update(@PathVariable Long id, @RequestBody BranchRequest request){
        BranchResponse response = branchService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
