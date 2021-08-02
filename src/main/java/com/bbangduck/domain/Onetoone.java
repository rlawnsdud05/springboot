package com.bbangduck.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data // getter/setter
@Entity // 서버 실행시 ORM이 됨(테이블이 생성됨)
public class Onetoone {
	
	@Id // PK를 해당 변수로 하겠다.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 데이터베이스 번호 전략을 따름
	private Long qnaNum;
	
	private String qnaTitle;
	private String qnaAuthor;
	private String qnaContent;
	private Long qnaDate;
	private String qnaPwd;
	private String qnaState;
	

}
