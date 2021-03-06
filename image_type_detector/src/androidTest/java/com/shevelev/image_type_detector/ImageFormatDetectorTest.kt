package com.shevelev.image_type_detector

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.BeforeClass

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ImageFormatDetectorTest {
    companion object {
        private lateinit var context: Context
        private lateinit var imageFormatDetector: ImageFormatDetector

        @BeforeClass
        @JvmStatic
        fun init() {
            context = InstrumentationRegistry.getInstrumentation().context

            imageFormatDetector = ImageFormatDetector(
                listOf(
                    ImageMatchingFactory.getJpegSignature(),
                    ImageMatchingFactory.getPngSignature()
                )
            )
        }
    }

    @Test
    fun checkJpeg() {
        // Arrange
        val stream = context.assets.open("sample_jpeg.jpg")

        // Act
        val imageType = imageFormatDetector.getImageType(stream)
        stream.close()

        // Assert
        assertEquals(ImageFormat.JPEG, imageType)
    }

    @Test
    fun checkPng() {
        // Arrange
        val stream = context.assets.open("sample_png.png")

        // Act
        val imageType = imageFormatDetector.getImageType(stream)
        stream.close()

        // Assert
        assertEquals(ImageFormat.PNG, imageType)
    }

    @Test
    fun checkUndefined() {
        // Arrange
        val stream = context.assets.open("sample_undefined.txt")

        // Act
        val imageType = imageFormatDetector.getImageType(stream)
        stream.close()

        // Assert
        assertEquals(ImageFormat.UNDEFINED, imageType)
    }

    @Test
    fun checkEmpty() {
        // Arrange
        val stream = context.assets.open("sample_empty.txt")

        // Act
        val imageType = imageFormatDetector.getImageType(stream)
        stream.close()

        // Assert
        assertEquals(ImageFormat.UNDEFINED, imageType)
    }

    @Test
    fun checkNull() {
        // Arrange
        val stream = null

        // Act
        val imageType = imageFormatDetector.getImageType(stream)

        // Assert
        assertEquals(ImageFormat.UNDEFINED, imageType)
    }
}