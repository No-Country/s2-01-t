package fiados.com.service;

import fiados.com.models.entity.Point;
import fiados.com.repository.PointRepository;
import fiados.com.service.abstraction.PointService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private PointRepository pointRepository;


    @Override
    public void deleted(Long id) {
        Point point = getPoint(id);
        point.setSoftDelete(true);
        pointRepository.save(point);

    }

    public Point getPoint(Long id) {
        Optional<Point> point = Optional.of(pointRepository.findById(id).orElseThrow());
        return point.get();
    }

    @Override
    public Point addPointCustomer(Point point) {
        try {
            return pointRepository.save(point);
        } catch (Exception e) {
            throw new RuntimeException("Problems with the generation of points");
        }
    }
    
    @Override
    public int totalPoint(List<Point> points){     
        return points.stream().parallel().mapToInt(Point::getPoint).sum();
    }
 
}
