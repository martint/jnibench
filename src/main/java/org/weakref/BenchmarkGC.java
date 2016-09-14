package org.weakref;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.RunnerException;

import java.util.zip.Deflater;

import static org.weakref.Benchmarks.getRunner;

@Warmup(iterations = 5)
@State(Scope.Benchmark)
public class BenchmarkGC
{
    private static final int SIZE = 10 * 1024;

    @Benchmark
    @Group("deflate")
    @GroupThreads(1)
    public byte[] work(CompressionBuffer data)
    {
        Deflater deflater = new Deflater();
        deflater.setInput(data.getInput());
        deflater.finish();
        deflater.deflate(data.getOutput());

        return data.getOutput();
    }

    @Benchmark
    @Group("deflate")
    @GroupThreads(14)
    public byte[] allocate()
    {
        return new byte[SIZE];
    }


    public static void main(String[] args)
            throws RunnerException
    {
        getRunner(BenchmarkGC.class, true).run();
    }
}
