package com.rmartinper.filepicker.model;

import java.util.HashMap;
import java.util.Set;

/**
 * <p>
 * Created by Raul Martin on 02-07-2020.
 * </p>
 */

/*  SingleTon containing <Key,Value> pair of all the selected files.
 *  Key: Directory/File path.
 *  Value: FileListItem Object.
 */
public class MarkedItemList {
    private static HashMap<String,FileListItem> ourInstance = new HashMap<>();

    /*
     * Constructs a new MarkedItemList.
     */
    private MarkedItemList() {
        super();
    }

    public static void addSelectedItem(FileListItem item) {
        ourInstance.put(item.getLocation(),item);
    }

    public static void removeSelectedItem(String key) {
        ourInstance.remove(key);
    }

    public static boolean hasItem(String key) {
        return ourInstance.containsKey(key);
    }

    public static void clearSelectionList() {
        ourInstance = new HashMap<>();
    }

    public static void addSingleFile(FileListItem item) {
        ourInstance = new HashMap<>();
        ourInstance.put(item.getLocation(),item);
    }

    public static String[] getSelectedPaths() {
        Set<String> paths = ourInstance.keySet();
        String[] fpaths = new String[paths.size()];
        int i = 0;
        for(String path: paths) {
            fpaths[i++] = path;
        }
        return fpaths;
    }

    public static int getFileCount() {
        return ourInstance.size();
    }
}
