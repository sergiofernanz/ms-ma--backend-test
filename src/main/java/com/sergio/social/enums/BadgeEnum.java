package com.sergio.social.enums;

/**
 * Custon emum type for the badges.
 *
 */
public enum BadgeEnum {
	FOREVER_ALONE(0);

	private final int constValue;

	private BadgeEnum(int constValue) {
		this.constValue = constValue;
	}

	public int constValue() {
		return constValue;
	}

	public String getName() {
		return name();
	}
}
