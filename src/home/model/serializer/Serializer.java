package home.model.serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;

/**
 * an interface used to define a object that can serialize other object.
 * @param <E>
 *  the object to serialize
 */
public interface Serializer<E> {
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
     * create a simple serializer with object that implements serializable.
     * @param file
     *  the file used to save and load
     * @return
     *  the serializer created
     * @param <E>
     *  the type of object
     */
    static <E> Serializer<E> createSimple(final File file) {
        return new Serializer<E>() {
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
    /**
     * create a serializer able to serialize in JSON.
     * @param file
     *  the file to put the object and restore it
     * @param type
     *  the type of object to save
     * @param adapter
     *  the adapter for store the object
     * @param <E>
     *  the object to serialize
     * @return
     *  the serializer
     * 
     */
    static <E> Serializer <E> createJsonSerializer(final File file, final Type type, final TypeAdapter<E> adapter) {
        return new Serializer<E>() {
            @Override
            public void save(final E object) throws IOException {
                final Gson gson = new GsonBuilder().registerTypeAdapter(type, adapter).create();
                final PrintStream ps = new PrintStream(new FileOutputStream(file));
                ps.print(gson.toJson(object, type));
                ps.close();
            }
            @Override
            public E load() throws FileNotFoundException, IOException, ClassNotFoundException {
                final Gson gson = new GsonBuilder().registerTypeAdapter(type, adapter).create();
                return gson.fromJson(Files.readAllLines(Paths.get(file.toURI())).get(0), type);
            }
        };
    }
}
