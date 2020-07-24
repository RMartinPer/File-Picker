package com.rmartinper.filepicker.model;

/* <p>
 * Created by Raul Martin on 02-07-2020.
 * </p>
 */

import java.util.Locale;

/**
 * The model/container class holding file list data.
 */
public class FileListItem implements Comparable<FileListItem> {
    private String filename;
    private String location;
    private boolean directory;
    private boolean marked;
    private long time;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    @Override
    public int compareTo(FileListItem fileListItem) {
        if(fileListItem.isDirectory() && isDirectory()) {
            //If the comparison is between two directories, return the directory with
            //alphabetic order first.
            return filename.toLowerCase().compareTo(fileListItem.getFilename().toLowerCase(Locale.getDefault()));
        } else if(!fileListItem.isDirectory() && !isDirectory()) {
            //If the comparison is not between two directories, return the file with
            //alphabetic order first.
            return filename.toLowerCase().compareTo(fileListItem.getFilename().toLowerCase(Locale.getDefault()));
        } else if(fileListItem.isDirectory() && !isDirectory()) {
            //If the comparison is between a directory and a file, return the directory.
            return 1;
        } else {
            //Same as above but order of occurence is different.
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        FileListItem fli = (FileListItem) o;
        return  filename.equals(fli.filename) &&
                location.equals(fli.location) &&
                directory == fli.directory &&
                marked == fli.marked &&
                time == fli.time;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.filename != null ? this.filename.hashCode() : 0);
        hash = 17 * hash + (this.location != null ? this.location.hashCode() : 0);
        hash = 17 * hash + (this.directory ? 1 : 0);
        hash = 17 * hash + (this.marked ? 1 : 0);
        hash = 17 * hash + (int) (this.time ^ (this.time >>> 32));
        return hash;
    }
}