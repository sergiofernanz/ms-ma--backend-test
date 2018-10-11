package com.sergio.social.enums;

/**
 * Custon emum type for the visibility of a user.
 *
 */
public enum VisibilityEnum {
	HIDDEN(0), PUBLIC(1);

	private final int constValue;

	private VisibilityEnum(int constValue) {
		this.constValue = constValue;
	}

	public int constValue() {
		return constValue;
	}

	public String getName() {
		return name();
	}
}
