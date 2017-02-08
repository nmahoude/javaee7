package concurrency.application.boundary;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import concurrency.application.entity.Customer;

public class Resource {
  static Map<Long, Customer> customers = new HashMap<>();
  static {
    Customer a = new Customer();
    a.setId(1L);
    a.setName("a");
    customers.put(1L, a);
    
    
  }
  
  @GET
  @Path("{id}")
  public Customer getCustomer(@PathParam("id") Long id) {
    if (id != 1L) {
      throw new NotFoundException();
    }
    return customers.get(id);
  }
}
