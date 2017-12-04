package core.model;

public enum SlotType {

	SLOT_K("K"),
	SLOT_O("O");
	
	private final String code;

	private SlotType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
