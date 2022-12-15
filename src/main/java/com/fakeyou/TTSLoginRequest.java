package com.fakeyou;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "username_or_email",
        "password"
})

public class TTSLoginRequest {
    @JsonProperty("username_or_email")
    private final String usernameOrEmail;
    @JsonProperty("password")
    private final String password;

    public TTSLoginRequest(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    @JsonProperty("username_or_email")
    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }
}