package com.mypuresound.puresound

import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mypuresound.puresound.player.MusicPlayerManager
import com.mypuresound.puresound.player.MusicPlayerViewModel
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class MusicPlayerViewModelTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: MusicPlayerViewModel
    private val mockPlayerManager = mockk<MusicPlayerManager>(relaxed = true)

    @Before
    fun setup() {
        viewModel = MusicPlayerViewModel(
            playerManager = mockPlayerManager
        )
    }

    @Test
    fun `playSong sets isPlaying true and calls setPlaylist`() = runTest {
        val uri = mockk<Uri>()
        val playlist = listOf(uri)

        viewModel.playSong(uri, playlist)

        // Estado
        assertTrue(viewModel.isPlaying.value)

        // Comportamento
        verify {
            mockPlayerManager.setPlaylist(playlist, 0)
        }
    }

    @Test
    fun `pause sets isPlaying false and calls pause`() = runTest {
        viewModel.pause()

        assertFalse(viewModel.isPlaying.value)
        verify { mockPlayerManager.pause() }
    }

    @Test
    fun `next calls playerManager next`() {
        viewModel.nextMediaItem()
        verify { mockPlayerManager.seekToNextMediaItem() }
    }

    @Test
    fun `previous calls playerManager previous`() {
        viewModel.previousMediaItem()
        verify { mockPlayerManager.seekToPreviousMediaItem() }
    }

}