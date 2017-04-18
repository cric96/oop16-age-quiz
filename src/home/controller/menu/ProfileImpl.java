package home.controller.menu;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

final class ProfileImpl implements Profile {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private boolean enabled;
    private final File file;

    ProfileImpl(final File fileName) {
        this.enabled = false;
        this.file = fileName;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(final String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public File getSaveGame() {
        return this.file;
    }

    @Override
    public String toString() {
        return "ProfileImpl [name=" + name + ", enabled=" + enabled + ", file=" + file + "]";
    }
    
}