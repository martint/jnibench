#include "native.h"
#include <unistd.h>

JNIEXPORT void JNICALL Java_org_weakref_Native_nothing(JNIEnv* env, jclass clazz)
{
}

JNIEXPORT void JNICALL Java_org_weakref_Native_array(JNIEnv* env, jclass clazz, jbyteArray data)
{
    // get a pointer to the buffer (which implies getting a copy)
    jbyte* buffer = (*env)->GetByteArrayElements(env, data, NULL);

    // release the buffer (which copies it back in to the heap buffer)
    (*env)->ReleaseByteArrayElements(env, data, buffer, 0);
}
