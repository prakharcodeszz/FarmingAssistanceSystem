package com.fas.farmer.service;

import com.fas.farmer.dtos.AddProductRequest;
import com.fas.farmer.dtos.ProductDetails;
import com.fas.farmer.dtos.UserDetails;
import com.fas.farmer.entities.Farmer;
import com.fas.farmer.entities.Product;
import com.fas.farmer.exceptions.FarmerNotFoundException;
import com.fas.farmer.exceptions.ProductNotFoundException;
import com.fas.farmer.repository.IFarmersRepository;
import com.fas.farmer.repository.IProductRepository;
import com.fas.farmer.utils.FarmersUtil;
import com.fas.farmer.utils.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IFarmersRepository farmersrepository;


    @Autowired
    private ProductUtil productUtil;
    @Autowired
    private FarmersUtil farmersUtil;


    @Override
    public ProductDetails addProduct(AddProductRequest addProductRequest) {
        Product product = new Product();
        product.setName(addProductRequest.getName());
        product.setQuantity(addProductRequest.getQuantity());
        product.setPrice(addProductRequest.getPrice());
        product.setFarmerId(addProductRequest.getFarmerId());
        productRepository.save(product);
        return productUtil.toProductDetails(product);
    }

    @Override
    public ProductDetails getProductDetails(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(!productOptional.isPresent())
            throw new ProductNotFoundException("Product not found for id: "+productId);
        Product product = productOptional.get();
        return productUtil.toProductDetails(product);
    }

    @Override
    public List<ProductDetails> getProductsByPincode(Long pincode) {
        List<Product> productList = productRepository.findAll();
        List<ProductDetails> productDetails = productUtil.toProductDetailsList(productList);
        return productDetails.stream().filter(obj -> obj.getPincode() >= pincode - 1 && obj.getPincode() <= pincode + 1).collect(Collectors.toList());
    }

    @Override
    public List<ProductDetails> getProductsByFarmerId(Long farmerId) {
        Optional<Farmer> farmerOptional = farmersrepository.findById(farmerId);
        if(!farmerOptional.isPresent())
            throw new FarmerNotFoundException("Farmer not found for id: "+farmerId);
        Farmer farmer =  farmerOptional.get();
        UserDetails userDetails = farmersUtil.getUserDetails(farmer.getUsername());
        farmersUtil.isFarmerLoggedIn(userDetails);

        List<Product> productList = productRepository.getProductsByFarmerId(farmerId);
        return productUtil.toProductDetailsList(productList);
    }
}
