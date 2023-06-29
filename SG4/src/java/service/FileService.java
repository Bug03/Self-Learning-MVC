/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.File;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author Admin
 */
public interface FileService {
    
    File uploadFile(FileItem item, String path);
    
    void copyFile(File source, File target);
}
