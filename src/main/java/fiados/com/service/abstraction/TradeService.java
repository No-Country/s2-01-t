
package fiados.com.service.abstraction;

import fiados.com.models.entity.Trade;
import fiados.com.models.request.TraderRequest;
import fiados.com.models.response.TradeResponse;
import java.util.List;


public interface TradeService {
    public Trade getInfoUser();        
    public void delete(Long id);   
    public TradeResponse update(Long id, TraderRequest request);    
    public TradeResponse getById(Long id);
    public List<TradeResponse> getAllUser();
    public Trade getTrade(Long id);


}
