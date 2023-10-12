package org.trananh.shoppingappbackend.ultilities.idGenerator;

import java.util.stream.Stream;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;


public class PromotionDetailIdGenerator implements IdentifierGenerator{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4931467457985073156L;
	private String prefix = "PRO_DETAIL";

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		String query = "SELECT id FROM PromotionDetail";
		Stream<String> ids = session.createQuery(query, String.class).stream();
		Long max = ids.map(o -> o.replace(prefix, "")).mapToLong(Long::parseLong).max().orElse(0L);
		return prefix + (String.format("%04d", max + 1));
	}
}