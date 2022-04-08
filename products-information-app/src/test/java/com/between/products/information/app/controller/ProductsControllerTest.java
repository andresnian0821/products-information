package com.between.products.information.app.controller;

import com.between.products.information.app.config.BaseConfig;
import com.between.products.information.common.dto.response.ProductDetailResponse;
import com.between.products.information.core.service.ProductsService;
import com.between.products.information.core.service.impl.ProductsServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.util.Assert;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

@ContextHierarchy({@ContextConfiguration(classes = BaseConfig.class)})
public class ProductsControllerTest {

    @Test
    @Order(1)
    public void detailSimilarProductSuccess(){
        ProductsController productsController = new ProductsController();
        List<ProductDetailResponse> listProducts = new ArrayList<>();
        listProducts.add(new ProductDetailResponse("1", "Test", 22.8, true));
        listProducts.add(new ProductDetailResponse("2", "Test1", 11.8, false));
        ProductsService productsServiceImpl = mock(ProductsServiceImpl.class);
        productsController.setProductsServiceImpl(productsServiceImpl);
        Mockito.when(productsServiceImpl.similarProductsDetail(Mockito.any())).thenReturn(listProducts);
        Assert.isTrue(productsController.getDetailProduct("1").size() ==2);
    }

}
