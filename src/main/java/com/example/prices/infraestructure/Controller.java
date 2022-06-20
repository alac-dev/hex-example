package example.prices.infraestructure;

import example.prices.application.PricesService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  
  private final PricesService pricesService;

  public Controller(PricesService pricesService) {
    this.pricesService = pricesService;
  }
  
  //metodos
  
  //get
}
