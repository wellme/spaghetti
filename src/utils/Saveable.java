package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public interface Saveable<T extends Saveable<T>> extends Serializable {

	public default void saveToFile(String path) throws FileNotFoundException, IOException {
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path));
		stream.writeObject(this);
		stream.flush();
		stream.close();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T loadFromFile(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream stream = new ObjectInputStream(new FileInputStream(path));
		Object o = stream.readObject();
		stream.close();
		return (T) o;
	}
}
