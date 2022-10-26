package ChessProject;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileInterface {
    public void saveFile(String fileName, Game game) throws IOException;
    public void loadFile(String fileName, Game game) throws FileNotFoundException;
}
