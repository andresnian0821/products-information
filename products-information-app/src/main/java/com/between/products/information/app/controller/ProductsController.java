package com.between.products.information.app.controller;

import com.between.products.information.core.service.ProductsService;
import com.between.products.information.common.dto.response.ProductDetailResponse;
import com.between.products.information.common.routes.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = Routes.BASE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {

    private ProductsService productsServiceImpl;

    @GetMapping (
            value = Routes.SIMILAR_PRODUCTS_DETAIL,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<ProductDetailResponse> getDetailProduct(
            @PathVariable String productId
    ){
        return productsServiceImpl.similarProductsDetail(productId);
    }

    @Autowired
    public void setProductsServiceImpl(ProductsService productsServiceImpl) {
        this.productsServiceImpl = productsServiceImpl;
    }
}
