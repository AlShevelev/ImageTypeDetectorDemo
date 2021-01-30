package com.shevelev.image_type_detector

/**
 * Checks that a tested sequence of bytes matches a standard sequence of bytes
 * @param standardStamp unique set of bytes from an image header
 * @param format a type of an image for this checking
 */
class ImageMatching(
    private val standardStamp: ByteArray,
    val format: ImageFormat
) {
    /**
     * Size of the standard stamp in bytes
     */
    val size: Int
        get() = standardStamp.size

    /**
     * Checks that the tested bytes array matches the standard bytes array
     * @param testedStamp array to check
     * @return true if matches
     */
    fun check(testedStamp: ByteArray): Boolean {
        if (size > testedStamp.size) {
            return false
        }

        for (i in standardStamp.indices) {
            if (standardStamp[i] != testedStamp[i]) {
                return false
            }
        }

        return true
    }
}