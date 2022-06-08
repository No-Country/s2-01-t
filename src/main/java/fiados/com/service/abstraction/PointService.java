package fiados.com.service.abstraction;

import fiados.com.models.entity.Trade;
import fiados.com.models.request.PointRequest;
import fiados.com.models.response.PointResponse;

public interface PointService {

    PointResponse addPointTrade(PointRequest request);

    void deleted(Long id);
}
