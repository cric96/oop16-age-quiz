package home.model.building;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

final class BuildingImageImpl implements BuildingImage {
    private final String path;
    private int age;
    BuildingImageImpl(final File path) {
        this.path = path.getPath();
        this.age = 0;
    }
    @Override
    public Class<?> getType() {
        return BuildingImage.class;
    }

    @Override
    public void nextAge() {
        this.age++;
        if (Files.exists(Paths.get(this.path + age))) {
            throw new IllegalStateException("No image find in this age");
        }
    }

    @Override
    public File getImagePath() {
        /*operazione ternaria per rendere più conciso*/
        return new File(this.path + (this.age == 0 ? "" : this.age));
    }
    /* TODO DA FARE!*/
    @Override
    public Class<?> getParentType() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Optional<?> getParent() {
        // TODO Auto-generated method stub
        return null;
    }

}
