package com.rbinternational.test.demometrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class ActuatorConfig {

	@Autowired
	HealthEndpoint healthEndpoint;
	
	@Bean
	HelathDataSource dataSourceStatusProbe() {
		return new HelathDataSource(healthEndpoint);
	}
}
