package com.tollywood2bollywood.t2bliveapp;

/**
 * Created by JAI JALARAM on 05-02-2017.
 */

public class DaynamicTabModel {
    private String id,type,slug,url,status,title,title_plain,content,excerpt,date,modified,categories,tags,author,comments,attachments,comment_count,comment_status,thumbnail,custom_fields,thumbnail_size,thumbnail_images;

    public DaynamicTabModel(String id, String type, String slug, String url, String status, String title, String title_plain, String content, String excerpt, String date, String modified, String categories, String tags, String author, String comments, String attachments, String comment_count, String comment_status, String thumbnail, String custom_fields, String thumbnail_size, String thumbnail_images) {
        this.id = id;
        this.type = type;
        this.slug = slug;
        this.url = url;
        this.status = status;
        this.title = title;
        this.title_plain = title_plain;
        this.content = content;
        this.excerpt = excerpt;
        this.date = date;
        this.modified = modified;
        this.categories = categories;
        this.tags = tags;
        this.author = author;
        this.comments = comments;
        this.attachments = attachments;
        this.comment_count = comment_count;
        this.comment_status = comment_status;
        this.thumbnail = thumbnail;
        this.custom_fields = custom_fields;
        this.thumbnail_size = thumbnail_size;
        this.thumbnail_images = thumbnail_images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_plain() {
        return title_plain;
    }

    public void setTitle_plain(String title_plain) {
        this.title_plain = title_plain;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getComment_status() {
        return comment_status;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCustom_fields() {
        return custom_fields;
    }

    public void setCustom_fields(String custom_fields) {
        this.custom_fields = custom_fields;
    }

    public String getThumbnail_size() {
        return thumbnail_size;
    }

    public void setThumbnail_size(String thumbnail_size) {
        this.thumbnail_size = thumbnail_size;
    }

    public String getThumbnail_images() {
        return thumbnail_images;
    }

    public void setThumbnail_images(String thumbnail_images) {
        this.thumbnail_images = thumbnail_images;
    }
}
