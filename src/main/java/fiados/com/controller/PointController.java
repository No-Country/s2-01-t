package fiados.com.controller;


import fiados.com.models.entity.Trade;
import fiados.com.models.request.PointRequest;
import fiados.com.models.response.PointResponse;
import fiados.com.service.abstraction.PointService;
import fiados.com.service.abstraction.TradeService;
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

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        pointService.deleted(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
