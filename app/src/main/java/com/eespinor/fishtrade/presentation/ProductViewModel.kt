package com.eespinor.fishtrade.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eespinor.fishtrade.domain.model.ProductDetailModel
import com.eespinor.fishtrade.domain.model.ProductModel
import com.eespinor.fishtrade.domain.usecase.GetProductUseCase
import com.eespinor.fishtrade.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProductState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _state.update { it.copy(
                products = getProductsUseCase(), isLoading = false
            ) }
        }
    }

    fun selectProduct(code:String){
        viewModelScope.launch {
            _state.update { it.copy(
                selectedProduct = getProductUseCase(code)
            ) }
        }
    }

    fun dismissProductDialog(){
        _state.update { it.copy(
            selectedProduct = null
        ) }
    }


    data class ProductState(
        val products: List<ProductModel> = listOf(),
        val isLoading: Boolean = false,
        val selectedProduct: ProductDetailModel? = null
    )
}