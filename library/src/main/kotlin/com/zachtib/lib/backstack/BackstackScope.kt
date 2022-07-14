package com.zachtib.lib.backstack

interface BackstackScope<K> {
    fun push(key: K)
    fun pop()
}
