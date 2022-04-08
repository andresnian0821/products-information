package com.between.products.information.core.service.impl;

import com.between.products.information.client.operations.ProductsOperation;
import com.between.products.information.core.service.ProductsService;
import com.between.products.information.common.dto.external.response.DetailProductResponse;
import com.between.products.information.common.dto.response.ProductDetailResponse;
import com.between.products.information.common.exception.ProductInformationException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductsServiceImpl implements ProductsService {

    private Logger logger = LoggerFactory.getLogger(ProductsServiceImpl.class);

    private ProductsOperation productsOperation;

    @Override
    public List<ProductDetailResponse> similarProductsDetail(String productId) throws ProductInformationException {
        try{
            List<ProductDetailResponse> listProductDetails = new ArrayList<>();
            List<Integer> similarProducts = productsOperation.similarProducts(productId);

            if(!similarProducts.isEmpty()){

                similarProducts.parallelStream().forEach(product -> {
                    ProductDetailResponse detail = getDetailProduct(product.toString());
                    if(detail != null) listProductDetails.add(detail);
                });
                if (listProductDetails.isEmpty()){
                    throw new ProductInformationException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
                }
            }else{
                throw new ProductInformationException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            return listProductDetails;
        }catch (FeignException ex){
            throw new ProductInformationException(HttpStatus.BAD_GATEWAY, HttpStatus.BAD_GATEWAY.getReasonPhrase());
        }
    }

    public ProductDetailResponse getDetailProduct(String productId){
        try{
            DetailProductResponse detailProduct = this.productsOperation.detailProduct(productId);
            return new ProductDetailResponse(detailProduct.getId(), detailProduct.getName(), detailProduct.getPrice(), detailProduct.getAvailability());
        }catch (FeignException feignException){
            logger.error(feignException.getMessage());
            return null;
        }
    }

    @Autowired
    public void setProductsOperations(ProductsOperation productsOperation) {
        this.productsOperation = productsOperation;
    }
}
