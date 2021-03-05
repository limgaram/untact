package com.sbs.untact.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenFile {
	private int id;
	private String regDate;
	private String updateDate;
	private boolean delStatus;
	private String delDate;
	private String typeCode;
	private String type2Code;
	private String relTypeCode;
	private int relId;
	private String fileExtTypeCode;
	private String fileExtType2Code;
	private int fileSize;
	private int fileNo;
	private String fileExt;
	private String fileDir;
	private String originFileName;

	@JsonIgnore
	public String getFilePath(String genFileDirPath) {
		return genFileDirPath + getBaseFileUrl();
	}

	private String getBaseFileUrl() {
		return "/" + relTypeCode + "/" + fileDir + "/" + getFileName();
	}

	private String getFileName() {
		return id + "." + fileExt;
	}

	public String getForPrintUrl() {
		return "/gen" + getBaseFileUrl() + "?updateDate=" + updateDate;
	}
}
