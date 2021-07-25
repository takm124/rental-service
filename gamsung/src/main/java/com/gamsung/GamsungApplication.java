package com.gamsung;

import com.gamsung.domain.Staff;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;


@EnableJpaAuditing
@SpringBootApplication
public class GamsungApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamsungApplication.class, args);
	}
/*
	@Bean
	public AuditorAware<String> auditorProvider(){
		return () -> {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			Staff currentUser = (Staff) attr.getRequest().getSession().getAttribute(SessionConst.LOGIN_STAFF);

			if(currentUser != null){
				return Optional.of(currentUser.getStaffName());
			}

			return Optional.of("Anonymous"); // 로그인 정보 없는경우 오류 처리 방안 구상하는 중
		};
	}*/
}
