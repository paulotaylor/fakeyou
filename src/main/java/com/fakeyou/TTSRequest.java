package com.fakeyou;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uuid_idempotency_token",
        "tts_model_token",
        "inference_text"
})
public class TTSRequest {

    @JsonProperty("uuid_idempotency_token")
    private String uuidIdempotencyToken;
    @JsonProperty("tts_model_token")
    private String ttsModelToken;
    @JsonProperty("inference_text")
    private String inferenceText;

    @JsonProperty("uuid_idempotency_token")
    public String getUuidIdempotencyToken() {
        return uuidIdempotencyToken;
    }

    @JsonProperty("uuid_idempotency_token")
    public void setUuidIdempotencyToken(String uuidIdempotencyToken) {
        this.uuidIdempotencyToken = uuidIdempotencyToken;
    }

    @JsonProperty("tts_model_token")
    public String getTtsModelToken() {
        return ttsModelToken;
    }

    @JsonProperty("tts_model_token")
    public void setTtsModelToken(String ttsModelToken) {
        this.ttsModelToken = ttsModelToken;
    }

    @JsonProperty("inference_text")
    public String getInferenceText() {
        return inferenceText;
    }

    @JsonProperty("inference_text")
    public void setInferenceText(String inferenceText) {
        this.inferenceText = inferenceText;
    }

}