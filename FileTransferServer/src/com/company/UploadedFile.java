package com.company;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class UploadedFile extends File{
    private File file;

    public int getVersion() {
        return version;
    }

    private int version;
    private byte[] fileNameBytes; //name in server
    boolean lock;
    AtomicInteger concurrentDownloaded;

    public void lock(){lock = true;}
    public void unlock(){lock = false;}

    public byte[] getFileNameBytes() {
        return fileNameBytes;
    }
    public void increaseVersion(){
        version++;
    }
    public void setFileNameBytes(byte[] fileNameBytes) {
        this.fileNameBytes = fileNameBytes;
    }

    public UploadedFile(String pathname) {
        super(pathname);
        this.version=0;
        this.concurrentDownloaded = new AtomicInteger(0);

    }
    public boolean isLocked(){return lock;}
}
