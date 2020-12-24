package app.web.drjackycv.domain.extension

import android.os.StrictMode

fun <T> allowReads(block: () -> T): T {
    val oldPolicy = StrictMode.allowThreadDiskReads()
    try {
        return block()
    } finally {
        StrictMode.setThreadPolicy(oldPolicy)
    }
}

fun <T> allowWrites(block: () -> T): T {
    val oldPolicy = StrictMode.allowThreadDiskWrites()
    try {
        return block()
    } finally {
        StrictMode.setThreadPolicy(oldPolicy)
    }
}