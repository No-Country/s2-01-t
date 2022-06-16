package fiados.com.service;

import fiados.com.models.entity.Debt;
import fiados.com.models.entity.Point;
import fiados.com.models.request.DebRequestTotal;
import fiados.com.models.response.DebResponseTotal;
import fiados.com.repository.PointRepository;
import fiados.com.service.abstraction.PointService;
import java.util.ArrayList;
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
    public void findAllUserId(){
        List<Point> l=pointRepository.findAll();
        for(Point p:l){
            System.out.println("Puntos:" + p.getPoint());
        }
    }
    @Override
    public  void findByCustumerId(Long id){
        List<Point> l=pointRepository.findAllByIdCostumer(id);
         int suma = l.stream().parallel().mapToInt(Point::getPoint).sum();
        for(Point p:l){
            System.out.println("Puntos Totales: " +suma +" De tantos puntos"+ p.getPoint());
        }
       
    }
//     @Override
//    public DebResponseTotal getTotal(DebRequestTotal request) {
//        List<Debt> debts = debtRepository.findByCustomerIdAndTradeIdAndConditions(request.getCustomerId(),request.getTradeId(),request.getConditions());
//        double suma = debts.stream().parallel().mapToDouble(Debt::getTotalAmount).sum();
//        return DebResponseTotal.builder()
//                .totalAmount(suma)
//                .tradeId(request.getTradeId())
//                .customerId(request.getCustomerId())
//                .build();
//    }
//    public int pointTotal(){
//        
//    }
}
