package com.rmartinper.filepicker.utils;

import com.rmartinper.filepicker.model.DialogConfigs;
import com.rmartinper.filepicker.model.DialogProperties;

import java.io.File;
import java.io.FileFilter;
import java.util.Locale;

/**
 * <p>
 * Created by Raul Martin on 22-07-2020.
 * </p>
 */

/*  Class to filter the list of files.
 */
public class ExtensionFilter implements FileFilter {
    private final String[] validExtensions;
    private DialogProperties properties;

    public ExtensionFilter(DialogProperties properties) {
        String[] extensions = properties.getExtensions();
        if(extensions != null) {
            this.validExtensions = extensions;
        } else {
            this.validExtensions = new String[]{""};
        }
        this.properties = properties;
    }

    /**Function to filter files based on defined rules.
     */
    @Override
    public boolean accept(File file) {
        //All directories are added in the least that can be read by the Application
        if(file.isDirectory() && file.canRead()) {
            return true;
        } else if(properties.getSelectionType() == DialogConfigs.DIR_SELECT) {
            /*  True for files, If the selection type is Directory type, ie.
             *  Only directory has to be selected from the list, then all files are
             *  ignored.
             */
            return false;
        } else {
            /*  Check whether name of the file ends with the extension. Added if it
             *  does.
             */
            String name = file.getName().toLowerCase(Locale.getDefault());
            for(String ext: validExtensions) {
                if(name.endsWith(ext)) {
                    return true;
                }
            }
        }
        return false;
    }
}
