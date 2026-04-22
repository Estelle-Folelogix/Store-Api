package com.estelle.store.product.presentation;

import com.estelle.store.product.domain.model.Product;
import com.estelle.store.product.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{prodId}")
    public ResponseEntity<Product> getProduct(@PathVariable int prodId){

        Product prod = service.getProduct(prodId);

        if (prod != null)
            return new ResponseEntity<>(prod, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product") //this method was working well in postman but not in swagger, i,ve try the other one below for both
                                                               // because swagger was reading my prod as a string instead of a json
    public ResponseEntity<?> createProduct(
            @RequestPart("prod") String prodJson,
            @RequestPart("imageFile") MultipartFile imageFile) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            Product prod = mapper.readValue(prodJson, Product.class);

            Product product = service.createProduct(prod, imageFile);
            return new ResponseEntity<>(product, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@PostMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProduct(
            @RequestPart("prod") Product prod, // Spring manage the JSON itself here
            @RequestPart("imageFile") MultipartFile imageFile) {

        try {
            // no manual ObjectMapper needed, prod is already an Product objet
            Product product = service.createProduct(prod, imageFile);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (IOException e) {
            // Error related at the image treatment
            return new ResponseEntity<>("Error while treating image : " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // others errors (validation, database etc.)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }*/


    //Method to fetch the image which comes with product
    @GetMapping("/product/{prodId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int prodId){

        Product prod = service.getProduct(prodId);
        byte[] imageFile = prod.getImageData();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(prod.getImageType()))
                .body(imageFile);
    }

    @PutMapping("/product/{prodId}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer prodId, @RequestPart("prod") Product prod,
                                                @RequestPart("imageFile") MultipartFile imageFile){
        Product product = null;
        try {
            product = service.updateProduct(prodId, prod, imageFile);
            return (product != null)
                    ? new ResponseEntity<>("Updated", HttpStatus.OK)
                    : new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{prodId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int prodId){
        Product prod = service.getProduct(prodId);
        if (prod != null){
            service.deleteProduct(prodId);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Failed to delete", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products = service.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
