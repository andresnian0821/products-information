package com.between.products.information.core.service;

import com.between.products.information.common.dto.response.ProductDetailResponse;
import java.util.List;

public interface ProductsService {
    List<ProductDetailResponse> similarProductsDetail(String productId);
}
