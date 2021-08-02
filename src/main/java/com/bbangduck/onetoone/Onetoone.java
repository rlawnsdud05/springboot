package com.bbangduck.onetoone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data @Entity 
public class Onetoone {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	private String qnaTitle;
	private String qnaAuthor;
	private String qnaContent;
	private Long qnaDate;
	private String qnaPwd;
	private String qnaState;
	
}
