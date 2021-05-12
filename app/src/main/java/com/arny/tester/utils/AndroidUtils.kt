package com.arny.tester.utils

import android.content.Intent
import android.os.Bundle

fun Intent?.dump(): String? {
    if (this != null) {
        val bundle = this.extras
        if (bundle != null) {
            val stringBuilder = StringBuilder()
            for (key in bundle.keySet()) {
                val value = bundle[key]
                if (value != null) {
                    stringBuilder.append(
                        String.format(
                            "\nkey:%s  val:%s  classname:(%s)",
                            key,
                            value.toString(),
                            value.javaClass.name
                        )
                    )
                }
            }
            return stringBuilder.toString()
        }
    }
    return null
}

fun Bundle?.dump(): String? {
    if (this != null) {
        val stringBuilder = StringBuilder()
        for (key in this.keySet()) {
            val value = this[key]
            if (value != null) {
                stringBuilder.append(
                    String.format(
                        "\nkey:%s  val:%s  classname:(%s)",
                        key,
                        value.toString(),
                        value.javaClass.name
                    )
                )
            }
        }
        return stringBuilder.toString()
    }
    return null
}