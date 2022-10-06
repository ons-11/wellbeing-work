package tn.esprit.spring.wecare.controllers;

public class ImageUploadResponce {
	private String message;

    public ImageUploadResponce(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    } 
}
