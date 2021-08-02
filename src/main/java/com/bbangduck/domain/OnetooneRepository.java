package com.bbangduck.domain;

import org.springframework.data.jpa.repository.JpaRepository;

// @Repository를 적어야 원래 스프링 IoC에 빈으로 등록이 되는데
// JpaRepository를 extends를 하면 생략 가능함
// JpaRepository가 CRUD 함수를 들고 있음 
public interface OnetooneRepository extends JpaRepository<Onetoone, Long>{

}
