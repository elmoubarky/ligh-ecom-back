package org.sid;

import java.util.Random;

import org.sid.entitie.Category;
import org.sid.entitie.Product;
import org.sid.repository.CategoryRepository;
import org.sid.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import net.bytebuddy.utility.RandomString;

@SpringBootApplication
public class LightEcomApplication implements CommandLineRunner{
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	//exposer l'id
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(LightEcomApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//exposer l'id
		repositoryRestConfiguration.exposeIdsFor(Product.class, Category.class);
		
		categoryRepository.save(new Category(null, "Ordinateurs",null, null, null));
		categoryRepository.save(new Category(null, "Mobile",null, null, null));
		categoryRepository.save(new Category(null, "Printers", null, null, null));
		categoryRepository.save(new Category(null, "Smart Phones",null, null, null));
		
		Random rd = new Random();
		categoryRepository.findAll().forEach(c->{
			
			for(int i=0; i<10; i++) {
				Product p = new Product();
				p.setName(RandomString.make(18));
				p.setCurrentPrice(100+rd.nextInt(1000));
				p.setDescription(RandomString.make(25));
				p.setAvailable(rd.nextBoolean());
				p.setPromotion(rd.nextBoolean());
				p.setSelected(rd.nextBoolean());
				p.setPhotoName("image1.png");
				p.setCategory(c);
				productRepository.save(p);
			}
			
			
		});
		
	}

}
