/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author yagami
 */
public class ChooseImagesJPG extends FileFilter{

    @Override
    public boolean accept(File f) {
        if(f.isDirectory())
            return true;
        if(f.getName().endsWith(".jpg") || f.getName().endsWith(".png") || f.getName().endsWith(".gif")) 
            return true;
        return false;
    }

    @Override
    public String getDescription() {
        return ".jpg,.png,.gif";
    }
    
}
