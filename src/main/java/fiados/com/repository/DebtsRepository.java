package fiados.com.repository;

import fiados.com.models.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtsRepository extends JpaRepository<Debt, Long> {
}
