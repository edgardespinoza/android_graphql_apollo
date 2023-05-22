package com.eespinor.fishtrade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.eespinor.fishtrade.presentation.ProductScreen
import com.eespinor.fishtrade.presentation.ProductViewModel
import com.eespinor.fishtrade.ui.theme.FishTradeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FishTradeTheme {
                val viewModel = hiltViewModel<ProductViewModel>()
                val productState by viewModel.state.collectAsState()

                ProductScreen(state = productState,
                    onSelectProduct =  viewModel::selectProduct,
                    onDismissProductDialog = viewModel::dismissProductDialog)
            }
        }
    }
}
