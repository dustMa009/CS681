package edu.umb.cs681.hw12;

import java.util.LinkedList;

public class FileSystem {
    private static FileSystem fs = null;
    private LinkedList<Directory> rootDirs;
    private FileSystem() { rootDirs = new LinkedList<Directory>(); };
    public static FileSystem getInstance() {
        if (fs == null) {
            fs = new FileSystem();
        }
        return fs;
    }

    public LinkedList<Directory> getRootDirs() { return rootDirs; }

    public void appendRootDir(Directory root) {
        rootDirs.add(root);
    }

    public void removeRootDir(Directory root) { rootDirs.remove(root); }

    public void clearRootDirs() { rootDirs.clear(); }
}
