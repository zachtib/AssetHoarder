package com.zachtib.libtest

import org.junit.runners.model.Statement

inline fun statement(crossinline evaluate: () -> Unit): Statement {
    return object : Statement() {
        override fun evaluate() = evaluate()
    }
}
