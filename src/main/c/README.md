Compile for OS X:

```
gcc -O2 -fPIC -shared -o libnative.dylib -I $JAVA_HOME/include -I $JAVA_HOME/include/darwin native.c
```

Compile for Linux:

```
gcc -O2 -fPIC -shared -o libnative.so -I $JAVA_HOME/include -I $JAVA_HOME/include/linux native.c
```
