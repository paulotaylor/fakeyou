package com.fakeyou;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "job_token",
        "status",
        "maybe_extra_status_description",
        "attempt_count",
        "maybe_result_token",
        "maybe_public_bucket_wav_audio_path",
        "model_token",
        "tts_model_type",
        "title",
        "raw_inference_text",
        "created_at",
        "updated_at"
})
public class State {

    @JsonProperty("job_token")
    private String jobToken;
    @JsonProperty("status")
    private String status;
    @JsonProperty("maybe_extra_status_description")
    private Object maybeExtraStatusDescription;
    @JsonProperty("attempt_count")
    private Integer attemptCount;
    @JsonProperty("maybe_result_token")
    private String maybeResultToken;
    @JsonProperty("maybe_public_bucket_wav_audio_path")
    private String maybePublicBucketWavAudioPath;
    @JsonProperty("model_token")
    private String modelToken;
    @JsonProperty("tts_model_type")
    private String ttsModelType;
    @JsonProperty("title")
    private String title;
    @JsonProperty("raw_inference_text")
    private String rawInferenceText;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("job_token")
    public String getJobToken() {
        return jobToken;
    }

    @JsonProperty("job_token")
    public void setJobToken(String jobToken) {
        this.jobToken = jobToken;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("maybe_extra_status_description")
    public Object getMaybeExtraStatusDescription() {
        return maybeExtraStatusDescription;
    }

    @JsonProperty("maybe_extra_status_description")
    public void setMaybeExtraStatusDescription(Object maybeExtraStatusDescription) {
        this.maybeExtraStatusDescription = maybeExtraStatusDescription;
    }

    @JsonProperty("attempt_count")
    public Integer getAttemptCount() {
        return attemptCount;
    }

    @JsonProperty("attempt_count")
    public void setAttemptCount(Integer attemptCount) {
        this.attemptCount = attemptCount;
    }

    @JsonProperty("maybe_result_token")
    public String getMaybeResultToken() {
        return maybeResultToken;
    }

    @JsonProperty("maybe_result_token")
    public void setMaybeResultToken(String maybeResultToken) {
        this.maybeResultToken = maybeResultToken;
    }

    @JsonProperty("maybe_public_bucket_wav_audio_path")
    public String getMaybePublicBucketWavAudioPath() {
        return maybePublicBucketWavAudioPath;
    }

    @JsonProperty("maybe_public_bucket_wav_audio_path")
    public void setMaybePublicBucketWavAudioPath(String maybePublicBucketWavAudioPath) {
        this.maybePublicBucketWavAudioPath = maybePublicBucketWavAudioPath;
    }

    @JsonProperty("model_token")
    public String getModelToken() {
        return modelToken;
    }

    @JsonProperty("model_token")
    public void setModelToken(String modelToken) {
        this.modelToken = modelToken;
    }

    @JsonProperty("tts_model_type")
    public String getTtsModelType() {
        return ttsModelType;
    }

    @JsonProperty("tts_model_type")
    public void setTtsModelType(String ttsModelType) {
        this.ttsModelType = ttsModelType;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("raw_inference_text")
    public String getRawInferenceText() {
        return rawInferenceText;
    }

    @JsonProperty("raw_inference_text")
    public void setRawInferenceText(String rawInferenceText) {
        this.rawInferenceText = rawInferenceText;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}