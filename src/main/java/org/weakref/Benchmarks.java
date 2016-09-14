package org.weakref;

import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

class Benchmarks
{
    public static Runner getRunner(Class<?> clazz, boolean profileGc)
    {
        ChainedOptionsBuilder builder = new OptionsBuilder()
                .verbosity(VerboseMode.NORMAL)
                .jvmArgsAppend(
                        "-Djava.library.path=src/main/c",
                        "-XX:+UseG1GC",
                        "-mx1G",
                        "-ms1G",
                        "-XX:G1HeapRegionSize=32m")
                .include(".*\\." + clazz.getSimpleName() + ".*");

        if (profileGc) {
            builder.addProfiler(GCProfiler.class);
        }
        return new Runner(builder.build());
    }

    public static Runner getRunner(Class<?> clazz)
    {
        return getRunner(clazz, false);
    }
}
