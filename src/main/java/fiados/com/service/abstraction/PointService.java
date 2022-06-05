package fiados.com.service.abstraction;

import fiados.com.models.entity.Point;
import fiados.com.models.request.PointRequest;
import fiados.com.models.response.PointResponse;

import java.util.Optional;

public interface PointService {
    PointResponse addPointTrade(PointRequest request);
    void deleted(Long id);
}
