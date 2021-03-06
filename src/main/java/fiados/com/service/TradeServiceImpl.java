package fiados.com.service;

import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Debt;
import fiados.com.models.entity.Point;
import fiados.com.models.entity.Trade;
import fiados.com.models.enums.EnumCondition;
import fiados.com.models.mapper.PointMapper;
import fiados.com.models.mapper.TradeMapper;
import fiados.com.models.request.DebtRequest;
import fiados.com.models.request.PointRequest;
import fiados.com.models.request.TradeDebtRequest;
import fiados.com.models.request.TradeRequest;
import fiados.com.models.response.DebtCustomerResponse;
import fiados.com.models.response.PointResponse;
import fiados.com.models.response.TradeResponse;
import fiados.com.models.response.TradeUpdateResponse;
import fiados.com.repository.TradeRepository;
import fiados.com.repository.UserRepository;
import fiados.com.service.abstraction.CustomerService;
import fiados.com.service.abstraction.DebtsService;
import fiados.com.service.abstraction.PointService;
import fiados.com.service.abstraction.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService {

    private static final String TRADE_NOT_FOUND_MESSAGE = "Trade not found.";

    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TradeMapper tradeMapper;
    @Autowired
    private DebtsService debtService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PointMapper pointMapper;

    @Autowired
    private PointService pointService;



    @Override
    public Trade getInfoUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (principal instanceof Trade) {
                String userName = ((Trade) principal).getUsername();
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
        return (Trade) userRepository.findByEmail(principal.toString());
    }

    @Override
    public void delete(Long id) {
        Trade trade = getTrade(id);
        trade.setSoftDelete(true);
        tradeRepository.save(trade);
    }

    @Override
    public Trade getTrade(Long id) {
        Optional<Trade> trade = tradeRepository.findById(id);
        if (trade.isEmpty() || trade.get().isSoftDelete()) {
            throw new RuntimeException("User not found.");
        }
        return trade.get();
    }


    //Devuelve todos los comerciante y sus sucursales e puntajes
    @Override
    public List<TradeResponse> getAll() {
        List<Trade> tradeList = tradeRepository.findAll();
        return tradeMapper.entitySet2DTOList(tradeList);
    }

    @Override
    public TradeUpdateResponse update(Long id, TradeRequest request) {
        Trade trade = tradeRepository.findById(id).orElseThrow();
        tradeMapper.tradeRefreshValues(trade, request);
        Trade tradeUpdate = tradeRepository.save(trade);
        return tradeMapper.tradeEntity2DTO(tradeUpdate);

    }
    @Override
    public List<TradeResponse> findByFirstNameAndCity(String firstName, String city) {
        List<TradeResponse> responses = new ArrayList<>();
        List<Trade> trades = tradeRepository.findByFirstNameAndCity(firstName, city);
        trades.forEach(trade -> {
            responses.add(tradeMapper.entity2DTO(trade, true, true, true));
        });
        return responses;
    }

    //Devuelve al comerciante y sus sucursales, y sus puntuaciones a distintos clientes
    @Override
    public TradeResponse getById(Long id) {
        Trade trade = getTrade(id);
        return tradeMapper.entity2DTO(trade, true, true, true);
    }
   @Override
    public DebtCustomerResponse tradeDebtCustomer(TradeDebtRequest request) {
        Trade trade = getInfoUser();  
        Customer customer = customerService.getById(request.getId_customer());
        Debt debt = debtService.add(DebtRequest.builder()            
                .totalAmount(request.getTotalAmount())
                .customer(customer)
                .conditions(EnumCondition.ACTIVATED)
                .trade(trade)
                .build());
        trade.addDebt(debt);
        try{
            tradeRepository.save(trade);
            customerService.customerDebt(debt, customer);
        }catch(Exception e){
            throw new RuntimeException("error saving merchant data.");
        }        
        return tradeMapper.tradeToDebt(trade, customer, debt);
    }
    @Override
    public void tradeAddPoint(Trade trade, Point point){
        List<Point> points=trade.getPoints();               
       
         try{
             points.add(point);
             trade.setPoints(points);
            tradeRepository.save(trade);
        }catch(Exception e){
            throw new RuntimeException("error saving merchant data.");
        }
    }

    @Override
    public PointResponse addPointCustomer(PointRequest request) {
        Trade trade = getInfoUser();
        Customer customer = customerService.getById(request.getIdCostumer());
        Point point = pointMapper.entity2DTO(request);
        point.setIdTrade(trade.getId());
        point.setIdCostumer(customer.getId());
        customer.addPoint(point);
        pointService.addPointCustomer(point);
        customerService.save(customer);
        return pointMapper.DTO2Entity(point);
    }


}
