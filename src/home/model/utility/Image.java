package home.model.utility;

import java.io.File;
import java.net.URI;

/*TODO cerca di rendere più significativa questa interfaccia*/
/**
 * a simple interface to model an image.
 */
public interface Image {
    /**
     * create a simple image.
     * @param fileName
     *  the path of the image
     * @return
     *  the image created
     */
    static Image createImage(final File fileName){
        return new Image() {
            private File file = fileName;
            @Override
            public File getFileName() {
                return this.file;
            }
        };
    }
    /**
     * @return
     *  the path of the image
     */
    File getFileName();
}
