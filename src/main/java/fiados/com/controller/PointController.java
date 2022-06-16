package fiados.com.controller;


import fiados.com.models.response.BranchResponse;
import fiados.com.service.abstraction.PointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/point")
@CrossOrigin(origins = "*")
@Api(value = "Controller for points", description = "Controller related to scoring customers and merchants")
public class PointController {

    @Autowired
    private PointService pointService;
    
    @ApiOperation(value = "Method Delete", notes = "Return void" )
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        pointService.deleted(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/all")
    @ApiOperation(value = "Search list Point method", notes = "Return list Point " )
     @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getAll(){
        pointService.findAllUserId();        
    }
}
