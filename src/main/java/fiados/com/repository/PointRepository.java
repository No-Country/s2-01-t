package fiados.com.repository;

import fiados.com.models.entity.Point;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findAllByIdCostumer(Long id); 
    List<Point> findAllByIdTrade(Long id); 
}
