/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplerestfull.projectrestfull;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Inspiron
 */
@RestController
public class ProductServiceController {
    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        Product Kue = new Product();
        Kue.setId("1");
        Kue.setName("Pancake");
        Kue.setTotal("5");
        Kue.setHarga("10000");
        productRepo.put(Kue.getId(), Kue);
        
        Product Minuman = new Product();
        Minuman.setId("2");
        Minuman.setName("Caramel Macchiato");
        Minuman.setTotal("5");
        Minuman.setHarga("20000");
        productRepo.put(Minuman.getId(), Minuman);
    }
    
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        productRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        if(!productRepo.containsKey(id)){
            return new ResponseEntity<>("Product Not Found, Please check again", HttpStatus.NOT_FOUND);
        }
        else{
            productRepo.remove(id);
            product.setId(id);
            productRepo.put(id, product);
            return new  ResponseEntity<>("Product is updated Successfully",HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        if(productRepo.containsKey(product.getId())){ 
            return new ResponseEntity<>("ID Product Cannot be the Same, please check again", HttpStatus.OK);
        }
        else{
            productRepo.put(product.getId(), product);
            return new ResponseEntity<>("Product is created Successfully", HttpStatus.CREATED);
        }
    }
    
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }

    
}
