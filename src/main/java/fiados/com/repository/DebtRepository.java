package fiados.com.repository;

import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Debt;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
    //  List<Debt> findByCustomer(Customer customer);
//    Debt findByTrade();
}
