package com.bbangduck.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

// 단위 테스트(DB 관련된 Bean이 IoC에 등록되면 됨)
// Replace.ANY -> 내장된 가짜 DB로 테스트(실제 DB가 아님)
// Replace.NONE -> 실제 DB로 테스트
// DataJpaTest : 레포지터리들을 다 IoC에 등록해둠 -> @Mock를 쓰지 않아도 됨 대신 @Autowired 씀

@Transactional
@AutoConfigureTestDatabase(replace = Replace.ANY)
@DataJpaTest
public class OnetooneRepositoryUnitTest {

	@Autowired
	private OnetooneRepository oneRepo;
}
