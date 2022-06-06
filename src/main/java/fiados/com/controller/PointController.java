package fiados.com.controller;

import fiados.com.models.request.PointRequest;
import fiados.com.models.response.PointResponse;
import fiados.com.service.abstraction.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/point")
@CrossOrigin(origins = "*")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("")
    private ResponseEntity<PointResponse> addPointTrade(@Valid @RequestBody PointRequest request){
        PointResponse response = pointService.addPointTrade(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        pointService.deleted(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
