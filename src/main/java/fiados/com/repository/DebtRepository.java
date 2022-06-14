package fiados.com.repository;

import fiados.com.models.entity.Debt;
import java.util.List;

import fiados.com.models.enums.EnumCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {

    List<Debt> findByCustomerIdAndTradeIdAndConditions(Long customerId, Long tradeId, EnumCondition conditions);

}
