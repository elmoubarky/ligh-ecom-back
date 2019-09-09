package org.sid.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.sid.entitie.Product;
import org.sid.repository.ProductRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
public class CatalogueRestController {
	
	private ProductRepository productRepository;

	public CatalogueRestController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping(path = "/photoProduct/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getPhoto (@PathVariable("id")  Long id) throws Exception {
		
		Product p = productRepository.findById(id).get();
		//System.out.println("photo name = "+p.getPhotoName());
		String path = System.getProperty("user.home")+"/ecom/product/"+p.getPhotoName();
		//System.out.println("photo path = "+path);
		return Files.readAllBytes(Paths.get(path));
		
	}
	
	@PostMapping(path="/uploadPhoto/{id}")
	public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception{

		Product p = productRepository.findById(id).get();
		String filename = file.getOriginalFilename();
		p.setPhotoName(filename);	
		String path = System.getProperty("user.home")+"/ecom/product/"+filename;
	//	System.out.println("photo path = "+path);
		//System.out.println("photo bites = "+file.getBytes());
		Files.write(Paths.get(path), file.getBytes());
		productRepository.save(p);
	}
	
	

}
