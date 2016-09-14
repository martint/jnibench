package org.weakref;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Benchmark)
public class Data
{
    @Param({
            "0",
            "1048576",
            "2097152",
            "3145728",
            "4194304",
            "5242880",
            "6291456",
            "7340032",
            "8388608",
            "9437184",
            "10485760",
            "20971520",
            "31457280",
            "41943040",
            "52428800",
            "62914560",
            "73400320",
            "83886080",
            "94371840"
    })
    private int size;

    private byte[] buffer;

    @Setup
    public void setup()
    {
        buffer = new byte[size];
        ThreadLocalRandom.current().nextBytes(buffer);
    }

    public byte[] getBuffer()
    {
        return buffer;
    }
}
