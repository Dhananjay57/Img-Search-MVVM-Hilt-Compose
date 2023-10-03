package com.dhananjay.imgsearch.ui.component

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.dhananjay.imgsearch.R
import com.dhananjay.imgsearch.databinding.FragmentFirstBinding
import com.dhananjay.imgsearch.ui.screens.ImageSearchContent
import com.dhananjay.imgsearch.ui.theme.ImageSearchTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {
                ImageSearchTheme {
                    // A surface container using the 'background' color from the theme
                    MyApp {
                        ImageSearchContent()
                    }
                }
            }
        }


    }

    @Composable
    fun MyApp(content: @Composable () -> Unit) {
        content()
    }
}