package com.bbangduck.onetoone;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnetooneRepository extends JpaRepository<Onetoone, Integer> {
	
		Page<Onetoone> findByQnaTitle(Pageable page, String qnaTitle);
		Page<Onetoone> findByQnaTitleContaining(Pageable page, String qnaTitle);
		
	

}
