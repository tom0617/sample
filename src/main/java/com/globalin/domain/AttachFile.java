package com.globalin.domain;

public class AttachFile {
	
	private String fileName;
	private String uploadPath;
	private String uuid;
	//이미지 파일인지 아닌지
	private boolean image;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public boolean isImage() {
		return image;
	}
	public void setImage(boolean image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "AttachFile [fileName=" + fileName + ", uploadPath=" + uploadPath + ", uuid=" + uuid + ", image=" + image
				+ "]";
	}

}
