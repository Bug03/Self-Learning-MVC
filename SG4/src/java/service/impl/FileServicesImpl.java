package service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.fileupload.FileItem;
import service.FileService;

/**
 *
 * @author Admin
 */
public class FileServicesImpl implements FileService {

    @Override
    public File uploadFile(FileItem item, String path) {
        String fileName = item.getName();
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File fileSaved = new File(folder.getAbsolutePath() + File.separator + fileName);

        BufferedOutputStream bs = null;
        BufferedInputStream bi = null;
        try {
            bs = new BufferedOutputStream(new FileOutputStream(fileSaved));
            bi = new BufferedInputStream(item.getInputStream());

            byte[] buffer = new byte[1024];
            int length;

            while ((length = bi.read(buffer)) > 0) {
                bs.write(buffer, 0, length);
            }
            bs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bi.close();
            } catch (IOException ex) {
                Logger.getLogger(FileServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                bs.close();
            } catch (IOException ex) {
                Logger.getLogger(FileServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fileSaved;
    }

    @Override
    public void copyFile(File source, File target) {
        InputStream is = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(target);

            byte[] buffer= new byte[1024];
            int length;

            while ((length = is.read(buffer)) > 0){
                os.write(buffer,0,length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
