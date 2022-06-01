
package fiados.com.service;

import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Trade;
import fiados.com.models.request.TraderRequest;
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

    private Trade getTrade(Long id) {
        Optional<Trade> trade = tradeRepository.findById(id);
        if ((trade.isEmpty() || trade.get().isSoftDelete())){
            throw new UnsupportedOperationException(TRADE_NOT_FOUND_MESSAGE);
        }
        return trade.get();
    }

    @Override
    public TradeResponse update(Long id, TraderRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TradeResponse getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TradeResponse> getAllUser() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



}
