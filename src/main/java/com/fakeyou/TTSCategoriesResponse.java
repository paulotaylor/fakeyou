package com.fakeyou;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "categories"
})
public class TTSCategoriesResponse {
    @JsonProperty("categories")
    private List<TTSCategory> categories;
    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("categories")
    public List<TTSCategory> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(List<TTSCategory> categories) {
        this.categories = categories;
    }
}