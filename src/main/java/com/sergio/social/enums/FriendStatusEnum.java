package com.sergio.social.enums;

/**
 * Custon emum type for the status of friend request
 *
 */
public enum FriendStatusEnum {
	INITIAL(0), ACCEPTED(1), REFUSED(2);

	private final int constValue;

	private FriendStatusEnum(int constValue) {
		this.constValue = constValue;
	}

	public int constValue() {
		return constValue;
	}

	public int getConstValue() {
		return constValue;
	}

	public String getName() {
		return name();
	}
}
