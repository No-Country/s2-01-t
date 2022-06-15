package fiados.com.controller;

import fiados.com.models.entity.Debt;
import fiados.com.models.request.CommentTradeRequest;
import fiados.com.models.request.CustomerPointRequest;
import fiados.com.models.request.CustomerRequest;
import fiados.com.models.response.CustomerComment;
import fiados.com.models.response.CustomerResponse;
import fiados.com.models.response.PointResponse;
import fiados.com.service.abstraction.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Api(value = "Controller to manage clients", description = "Controllers related to client services")
public class CustomerController {

    private final CustomerService customerService;

    @ApiOperation(value = "Method to search for a list of clients", notes = "Return list client")
    @GetMapping("/all")
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllUser();
    }

    @ApiOperation(value = "Method that searches for a client by id", notes = "Return info client")
    @GetMapping("{id}")
    public CustomerResponse getCustomer(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Method that searches for a client by id udpdate as info", notes = "Return info client updated")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody CustomerRequest request) {
        customerService.update(id, request);
    }

    @ApiOperation(value = "Method that searches and delete for a client by id", notes = "Return void")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        customerService.delete(id);
    }

    @ApiOperation(value = "Method that the client makes comments", notes = "Return client/Comment")
    @PostMapping("/customer_comment")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerComment commentCustomer(@RequestBody CommentTradeRequest comment) {
        return customerService.commentUser(comment);
    }

    @ApiOperation(value = "Method that reviews the client's debts", notes = "Return client/Debts")
    @GetMapping("/total_amount")
    public List<Debt> customerTotalAmount() {
        return customerService.customerTotalAmount();
    }

    @ApiOperation(value = "Method where customer rates the merchants", notes = "Return client/Point/Trade")
    @PostMapping("/trade_point")
    @ResponseStatus(HttpStatus.CREATED)
    public PointResponse customerPoint(@RequestBody CustomerPointRequest request) {
        return customerService.customerPoint(request);
    }
}
