
package fiados.com.service.abstraction;

import fiados.com.models.entity.Trade;
import fiados.com.models.request.TradeRequest;
import fiados.com.models.response.TradeResponse;
import fiados.com.models.response.TradeUpdateResponse;

import java.util.List;


public interface TradeService {
    public Trade getInfoUser();        
    public void delete(Long id);   

    public TradeResponse getById(Long id);
    public Trade getTrade(Long id);
    List<TradeResponse> getAll();
    TradeUpdateResponse update(Long id, TradeRequest request);

    List<TradeResponse> findByFirstNameAndCity(String firstName, String city);
}
