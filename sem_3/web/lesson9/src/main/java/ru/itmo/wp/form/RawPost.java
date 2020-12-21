package ru.itmo.wp.form;

import javax.validation.constraints.*;

public class RawPost {
    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String text;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 60)
    @Pattern(regexp = "([a-z]+[\\s]*)*", message = "Expected Latin letters and whitespace symbols")
    private String tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
