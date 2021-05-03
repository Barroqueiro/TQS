package tqs.testContainers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContainersTest {
  @Container
  public static PostgreSQLContainer container = new PostgreSQLContainer()
    .withUsername("Dinis")
    .withPassword("GandaPasswod")
    .withDatabaseName("Machine");

  @Autowired
  private MachineRepository bookRepository;
  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", container::getJdbcUrl);
    registry.add("spring.datasource.password", container::getPassword);
    registry.add("spring.datasource.username", container::getUsername);
  }

  @Test
  @Order(1)
  void contextLoads() {

    Machine tt = new Machine();
    tt.setName("Tico Tico");
    tt.setModel("TR-5000");
    
    Machine se = new Machine();
    se.setName("Serra Eletrica");
    se.setModel("M-60");

    bookRepository.save(tt);
    bookRepository.save(se);

  }
  
  @Test 
  @Order(2)
  void readMachines(){
	  List<Machine> machines = bookRepository.findAll();
	  assertThat(machines.get(0).getName(), is("Tico Tico"));
	  assertThat(machines.get(0).getModel(), is("TR-5000"));
	  assertThat(machines.get(1).getName(), is("Serra Eletrica"));
	  assertThat(machines.get(1).getModel(), is("M-60"));
	  
  }

}
