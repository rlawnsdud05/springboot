package com.bbangduck.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bbangduck.domain.OnetooneRepository;

// 단위 테스트 (service에 관련된 애들마 IoC에 띄우면됨(메모리)
// 서비스와 관련된 객체가 레포지터리 밖에 없는데
// 그걸 가짜 객체로 만들 수 있는데 이걸 지원해주는 게 Mockito 환경

@ExtendWith(MockitoExtension.class)
public class OnetooneServiceUnitTest {

	// @Mock
	// 메모리에 뜸(스프링 IoC가 아닌 모키토라는 메모리 공간에 뜸)
	// 모키토 환경은 레포지터리를 안들고 있기 때문에 직접 주입해줘야 함
	
	// @InjectMocks : 서비스 객체가 만들어질 때, @Mock로 등록된 모든 애들을 주입받음
	@InjectMocks
	private OnetooneService oneService;
	
	@Mock
	private OnetooneRepository oneRepo;
	
	
}
