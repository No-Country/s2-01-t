package fiados.com.repository;

import fiados.com.models.entity.Debt;
import fiados.com.models.enums.EnumCondition;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
    //  List<Debt> findByCustomer(Customer customer);
//    Debt findByTrade();
    
    
//    Select  customer_id, trade_id, sum(total_amount) as Total  
// From trusted.debt 
// where customer_id=2 and conditions="ACTIVATED" 
// group by customer_id,trade_id ;
    
    
    //jpql ->value = "SELECT b.customer, b.trade, sum(b.totalAmount) as Total FROM Debt b WHERE Customer=id AND conditions='ACTIVATED'"
//        + "GROUP BY  b.Customer,b.Trade"
    
//        @Query(value = "SELECT b.customer_id, b.trade_id, sum(b.total_amount) as Total " +
//                        " FROM debt b" +
//                       " WHERE b.customer_id=id and b.conditions='ACTIVATED'" +
//                       " GROUP BY b.customer_id,b.trade_id ;", nativeQuery=true )
           
         List<Debt> findByCustomerIdAndTradeIdAndConditions(Long id_c, Long id_t, EnumCondition condition); 
}
