package com.rmartinper.filepicker.model;

/**
 * <p>
 * Created by Raul Martin on 02-07-2020.
 * </p>
 */

/*  Helper class for setting properties of Dialog.
 */
public abstract class DialogConfigs {
    /*  SELECTION_MODES  */

    /*  SINGLE_MODE specifies that a single File/Directory has to be selected
     *  from the list of Files/Directories. It is the default Selection Mode.
     */
    public static final int SINGLE_MODE = 0;

    /*  MULTI_MODE specifies that multiple Files/Directories has to be selected
     *  from the list of Files/Directories.
     */
    public static final int MULTI_MODE = 1;

    /*  SELECTION_TYPES  */

    /*  FILE_SELECT specifies that from list of Files/Directories a File has to
     *  be selected. It is the default Selection Type.
     */
    public static final int FILE_SELECT = 0;

    /*  DIR_SELECT specifies that from list of Files/Directories a Directory has to
     *  be selected.
     */
    public static final int DIR_SELECT = 1;

    /*  FILE_AND_DIR_SELECT specifies that from list of Files/Directories both
     *  can be selected.
     */
    public static final int FILE_AND_DIR_SELECT = 2;

    /*  PARENT_DIRECTORY  */
    public static final String DIRECTORY_SEPERATOR = "/";
    public static final String MOUNT_POINT = "mnt";
    public static final String SDCARD_DIR = "sdcard";

    /*  DEFAULT_DIR is the default mount point of the device. It is the default
     *  mount point.
     */
    public static final String DEFAULT_DIR = DIRECTORY_SEPERATOR + MOUNT_POINT;

    /*  EXTERNAL_DIR is the external storage directory of the device.
     */
    public static final String EXTERNAL_DIR = DIRECTORY_SEPERATOR + SDCARD_DIR;

    /*  Constructs a new DialogConfigs.
     */
    private DialogConfigs() {
        super();
    }
}
