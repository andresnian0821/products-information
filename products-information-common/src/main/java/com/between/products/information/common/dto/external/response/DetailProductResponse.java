package com.between.products.information.common.dto.external.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailProductResponse {
    private String id;
    private String name;
    private Double price;
    private Boolean availability;

    @Override
    public String toString(){
        return new GsonBuilder().create().toJson(this);
    }
}
