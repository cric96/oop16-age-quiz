package home.controller.dialog;

import java.util.Objects;

import home.utility.Utility;

//package-protected
final class DialogImpl implements Dialog {
    private final String name;
    private final int level;
    private final int experience;
    private final boolean levelUp;
    private final boolean levelBlocked;
    DialogImpl(final String name, final int level,
            final int experience, final boolean levelUp, final boolean levelBlocked) {
        super();
        Objects.requireNonNull(name);
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.levelUp = levelUp;
        this.levelBlocked = levelBlocked;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public int getLevel() {
        return this.level;
    }
    @Override
    public int getExperience() {
        return this.experience;
    }
    @Override
    public boolean levelUpEnabled() {
        return this.levelUp;
    }
    @Override
    public boolean isLevelBlocked() {
        return this.levelBlocked;
    }
    @Override
    public String toString() {
        return "DialogImpl [name=" + name + ", level=" + level + ", experience=" + experience + ", levelUp=" + levelUp
                + ", levelBlocked=" + levelBlocked + "]";
    }

    //package-protected
    static class DialogBuilderImpl implements Dialog.Builder {
        private String name;
        private int level;
        private int experience;
        private boolean enable;
        private boolean blocked;
        private boolean builded;
        //package-protected
        DialogBuilderImpl() {
            this.builded = false;
        }
        @Override
        public Builder setName(final String name) {
            this.checkBuild();
            this.name = name;
            return this;
        }

        @Override
        public Builder setLevel(final int level) {
            this.checkBuild();
            this.level = level;
            return this;
        }

        @Override
        public Builder setExperience(final int experience) {
            this.checkBuild();
            this.experience = experience;
            return this;
        }

        @Override
        public Builder setLevelEnable(final boolean enable) {
            this.checkBuild();
            this.enable = enable;
            return this;
        }

        @Override
        public Builder setLevelBlocked(final boolean blocked) {
            this.checkBuild();
            this.blocked = blocked;
            return this;
        }
        public Dialog build() {
            this.checkBuild();
            if (Utility.checkNullOb(this.name)) {
                throw new IllegalStateException();
            }
            this.builded = true;
            return new DialogImpl(this.name, this.level, this.experience, this.enable, this.blocked);
        }
        private void checkBuild() {
            if (this.builded) {
                throw new IllegalStateException();
            }
        }
    }
}
