package home.controller.profile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import home.model.serializer.Serializer;
import home.utility.LocalFolder;

final class ProfileBoxImpl implements ProfileBox {
    private static final ProfileBox SINGLETON = new ProfileBoxImpl();
    private static final int NUM_PROFILES = 3;
    private static final Type SAVEDTYPE = new TypeToken<Set<Profile>>() { }.getType();
    private static final AdapterProfile ADAPTER = new AdapterProfile();
    private List<Profile> profiles;
    private Profile selected;
    private Optional<Serializer<List<Profile>>> serializer;
    private ProfileBoxImpl() {
        this.profiles = IntStream.range(0, NUM_PROFILES)
                                 .mapToObj(x -> new ProfileImpl(new File(LocalFolder.CONFIG_FOLDER 
                                                                             + LocalFolder.SEPARATOR.toString()
                                                                             + "profile" + x + ".obj")))
                                 .collect(Collectors.toList());
    }
    public static ProfileBox get() {
        return ProfileBoxImpl.SINGLETON;
    }
    @Override
    public void save() throws IOException {
        this.getSerializator().save(this.profiles);
    }
    @Override
    public void load() throws IOException, ClassNotFoundException {
        this.profiles = this.getSerializator().load();
    }
    @Override
    public List<Profile> getProfile() {
        return Collections.unmodifiableList(profiles);
    }
    @Override
    public void setFile(final File file) {
        Objects.requireNonNull(file);
        this.serializer = Optional.of(Serializer.createJsonSerializer(file, SAVEDTYPE, ADAPTER));
    }
    @Override
    public void select(final Profile profile) {
        Objects.requireNonNull(profile);
        if (!this.profiles.contains(profile)) {
            throw new IllegalArgumentException();
        }
        this.selected = profile;
    }
    @Override
    public Optional<Profile> getSelected() {
        return Optional.ofNullable(this.selected);
    }
    private Serializer<List<Profile>> getSerializator() {
        return this.serializer.orElseThrow(() -> new IllegalStateException("NO FILE SELECTED"));
    }

    private static class AdapterProfile extends  TypeAdapter<List<Profile>> {

        @Override
        public List<Profile> read(final JsonReader reader) throws IOException {
            final List<Profile> profiles = new ArrayList<>();
            reader.beginArray();
            while (reader.hasNext()) {
                reader.beginObject();
                reader.nextName();
                final Profile current = new ProfileImpl(new File(reader.nextString()));
                reader.nextName();
                final String name = reader.nextString();
                if (!name.isEmpty()) {
                    current.setName(name);
                    current.setEnabled(true);
                }
                profiles.add(current);
                reader.endObject();
            }
            reader.endArray();
            return profiles;
        }

        @Override
        public void write(final JsonWriter writer, final List<Profile> profiles) throws IOException {
            writer.beginArray();
            for (final Profile profile : profiles) {
                writer.beginObject();
                writer.name("file").value(profile.getSaveGame().getAbsolutePath());
                writer.name("name").value(profile.getName().orElse(""));
                writer.endObject();
            }
            writer.endArray();
        }
    }
}
