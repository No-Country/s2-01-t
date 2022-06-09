package fiados.com.service;

import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Point;
import fiados.com.models.entity.Trade;
import fiados.com.models.entity.User;
import fiados.com.models.mapper.PointMapper;
import fiados.com.models.request.PointRequest;
import fiados.com.models.response.PointResponse;
import fiados.com.repository.PointRepository;
import fiados.com.repository.TradeRepository;
import fiados.com.service.abstraction.AuthService;
import fiados.com.service.abstraction.CustomerService;
import fiados.com.service.abstraction.PointService;
import fiados.com.service.abstraction.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private PointMapper pointMapper;
    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private AuthService authService;



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
}
