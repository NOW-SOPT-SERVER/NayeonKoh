package org.sopt.practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing /* JPA가 entity 감시 -> 변경 시점을 인식하여 수정 날짜 등의 (메타)정보 저장 가능 */
public class JpaAuditingConfig {


}
