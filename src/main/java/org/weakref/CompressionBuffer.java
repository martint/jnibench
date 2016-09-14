package org.weakref;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Benchmark)
public class CompressionBuffer
{
    @Param({
            "0",
            "2",
            "4",
            "6",
            "8",
            "10",
            "12",
            "14",
            "16",
            "18",
            "20",
            "22",
            "24",
            "26",
            "28",
            "30",
            "32",
            "34",
            "36",
            "38",
            "40",
            "42",
            "44",
            "46",
            "48",
            "50"
    })
    private int size;

    private byte[] input;
    private byte[] output;

    @Setup
    public void setup()
    {
        input = new byte[size * 1024 * 1024];
        output = new byte[2 * input.length];
        ThreadLocalRandom.current().nextBytes(input);
    }

    public byte[] getInput()
    {
        return input;
    }

    public byte[] getOutput()
    {
        return output;
    }
}
