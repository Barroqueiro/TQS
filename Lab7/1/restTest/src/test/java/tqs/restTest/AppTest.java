package tqs.restTest;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void listAll_isAvailable()
    {
    	get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200);
    }
    
    @Test
    public void testTitle_todo4() {
    	get("https://jsonplaceholder.typicode.com/todos/4").then().body("title",equalTo("et porro tempora"));
    }
    
    @Test
    public void items198and199_exist() {
    	get("https://jsonplaceholder.typicode.com/todos").then().body("id",hasItems(198,199));
    }
}
