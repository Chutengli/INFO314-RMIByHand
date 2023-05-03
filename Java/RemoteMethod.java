import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteMethod implements Serializable {
	private String name;
	private ParamType[] params;

	public RemoteMethod (String name, ParamType[] params) {
		this.name = name;
		this.params = params;
	}

	public String getName() {
		return name;
	}

	public ParamType[] getParams() {
		return params;
	}

}
