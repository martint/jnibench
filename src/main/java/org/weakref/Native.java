package org.weakref;

class Native
{
    static {
        System.loadLibrary("native");
    }

    public static native void nothing();
    public static native void array(byte[] data);
}
