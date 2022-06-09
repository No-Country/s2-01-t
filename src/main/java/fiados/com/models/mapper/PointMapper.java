package fiados.com.models.mapper;

import fiados.com.models.entity.Point;
import fiados.com.models.request.PointRequest;
import fiados.com.models.response.PointResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PointMapper {
    public Point entity2DTO(PointRequest request) {
        Point point = new Point();
        point.setPoint(request.getPoint());
        return point;
    }
    public PointResponse DTO2Entity(Point point) {
        PointResponse response = new PointResponse();
        response.setId(point.getId());
        response.setIdCostumer(point.getIdCostumer());
        response.setIdTrade(point.getIdTrade());
        response.setPoint_client(point.getPoint());
        return response;
    }

    public List<PointResponse> entitySet2DtoList(List<Point> points) {
        List<PointResponse> responses = new ArrayList<>();
        points.forEach(point -> {
            responses.add(DTO2Entity(point));
        });
        return responses;
    }
}
