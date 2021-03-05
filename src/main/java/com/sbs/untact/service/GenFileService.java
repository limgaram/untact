package com.sbs.untact.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sbs.untact.dao.GenFileDao;
import com.sbs.untact.dto.GenFile;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.util.Util;

@Service
public class GenFileService {

	@Value("${custom.genFileDirPath}")
	private String genFileDirPath;

	@Autowired
	private GenFileDao genFileDao;

	public ResultData saveMeta(String relTypeCode, int relId, String typeCode, String type2Code, int fileNo,
			String originFileName, String fileExtTypeCode, String fileExtType2Code, String fileExt, int fileSize,
			String fileDir) {

		Map<String, Object> param = Util.mapOf("relTypeCode", relTypeCode, "relId", relId, "typeCode", typeCode,
				"type2Code", type2Code, "fileNo", fileNo, "originFileName", originFileName, "fileExtTypeCode",
				fileExtTypeCode, "fileExtType2Code", fileExtType2Code, "fileExt", fileExt, "fileSize", fileSize,
				"fileDir", fileDir);
		genFileDao.saveMeta(param);

		int id = Util.getAsInt(param.get("id"), 0);
		return new ResultData("S-1", "성공하였습니다.", "id", id);
	}

	public ResultData save(MultipartFile multipartFile, int relId) {

		String fileInputName = multipartFile.getName();
		String[] fileInputNameBits = fileInputName.split("__");

		if (fileInputNameBits[0].equals("file") == false) {
			return new ResultData("F-1", "파라미터 명이 올바르지 않습니다.");
		}

		int fileSize = (int) multipartFile.getSize();

		if (fileSize <= 0) {
			return new ResultData("F-2", "파일이 업로드 되지 않았습니다.");
		}

		String relTypeCode = fileInputNameBits[1];
		;
		String typeCode = fileInputNameBits[3];
		String type2Code = fileInputNameBits[4];
		int fileNo = Integer.parseInt(fileInputNameBits[5]);
		String originFileName = multipartFile.getOriginalFilename();
		String fileExtTypeCode = Util.getFileExtTypeCodeFromFileName(multipartFile.getOriginalFilename());
		String fileExtType2Code = Util.getFileExtType2CodeFromFileName(multipartFile.getOriginalFilename());
		String fileExt = Util.getFileExtFromFileName(multipartFile.getOriginalFilename()).toLowerCase();

		if (fileExt.equals("jpeg")) {
			fileExt = "jpg";
		} else if (fileExt.equals("htm")) {
			fileExt = "html";
		}

		String fileDir = Util.getNowYearMonthDateStr();

		ResultData saveMetaRd = saveMeta(relTypeCode, relId, typeCode, type2Code, fileNo, originFileName,
				fileExtTypeCode, fileExtType2Code, fileExt, fileSize, fileDir);
		int newGenFileId = (int) saveMetaRd.getBody().get("id");

		// 새 파일이 저장될 폴더(io파일) 객체 생성
		String targetDirPath = genFileDirPath + "/" + relTypeCode + "/" + fileDir;
		java.io.File targetDir = new java.io.File(targetDirPath);

		// 새 파일이 저장될 폴더가 존재하지 않는다면 생성
		if (targetDir.exists() == false) {
			targetDir.mkdirs();
		}

		String targetFileName = newGenFileId + "." + fileExt;
		String targetFilePath = targetDirPath + "/" + targetFileName;

		// 파일 생성(업로드된 파일을 지정된 경로로 옮김)
		try {
			multipartFile.transferTo(new File(targetFilePath));
		} catch (IllegalStateException | IOException e) {
			return new ResultData("F-3", "파일저장에 실패하였습니다.");
		}

		return new ResultData("S-1", "파일이 생성되었습니다.", "id", newGenFileId, "fileRealPath", targetFilePath, "fileName",
				targetFileName);
	}

	public GenFile getGenFile(String relTypeCode, int relId, String typeCode, String type2Code, int fileNo) {
		return genFileDao.getGenFile(relTypeCode, relId, typeCode, type2Code, fileNo);
	}
}
