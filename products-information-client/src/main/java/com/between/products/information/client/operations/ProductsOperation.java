package com.between.products.information.client.operations;

import com.between.products.information.client.config.FeignConfiguration;
import com.between.products.information.common.dto.external.response.DetailProductResponse;
import com.between.products.information.common.routes.ExternalRoutes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Import(FeignConfiguration.class)
@FeignClient(
        name = ExternalRoutes.PRODUCT_OPERATION_NAME,
        url = "${client.api.similar.products.url}",
        configuration = FeignConfiguration.class
)
public interface ProductsOperation {

    @GetMapping(
            value = ExternalRoutes.BASE + ExternalRoutes.SIMILAR_PRODUCTS_SERVICE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<Integer> similarProducts(@PathVariable String productId);


    @GetMapping(
            value = ExternalRoutes.BASE + ExternalRoutes.DETAIL_PRODUCT_SERVICE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    DetailProductResponse detailProduct(@PathVariable String productId);
}
