package home.model.serializator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * an interface used to define a object that can serialize other object.
 * @param <E>
 *  the object to serialize
 */
public interface Serializator<E> {
    /**
     * save the object passed.
     * @param object
     *  the object that you want to save
     * @throws IOException
     *  if there is problem during the serialization
     */
    void save(E object) throws IOException;
    /**
     * load an abject.
     * @return
     *  the object loaded
     * @throws FileNotFoundException
     *  if the file is not present
     * @throws IOException
     *  if there is some problem with the serialization
     * @throws ClassNotFoundException
     *  if the file don't contain the object required
     */
    E load() throws FileNotFoundException, IOException, ClassNotFoundException;
    /**
     * create a simple serializator with object that implements serializable.
     * @param file
     *  the file used to save and load
     * @return
     *  the serializator created
     * @param <E>
     *  the type of object
     */
    static <E> Serializator<E> createSimple(final File file) {
        return new Serializator<E>() {
            @Override
            public void save(final E object) throws IOException {
                final ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(object);
                out.close(); 
            }

            @Override
            public E load() throws FileNotFoundException, IOException, ClassNotFoundException {
                final ObjectInput in = new ObjectInputStream(new FileInputStream(file));
                @SuppressWarnings("unchecked")
                E object = (E) in.readObject();
                in.close();
                return object;
            }
        };
    }
}
