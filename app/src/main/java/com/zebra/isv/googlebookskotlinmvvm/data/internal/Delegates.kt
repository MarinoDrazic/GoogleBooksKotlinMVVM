package com.zebra.isv.googlebookskotlinmvvm.data.internal

import kotlinx.coroutines.*

//this does something. probably.
fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}