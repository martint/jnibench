package org.weakref;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.RunnerException;

import java.util.concurrent.TimeUnit;

import static org.weakref.Benchmarks.getRunner;

@Warmup(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
public class BenchmarkLatency
{
    private static final int ITERATIONS = 1_000_000;
    private static final byte[] EMPTY = new byte[0];

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @OperationsPerInvocation(ITERATIONS)
    public void jniBaseline()
    {
        for (int i = 0; i < ITERATIONS; i++) {
            Native.nothing();
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @OperationsPerInvocation(ITERATIONS)
    public void javaBaseline()
    {
        for (int i = 0; i < ITERATIONS; i++) {
            doIt();
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @OperationsPerInvocation(ITERATIONS)
    public void emptyArray()
    {
        for (int i = 0; i < ITERATIONS; i++) {
            Native.array(EMPTY);
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmark(Data data)
    {
        Native.array(data.getBuffer());
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private void doIt()
    {
    }

    public static void main(String[] args)
            throws RunnerException
    {
        getRunner(BenchmarkLatency.class).run();
    }
}
