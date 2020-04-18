package app.web.drjackycv.presentation.extension

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import java.io.IOException
import java.io.Serializable

inline fun <reified T : Parcelable> Fragment.getParcelableParam(
    tag: String,
    errorMessage: String = "${this::class.java.simpleName} needs a ${T::class.java.simpleName} to populate"
): T =
    if (arguments == null) {
        throw IOException(errorMessage)
    } else {
        arguments?.getParcelable<Parcelable>(tag) as T
    }

inline fun <reified T : Serializable> Fragment.getSerializableParam(
    tag: String,
    errorMessage: String = "${this::class.java.simpleName} needs a ${T::class.java.simpleName} to populate"
): T =
    (arguments?.getSerializable(tag) ?: throw IOException(errorMessage)) as T

inline fun <reified T : Parcelable> Fragment.getNullableParcelableParam(tag: String): T? =
    arguments?.getParcelable(tag) as T?

inline fun <reified T : Serializable> Fragment.getNullableSerializableParam(tag: String): T? =
    arguments?.getSerializable(tag) as T?

inline fun <reified T : Any> Fragment.setParams(vararg pairs: Pair<String, T?>): Bundle {
    val bundle = bundleOf(*pairs)
    arguments = bundle

    return bundle
}