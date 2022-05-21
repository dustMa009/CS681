package edu.umb.cs681.hw15;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Directory extends FSElement {
    private ConcurrentLinkedQueue<FSElement> children;

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        this.children =  new ConcurrentLinkedQueue<>();
    }

    @Override
    boolean isDirectory() {
        return true;
    }

    public ConcurrentLinkedQueue<FSElement> getChildren() {
        return children;
    }

    public void appendChild(FSElement child) {
        this.children.add(child);
    }

    public void removeChild(FSElement child) {
        this.children.remove(child);
    }

    public int countChildren() {
        return children.size();
    }

    public ConcurrentLinkedQueue<FSElement> getSubDirectories() {
        ConcurrentLinkedQueue<FSElement> subDirectories = new ConcurrentLinkedQueue<>();
        for (FSElement child : this.children) {
            if (child.isDirectory()) {
                subDirectories.add((Directory) child);
            }
        }
        return subDirectories;
    }

    public ConcurrentLinkedQueue<FSElement> getFiles() {
        ConcurrentLinkedQueue<FSElement> files = new ConcurrentLinkedQueue<>();
        for (FSElement child : this.children) {
            if (child instanceof File) {
                files.add((File) child);
            }
        }
        return files;
    }

    @Override
    public int getSize() {
        int total_size = 0;
        for (FSElement child : children) {
            total_size += child.getSize();
        }
        this.size = total_size;
        return this.size;
    }

}
