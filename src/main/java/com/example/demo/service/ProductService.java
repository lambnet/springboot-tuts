package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.StockDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity add(ProductDto request){
        ProductEntity product = new ProductEntity();
        product.setName(request.productName());
        product.setPrice(request.price());
        product.setStock(request.stock());

        return productRepository.save(product);
    }
    //  Task - API to get available product under certain price
    public List<ProductEntity> fetchAll(boolean isAvailable,long price){

        if(isAvailable){
            return productRepository.findByStockGreaterThanAndPriceLessThanEqual(0, price);
        }else{
            return (List<ProductEntity>) productRepository.findAll();
        }

    }

    public void delete(long id){
         productRepository.deleteById(id);
    }

    public ProductEntity getItemById(long id){
        return productRepository.findById(id).orElse(new ProductEntity());
    }

    public ProductEntity updateStock(StockDto request){
        ProductEntity product = getItemById(request.id());

        product.setStock(product.getStock() + request.numberOfStock());

        return productRepository.save(product);

    }



}
