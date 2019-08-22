package com.rbinternational.test.demometrics;

import org.springframework.boot.actuate.health.HealthEndpoint;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.MeterBinder;

public class HelathDataSource implements MeterBinder {

	private HealthEndpoint healthEndpoint;
	private final String name;
	private final String description;
	private final Iterable<Tag> tags;
	private static final double UP = 1.0;
	private static final double DOWN = 0.0;

	public HelathDataSource(HealthEndpoint healthEndpoint) {
		this.healthEndpoint = healthEndpoint;
		this.name = "health";
		this.description = "DataSource status";
		this.tags = tags();

	}

	private String status() {
		return healthEndpoint.health().getStatus().getCode();
	}

	@Override
	public void bindTo(final MeterRegistry meterRegistry) {
		Gauge.builder(name, this, value -> value.status() == "UP" ? UP : DOWN).description(description).tags(tags)
				.baseUnit("status").register(meterRegistry);
	}

	protected static Iterable<Tag> tags() {
		return Tags.of(Tag.of("health", "global"));

	}
}