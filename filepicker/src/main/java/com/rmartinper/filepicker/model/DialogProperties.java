package com.rmartinper.filepicker.model;

import java.io.File;

/**
 * <p>
 * Created by Raul Martin on 02-07-2020.
 * </p>
 */

/*  Descriptor class to define properties of the Dialog. Actions are performed upon
 *  these Properties
 */
public class DialogProperties {
    /** Selection Mode defines whether a single of multiple Files/Directories
     *  have to be selected.
     *
     *  SINGLE_MODE and MULTI_MODE are the two selection modes, See DialogConfigs
     *  for more info. Set to SINGLE_MODE as default value by constructor.
     */
    private int selectionMode;

    /** Selection Type defines that whether a File/Directory or both of these has
     *  to be selected.
     *
     *  FILE_SELECT ,DIR_SELECT, FILE_AND_DIR_SELECT are the three selection modes,
     *  See DialogConfigs for more info. Set to FILE_SELECT as default value by constructor.
     */
    private int selectionType;

    /**  The Parent/Root Directory. List of Files are populated from here. Can be set
     *  to any readable directory. /sdcard is the default location.
     *
     *  Eg. /sdcard
     *  Eg. /mnt
     */
    private File root;

    /**  The Directory is used when Root Directory is not readable or accessible.
     *  /sdcard is the default location.
     *
     *  Eg. /sdcard
     *  Eg. /mnt
     */
    private File errorDir;

    /** The Directory can be used as an offset. It is the first directory that is
     *  shown in dialog. Consider making it Root's sub-directory.
     *
     *  Eg. Root: /sdcard
     *  Eg. Offset: /sdcard/Music/Country
     */
    private File offset;

    /** An Array of String containing extensions, Files with only that will be shown.
     *  Others will be ignored. Set to null as default value by constructor.
     *  Eg. String ext={"jpg","jpeg","png","gif"};
     */
    private String[] extensions;

    /** The indicator of if the hidden files are shown. If it is false, they are shown.
     *  Otherwise, they are not shown. Set to false as default value by constructor.
     */
    private boolean showHiddenFiles;

    /**
     * Gets the selection mode of these Properties and returns it.
     *
     * @return the selection mode of these Properties.
     */
    public int getSelectionMode() {
        return selectionMode;
    }

    /**
     * Sets the new selection mode of these Properties.
     *
     * @param selectionMode the new selection mode of these Properties.
     */
    public void setSelectionMode(int selectionMode) {
        this.selectionMode = selectionMode;
    }

    /**
     * Gets the selection type of these Properties and returns it.
     *
     * @return the selection mode of these Properties.
     */
    public int getSelectionType() {
        return selectionType;
    }

    /**
     * Sets the new selection type of these Properties.
     *
     * @param selectionType the new selection type of these Properties.
     */
    public void setSelectionType(int selectionType) {
        this.selectionType = selectionType;
    }

    /**
     * Gets the root of these Properties and returns it.
     *
     * @return the root of these Properties.
     */
    public File getRoot() {
        return root;
    }

    /**
     * Sets the new root of these Properties.
     *
     * @param root the new root of these Properties.
     */
    public void setRoot(File root) {
        this.root = root;
    }

    /**
     * Gets the error directory of these Properties and returns it.
     *
     * @return the error directory of these Properties.
     */
    public File getErrorDir() {
        return errorDir;
    }

    /**
     * Sets the new error directory of these Properties.
     *
     * @param errorDir the new error directory of these Properties.
     */
    public void setErrorDir(File errorDir) {
        this.errorDir = errorDir;
    }

    /**
     * Gets the offset of these Properties and returns it.
     *
     * @return the offset of these Properties.
     */
    public File getOffset() {
        return offset;
    }

    /**
     * Sets the new offset of these Properties.
     *
     * @param offset the new offset of these Properties.
     */
    public void setOffset(File offset) {
        this.offset = offset;
    }

    /**
     * Gets the extensions of these Properties and returns it.
     *
     * @return the extensions of these Properties.
     */
    public String[] getExtensions() {
        return extensions;
    }

    /**
     * Sets the new extensions of these Properties.
     *
     * @param extensions the new extensions of these Properties.
     */
    public void setExtensions(String[] extensions) {
        this.extensions = extensions;
    }

    /**
     * Gets the indicator of if the hidden files are shown of these Properties and returns it.
     *
     * @return the indicator of if the hidden files are shown of these Properties.
     */
    public boolean areHiddenFilesShown() {
        return showHiddenFiles;
    }

    /**
     * Sets the new indicator of if the hidden files are shown of these Properties.
     *
     * @param showHiddenFiles the new indicator of if the hidden files are shown of these Properties.
     */
    public void setHiddenFilesShown(boolean showHiddenFiles) {
        this.showHiddenFiles = showHiddenFiles;
    }

    public DialogProperties() {
        this(false);
    }

    public DialogProperties(boolean isExternal) {
        selectionMode = DialogConfigs.SINGLE_MODE;
        selectionType = DialogConfigs.FILE_SELECT;

        if (isExternal) {
            root = new File(DialogConfigs.EXTERNAL_DIR);
            errorDir = new File(DialogConfigs.EXTERNAL_DIR);
            offset = new File(DialogConfigs.EXTERNAL_DIR);
        } else {
            root = new File(DialogConfigs.DEFAULT_DIR);
            errorDir = new File(DialogConfigs.DEFAULT_DIR);
            offset = new File(DialogConfigs.DEFAULT_DIR);
        }

        extensions = null;
        showHiddenFiles = false;
    }
}