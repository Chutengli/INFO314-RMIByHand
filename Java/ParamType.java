import java.io.Serializable;

public class ParamType implements Serializable {
	private Object value;
	private String type;

	public ParamType(Object value, String type) {
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}
}
