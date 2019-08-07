package Koji;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

abstract class MapOperators {
    static boolean checkMove(Field x, Player p) {
        if (Math.abs(x.getXx() - p.getXx()) > 2) {
            return false;
        } else if (Math.abs(x.getYy() - p.getYy()) > 2) return false;
        if (Math.abs(x.getXx() - p.getXx()) <= 1) {
            if (Math.abs(x.getYy() - p.getYy()) <= 1) return true;
        }
        return (Math.abs(x.getXx() - p.getXx()) == 2) && (Math.abs(x.getYy() - p.getYy()) == 0) || (Math.abs(x.getXx() - p.getXx()) == 0) && (Math.abs(x.getYy() - p.getYy()) == 2);
    }

    static File getResourceAsFile(String resourcePath) {
        try {
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
            if (in == null) {
                return null;
            }

            File tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
            tempFile.deleteOnExit();

            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                //copy stream
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
