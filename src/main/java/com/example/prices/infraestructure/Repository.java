package example.prices.infraestructure;

import example.prices.domain.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Entity, String> {

}
