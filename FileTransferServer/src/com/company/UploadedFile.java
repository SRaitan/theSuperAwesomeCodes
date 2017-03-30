package com.company;
import java.io.File;

public class UploadedFile extends File{
    private File file;
    private int version;
    private byte[] fileNameBytes; //name in server
    boolean lock;

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

    }
    public boolean isLock (){return lock;}
}
