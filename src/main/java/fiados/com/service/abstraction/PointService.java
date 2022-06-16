package fiados.com.service.abstraction;

import fiados.com.models.entity.Point;
import java.util.List;

public interface PointService {


    Point addPointCustomer(Point point);
    void deleted(Long id);
    int totalPoint(List<Point> points);

}
