package fiados.com.service;

import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Debt;
import fiados.com.models.entity.Trade;
import fiados.com.models.enums.EnumCondition;
import fiados.com.models.mapper.TradeMapper;
import fiados.com.models.request.DebtRequest;
import fiados.com.models.request.TradeDebtRequest;
import fiados.com.models.request.TradeFilterRequest;
import fiados.com.models.request.TradeRequest;
import fiados.com.models.response.DebtCustomerResponse;
import fiados.com.models.response.TradeResponse;
import fiados.com.models.response.TradeUpdateResponse;
import fiados.com.repository.DebtRepository;
import fiados.com.repository.TradeRepository;
import fiados.com.repository.UserRepository;
import fiados.com.service.abstraction.CustomerService;
import fiados.com.service.abstraction.DebtsService;
import fiados.com.service.abstraction.TradeService;
import java.time.LocalDateTime;
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
    private DebtRepository debtRepository;
    @Autowired
    private CustomerService customerService;

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

    //TODO falta agregar que devuelva deuda
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
        TradeFilterRequest tradeFilterRequest = new TradeFilterRequest();
        List<TradeResponse> responses = new ArrayList<>();
        List<Trade> trades = tradeRepository.findByFirstNameAndCity(firstName, city);
        trades.forEach(trade -> {
            responses.add(tradeMapper.entity2DTO(trade, true, true));
        });
        return responses;
    }

    //TODO falta agregar que devuelva deuda
    //Devuelve al comerciante y sus sucursales, y sus puntuaciones a distintos clientes
    @Override
    public TradeResponse getById(Long id) {
        Trade trade = getTrade(id);
        return tradeMapper.entity2DTO(trade, true, true);
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

        return DebtCustomerResponse.builder()
                .id(debt.getId())
                .trade_id(trade.getId())
                .company(trade.getCompany_name())
                .date(debt.getDate().toLocalDate())
                .hour(debt.getDate().toLocalTime())
                .totalAmount(debt.getTotalAmount())
                .conditions(debt.getConditions())
                .customer_id(debt.getCustomer().getId())
                .first_name(debt.getCustomer().getFirstName())
                .last_name(debt.getCustomer().getLastName())
                .build();
    }

    
}
