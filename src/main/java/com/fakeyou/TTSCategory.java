package com.fakeyou;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "category_token",
        "model_type",
        "maybe_super_category_token",
        "can_directly_have_models",
        "can_have_subcategories",
        "can_only_mods_apply",
        "name",
        "name_for_dropdown",
        "is_mod_approved",
        "created_at",
        "updated_at",
        "deleted_at"
})
public class TTSCategory {

    @JsonProperty("category_token")
    private String categoryToken;
    @JsonProperty("model_type")
    private String modelType;
    @JsonProperty("maybe_super_category_token")
    private String maybeSuperCategoryToken;
    @JsonProperty("can_directly_have_models")
    private Boolean canDirectlyHaveModels;
    @JsonProperty("can_have_subcategories")
    private Boolean canHaveSubcategories;
    @JsonProperty("can_only_mods_apply")
    private Boolean canOnlyModsApply;
    @JsonProperty("name")
    private String name;
    @JsonProperty("name_for_dropdown")
    private String nameForDropdown;
    @JsonProperty("is_mod_approved")
    private Boolean isModApproved;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("deleted_at")
    private Object deletedAt;

    @JsonProperty("category_token")
    public String getCategoryToken() {
        return categoryToken;
    }

    @JsonProperty("category_token")
    public void setCategoryToken(String categoryToken) {
        this.categoryToken = categoryToken;
    }

    @JsonProperty("model_type")
    public String getModelType() {
        return modelType;
    }

    @JsonProperty("model_type")
    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    @JsonProperty("maybe_super_category_token")
    public String getMaybeSuperCategoryToken() {
        return maybeSuperCategoryToken;
    }

    @JsonProperty("maybe_super_category_token")
    public void setMaybeSuperCategoryToken(String maybeSuperCategoryToken) {
        this.maybeSuperCategoryToken = maybeSuperCategoryToken;
    }

    @JsonProperty("can_directly_have_models")
    public Boolean getCanDirectlyHaveModels() {
        return canDirectlyHaveModels;
    }

    @JsonProperty("can_directly_have_models")
    public void setCanDirectlyHaveModels(Boolean canDirectlyHaveModels) {
        this.canDirectlyHaveModels = canDirectlyHaveModels;
    }

    @JsonProperty("can_have_subcategories")
    public Boolean getCanHaveSubcategories() {
        return canHaveSubcategories;
    }

    @JsonProperty("can_have_subcategories")
    public void setCanHaveSubcategories(Boolean canHaveSubcategories) {
        this.canHaveSubcategories = canHaveSubcategories;
    }

    @JsonProperty("can_only_mods_apply")
    public Boolean getCanOnlyModsApply() {
        return canOnlyModsApply;
    }

    @JsonProperty("can_only_mods_apply")
    public void setCanOnlyModsApply(Boolean canOnlyModsApply) {
        this.canOnlyModsApply = canOnlyModsApply;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("name_for_dropdown")
    public String getNameForDropdown() {
        return nameForDropdown;
    }

    @JsonProperty("name_for_dropdown")
    public void setNameForDropdown(String nameForDropdown) {
        this.nameForDropdown = nameForDropdown;
    }

    @JsonProperty("is_mod_approved")
    public Boolean getIsModApproved() {
        return isModApproved;
    }

    @JsonProperty("is_mod_approved")
    public void setIsModApproved(Boolean isModApproved) {
        this.isModApproved = isModApproved;
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

    @JsonProperty("deleted_at")
    public Object getDeletedAt() {
        return deletedAt;
    }

    @JsonProperty("deleted_at")
    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

}