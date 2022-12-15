package com.fakeyou;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "models"
})
public class TTSVoicesResponse {
    @JsonProperty("models")
    private List<TTSVoice> models;
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


    @JsonProperty("models")
    public List<TTSVoice> getModels() {
        return models;
    }

    @JsonProperty("models")
    public void setModels(List<TTSVoice> models) {
        this.models = models;
    }
}