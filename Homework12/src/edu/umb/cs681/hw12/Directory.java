package edu.umb.cs681.hw12;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {
    private LinkedList<FSElement> children;

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        this.children = new LinkedList<FSElement>();
    }

    @Override
    boolean isDirectory() {
        lock.lock();
        try {
            return true;
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<FSElement> getChildren() {
        lock.lock();
        try {
            return children;
        } finally {
            lock.unlock();
        }
    }

    public void appendChild(FSElement child) {
        lock.lock();
        try {
            this.children.add(child);
        } finally {
            lock.unlock();
        }
    }

    public void removeChild(FSElement child) {
        lock.lock();
        try {
            this.children.remove(child);
        } finally {
            lock.unlock();
        }
    }

    public int countChildren() {
        lock.lock();
        try {
            return children.size();
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getSubDirectories() {
        lock.lock();
        try {
            LinkedList<Directory> subDirectories = new LinkedList<Directory>();
            for (FSElement child : this.children) {
                if (child.isDirectory()) {
                    subDirectories.add((Directory) child);
                }
            }
            return subDirectories;
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<File> getFiles() {
        lock.lock();
        try {
            LinkedList<File> files = new LinkedList<File>();
            for (FSElement child : this.children) {
                if (child instanceof File) {
                    files.add((File) child);
                }
            }
            return files;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getSize() {
        lock.lock();
        try {
            int total_size = 0;
            for (FSElement child : children) {
                total_size += child.getSize();
            }
            this.size = total_size;
            return this.size;
        } finally {
            lock.unlock();
        }
    }

}