package com.sbs.untact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Board {
	private int id;
	private String regDate;
	private String updateDate;
	private int articleId;
	private int memberId;
	private String code;

	private String name;
}