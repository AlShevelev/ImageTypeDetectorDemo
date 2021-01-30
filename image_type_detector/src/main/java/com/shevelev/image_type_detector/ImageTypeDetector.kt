package com.shevelev.image_type_detector

import android.content.Context
import android.net.Uri
import java.io.InputStream

/**
 * A facade logic for an image type detecting
 * @param checkers a full set of matchers
 */
class ImageTypeDetector(private val checkers: List<ImageMatching>) {
    /**
     * Calculates a type of an image
     */
    fun getImageType(context: Context, imageUri: Uri): ImageType {
        context.contentResolver.openInputStream(imageUri).use { inputStream ->
            if(inputStream != null) {
                return getImageType(inputStream)
            }
        }

        return ImageType.UNDEFINED
    }

    /**
     * Calculates a type of an image
     * @param inputStream an input stream with an image data
     */
    fun getImageType(inputStream: InputStream?): ImageType {
        val buffer = ByteArray(checkers.maxOf { it.size })
        var isRead = false

        if(inputStream != null) {
            inputStream.read(buffer)
            isRead = true
        }

        if(!isRead) {
            return ImageType.UNDEFINED
        }

        checkers.forEach {
            if(it.check(buffer)) {
                return it.type
            }
        }

        return ImageType.UNDEFINED
    }
}