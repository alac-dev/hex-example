package example.prices.application;

import example.prices.domain.Entity;
import example.prices.infraestructure.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PricesService {
  
  private final Repository repository;

  public PricesService(Repository repository) {
    this.repository = repository;
  }
  
  public Entity getPrices() {
    return this.repository.getPrices();
  }
}
