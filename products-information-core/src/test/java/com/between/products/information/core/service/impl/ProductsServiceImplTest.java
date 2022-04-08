package com.between.products.information.core.service.impl;

import com.between.products.information.client.operations.ProductsOperation;
import com.between.products.information.common.dto.external.response.DetailProductResponse;
import com.between.products.information.common.dto.response.ProductDetailResponse;
import com.between.products.information.common.exception.ProductInformationException;
import com.between.products.information.core.config.BaseConfig;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

@ContextHierarchy({@ContextConfiguration(classes = BaseConfig.class)})
public class ProductsServiceImplTest {

    @Test
    @Order(1)
    public void detailSimilarProductServiceSuccess(){
        ProductsServiceImpl productsService = new ProductsServiceImpl();
        List<Integer> listProducts = new ArrayList<>();
        listProducts.add(1);
        listProducts.add(2);

        List<ProductDetailResponse> listProductsDetails = new ArrayList<>();
        listProductsDetails.add(new ProductDetailResponse("1", "Test", 22.8, true));
        listProductsDetails.add(new ProductDetailResponse("2", "Test1", 11.8, false));

        DetailProductResponse detailProduct = new DetailProductResponse("50", "TestDetail", 33.2, true);

        ProductsOperation productsOperation = mock(ProductsOperation.class);
        productsService.setProductsOperations(productsOperation);
        Mockito.when(productsOperation.similarProducts(Mockito.anyString())).thenReturn(listProducts);
        Mockito.when(productsOperation.detailProduct(Mockito.anyString())).thenReturn(detailProduct);
        Assert.isTrue(productsService.similarProductsDetail("1").size() ==2);
    }

    @Test
    @Order(2)
    public void detailSimilarWithoutSimilars(){
        ProductsServiceImpl productsService = new ProductsServiceImpl();
        List<Integer> listProducts = new ArrayList<>();
        ProductsOperation productsOperation = mock(ProductsOperation.class);
        productsService.setProductsOperations(productsOperation);
        Mockito.when(productsOperation.similarProducts(Mockito.anyString())).thenReturn(listProducts);
        ProductInformationException thrown = Assertions.assertThrows(ProductInformationException.class, () -> {
            productsService.similarProductsDetail("1");
        });
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), thrown.getHttpStatus().value());
    }

    @Test
    @Order(3)
    public void detailSimilarWithoutDetails(){
        ProductsServiceImpl productsService = new ProductsServiceImpl();
        List<Integer> listProducts = new ArrayList<>();
        listProducts.add(1);
        listProducts.add(2);

        ProductsOperation productsOperation = mock(ProductsOperation.class);
        productsService.setProductsOperations(productsOperation);
        Mockito.when(productsOperation.similarProducts(Mockito.anyString())).thenReturn(listProducts);
        Mockito.when(productsOperation.detailProduct(Mockito.anyString())).thenThrow(FeignException.class);
        ProductInformationException thrown = Assertions.assertThrows(ProductInformationException.class, () -> {
            productsService.similarProductsDetail("1");
        });
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), thrown.getHttpStatus().value());
    }

    @Test
    @Order(4)
    public void detailSimilarCatchFeignException(){
        ProductsServiceImpl productsService = new ProductsServiceImpl();
        ProductsOperation productsOperation = mock(ProductsOperation.class);
        productsService.setProductsOperations(productsOperation);
        Mockito.when(productsOperation.similarProducts(Mockito.anyString())).thenThrow(FeignException.class);
        ProductInformationException thrown = Assertions.assertThrows(ProductInformationException.class, () -> {
            productsService.similarProductsDetail("1");
        });
        Assertions.assertEquals(HttpStatus.BAD_GATEWAY.value(), thrown.getHttpStatus().value());
    }


}
