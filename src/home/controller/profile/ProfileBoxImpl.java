package home.controller.profile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import home.utility.LocalFolder;

final class ProfileBoxImpl implements ProfileBox {
    private static final ProfileBox SINGLETON = new ProfileBoxImpl();
    private static final int NUM_PROFILES = 3;
    private Set<Profile> profiles;
    private Optional<File> saveFile;
    private Profile selected;
    private ProfileBoxImpl() {
        this.profiles = IntStream.range(0, NUM_PROFILES)
                                 .mapToObj(x -> Profile.createProfile(new File(LocalFolder.CONFIG_FOLDER.getInfo() + LocalFolder.SEPARATOR.getInfo() + x)))
                                 .collect(Collectors.toSet());
    }
    public static ProfileBox get() {
        return ProfileBoxImpl.SINGLETON;
    }
    @Override
    public void save() throws IOException {
        final ObjectOutput out = new ObjectOutputStream(new FileOutputStream(this.saveFile.orElseThrow(() -> new IllegalStateException())));
        out.writeObject(profiles);
        out.close();
    }
    @Override
    @SuppressWarnings("unchecked")
    public void load() throws IOException, ClassNotFoundException {
        final ObjectInput in = new ObjectInputStream(new FileInputStream(this.saveFile.orElseThrow(() -> new IllegalStateException())));
        this.profiles = (Set<Profile>) in.readObject();
        in.close();
    }
    @Override
    public Set<Profile> getProfile() {
        return Collections.unmodifiableSet(profiles);
    }
    @Override
    public void setFile(final File file) {
        Objects.requireNonNull(file);
        this.saveFile = Optional.of(file);
    }
    @Override
    public void select(final Profile profile) {
        Objects.requireNonNull(profile);
        if (!this.profiles.contains(profile)) {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public Optional<Profile> getSelected() {
        return Optional.ofNullable(this.selected);
    }
}
