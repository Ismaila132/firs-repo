package com.example.demo;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SbCoffee1Application {

	public static void main(String[] args) {
		SpringApplication.run(SbCoffee1Application.class, args);
	}

}
class Coffee 
{
	private final String id;
	private String name;
	
	public Coffee(String id, String name)
	{
		this.id = id;
		this.name = name;
	}
	public Coffee(String name)
	{
		this(UUID.randomUUID().toString(),name);
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
}
@RestController
@RequestMapping("/coffees")
class RestApiCoffeeController
{
	private List<Coffee> coffees = new ArrayList<>();

	public RestApiCoffeeController()
	{
		coffees.addAll(List.of(
			new Coffee("Café Nescafé"),
			new Coffee("Café Touba"),
			new Coffee("Café Expresso"),
			new Coffee("Café Crême")
		));
	}
	/*@GetMapping
	Iterable<Coffee> getCoffees()
	{
		return coffees;
	}*/
	@GetMapping("/{id}")
	Optional<Coffee> getCoffeeById(@PathVariable String id) {
		for (Coffee c: coffees) {
			if (c.getId().equals(id)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}
}
