package fiados.com.repository;

import fiados.com.models.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    Trade findByEmail(String email);

    List<Trade> findByFirstNameAndCity(String firstName, String city);
}
