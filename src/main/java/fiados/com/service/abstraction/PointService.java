package fiados.com.service.abstraction;

import fiados.com.models.entity.Point;
import fiados.com.models.request.PointRequest;
import fiados.com.models.response.PointResponse;

public interface PointService {

    PointResponse addPointTrade(PointRequest request); 
    Point addPointCustomer(Point point);
    void deleted(Long id);
}
