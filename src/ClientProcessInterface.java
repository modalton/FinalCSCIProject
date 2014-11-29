
public interface ClientProcessInterface<T> {
	void processInputObject(T object);
	boolean hasOutputObject();
	T processOutputObject();
	
}


