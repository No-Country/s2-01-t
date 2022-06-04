
package fiados.com.service;

import fiados.com.models.entity.Trade;
import fiados.com.models.mapper.TradeMapper;
import fiados.com.models.request.TradeRequest;
import fiados.com.models.response.TradeResponse;
import fiados.com.repository.TradeRepository;
import fiados.com.repository.UserRepository;
import fiados.com.service.abstraction.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService{

    private static final String TRADE_NOT_FOUND_MESSAGE = "Trade not found.";

    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TradeMapper tradeMapper;

    @Override
    public Trade getInfoUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            try {
                if(principal instanceof Trade) {
                    String userName = ((Trade) principal).getUsername();
                }
            }catch (Exception e) {
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
        Optional<Trade> trade = Optional.of(tradeRepository.findById(id).orElseThrow());
        return trade.get();
    }
    //TODO falta agregar que devuelva deuda y puntaje
    //Devuelve todos los comerciante y sus sucursales
    @Override
    public List<TradeResponse> getAll() {
        List<Trade> tradeList = tradeRepository.findAll();
        return tradeMapper.entitySet2DTOList(tradeList);
    }

    @Override
    public TradeResponse update(Long id, TradeRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //TODO falta agregar que devuelva deuda y puntaje
    //Devuelve al comerciante y sus sucursales
    @Override
    public TradeResponse getById(Long id) {
        Trade trade = getTrade(id);
        return tradeMapper.entity2DTO(trade, true);
    }





}
