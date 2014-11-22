
public interface ClientProcessInterface<T> {
	void processInputObject(T t);
	boolean hasOutputObject();
	T processOutputObject();
	
}

