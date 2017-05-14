package home.controller.profile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import home.model.serializator.Serializator;
import home.utility.LocalFolder;

final class ProfileBoxImpl implements ProfileBox {
    private static final ProfileBox SINGLETON = new ProfileBoxImpl();
    private static final int NUM_PROFILES = 3;
    private static final Type SAVEDTYPE = new TypeToken<Set<Profile>>() { }.getType();
    private static final AdapterProfile ADAPTER = new AdapterProfile();
    private Set<Profile> profiles;
    private Profile selected;
    private Optional<Serializator<Set<Profile>>> seriliazator;
    private ProfileBoxImpl() {
        this.profiles = IntStream.range(0, NUM_PROFILES)
                                 .mapToObj(x -> Profile.createProfile(new File(LocalFolder.CONFIG_FOLDER.getInfo() 
                                                                             + LocalFolder.SEPARATOR.getInfo()
                                                                             + "profile" + x + ".obj")))
                                 .collect(Collectors.toSet());
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
    public Set<Profile> getProfile() {
        return Collections.unmodifiableSet(profiles);
    }
    @Override
    public void setFile(final File file) {
        Objects.requireNonNull(file);
        this.seriliazator = Optional.of(Serializator.createJsonSerializator(file, SAVEDTYPE, ADAPTER));
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
    private Serializator<Set<Profile>> getSerializator() {
        return this.seriliazator.orElseThrow(() -> new IllegalStateException("NO FILE SELECTED"));
    }

    private static class AdapterProfile extends  TypeAdapter<Set<Profile>> {

        @Override
        public Set<Profile> read(final JsonReader reader) throws IOException {
            final Set<Profile> profiles = new HashSet<>();
            reader.beginArray();
            while (reader.hasNext()) {
                reader.beginObject();
                reader.nextName();
                final Profile current = Profile.createProfile(new File(reader.nextString()));
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
        public void write(final JsonWriter writer, final Set<Profile> profiles) throws IOException {
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
