package com.fakeyou;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "model_token",
        "tts_model_type",
        "creator_user_token",
        "creator_username",
        "creator_display_name",
        "creator_gravatar_hash",
        "title",
        "ietf_language_tag",
        "ietf_primary_language_subtag",
        "is_front_page_featured",
        "is_twitch_featured",
        "maybe_suggested_unique_bot_command",
        "category_tokens",
        "created_at",
        "updated_at"
})

public class TTSVoice {

    @JsonProperty("model_token")
    private String modelToken;
    @JsonProperty("tts_model_type")
    private String ttsModelType;
    @JsonProperty("creator_user_token")
    private String creatorUserToken;
    @JsonProperty("creator_username")
    private String creatorUsername;
    @JsonProperty("creator_display_name")
    private String creatorDisplayName;
    @JsonProperty("creator_gravatar_hash")
    private String creatorGravatarHash;
    @JsonProperty("title")
    private String title;
    @JsonProperty("ietf_language_tag")
    private String ietfLanguageTag;
    @JsonProperty("ietf_primary_language_subtag")
    private String ietfPrimaryLanguageSubtag;
    @JsonProperty("is_front_page_featured")
    private Boolean isFrontPageFeatured;
    @JsonProperty("is_twitch_featured")
    private Boolean isTwitchFeatured;
    @JsonProperty("maybe_suggested_unique_bot_command")
    private String maybeSuggestedUniqueBotCommand;
    @JsonProperty("category_tokens")
    private List<String> categoryTokens = null;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;

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

    @JsonProperty("creator_user_token")
    public String getCreatorUserToken() {
        return creatorUserToken;
    }

    @JsonProperty("creator_user_token")
    public void setCreatorUserToken(String creatorUserToken) {
        this.creatorUserToken = creatorUserToken;
    }

    @JsonProperty("creator_username")
    public String getCreatorUsername() {
        return creatorUsername;
    }

    @JsonProperty("creator_username")
    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    @JsonProperty("creator_display_name")
    public String getCreatorDisplayName() {
        return creatorDisplayName;
    }

    @JsonProperty("creator_display_name")
    public void setCreatorDisplayName(String creatorDisplayName) {
        this.creatorDisplayName = creatorDisplayName;
    }

    @JsonProperty("creator_gravatar_hash")
    public String getCreatorGravatarHash() {
        return creatorGravatarHash;
    }

    @JsonProperty("creator_gravatar_hash")
    public void setCreatorGravatarHash(String creatorGravatarHash) {
        this.creatorGravatarHash = creatorGravatarHash;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("ietf_language_tag")
    public String getIetfLanguageTag() {
        return ietfLanguageTag;
    }

    @JsonProperty("ietf_language_tag")
    public void setIetfLanguageTag(String ietfLanguageTag) {
        this.ietfLanguageTag = ietfLanguageTag;
    }

    @JsonProperty("ietf_primary_language_subtag")
    public String getIetfPrimaryLanguageSubtag() {
        return ietfPrimaryLanguageSubtag;
    }

    @JsonProperty("ietf_primary_language_subtag")
    public void setIetfPrimaryLanguageSubtag(String ietfPrimaryLanguageSubtag) {
        this.ietfPrimaryLanguageSubtag = ietfPrimaryLanguageSubtag;
    }

    @JsonProperty("is_front_page_featured")
    public Boolean getIsFrontPageFeatured() {
        return isFrontPageFeatured;
    }

    @JsonProperty("is_front_page_featured")
    public void setIsFrontPageFeatured(Boolean isFrontPageFeatured) {
        this.isFrontPageFeatured = isFrontPageFeatured;
    }

    @JsonProperty("is_twitch_featured")
    public Boolean getIsTwitchFeatured() {
        return isTwitchFeatured;
    }

    @JsonProperty("is_twitch_featured")
    public void setIsTwitchFeatured(Boolean isTwitchFeatured) {
        this.isTwitchFeatured = isTwitchFeatured;
    }

    @JsonProperty("maybe_suggested_unique_bot_command")
    public String getMaybeSuggestedUniqueBotCommand() {
        return maybeSuggestedUniqueBotCommand;
    }

    @JsonProperty("maybe_suggested_unique_bot_command")
    public void setMaybeSuggestedUniqueBotCommand(String maybeSuggestedUniqueBotCommand) {
        this.maybeSuggestedUniqueBotCommand = maybeSuggestedUniqueBotCommand;
    }

    @JsonProperty("category_tokens")
    public List<String> getCategoryTokens() {
        return categoryTokens;
    }

    @JsonProperty("category_tokens")
    public void setCategoryTokens(List<String> categoryTokens) {
        this.categoryTokens = categoryTokens;
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