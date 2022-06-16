package fiados.com.service.abstraction;

import fiados.com.models.entity.Point;

public interface PointService {


    Point addPointCustomer(Point point);
    void deleted(Long id);
    void findAllUserId();
}
