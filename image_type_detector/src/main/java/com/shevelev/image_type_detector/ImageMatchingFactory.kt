package com.shevelev.image_type_detector

/**
 * Creates matching for images
 */
object ImageMatchingFactory {
    /**
     * Jpeg
     */
    fun getJpegSignature(): ImageMatching =
        ImageMatching(
            byteArrayOf(
                0xFF.toByte(),
                0xD8.toByte(),
                0xFF.toByte()
            ),
            ImageType.JPEG
        )

    /**
     * Png
     */
    fun getPngSignature(): ImageMatching =
        ImageMatching(
            byteArrayOf(
                0x89.toByte(),
                'P'.toByte(),
                'N'.toByte(),
                'G'.toByte(),
                0x0D.toByte(),
                0x0A.toByte(),
                0x1A.toByte(),
                0x0A.toByte()
            ),
            ImageType.PNG
        )
}