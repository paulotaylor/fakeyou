package com.fakeyou;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "inference_job_token"
})
public class TTSResponse {

    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("inference_job_token")
    private String inferenceJobToken;

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("inference_job_token")
    public String getInferenceJobToken() {
        return inferenceJobToken;
    }

    @JsonProperty("inference_job_token")
    public void setInferenceJobToken(String inferenceJobToken) {
        this.inferenceJobToken = inferenceJobToken;
    }

}