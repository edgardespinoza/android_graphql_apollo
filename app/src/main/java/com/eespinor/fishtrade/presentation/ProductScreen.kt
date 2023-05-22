package com.eespinor.fishtrade.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.eespinor.fishtrade.domain.model.ProductDetailModel
import com.eespinor.fishtrade.domain.model.ProductModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductScreen(
    state: ProductViewModel.ProductState,
    onSelectProduct: (code: String) -> Unit,
    onDismissProductDialog: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            ListProducts(state, onSelectProduct)

            state.selectedProduct?.let {
                ProductDialog(
                    productDetailModel = state.selectedProduct,
                    onDismiss = onDismissProductDialog,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.White)
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ListProducts(
    state: ProductViewModel.ProductState, onSelectProduct: (code: String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.products) { product ->
            ProductItem(product = product, modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onSelectProduct(product.nodeId)
                }
                .padding(16.dp))
        }
    }
}

@Composable
fun ProductDialog(
    productDetailModel: ProductDetailModel, onDismiss: () -> Unit, modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(modifier = modifier) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                GlideImage(
                    imageModel = { productDetailModel.image },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                )

            }
            Text(text = productDetailModel.name)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = productDetailModel.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
private fun ProductItem(
    product: ProductModel, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = product.name, fontSize = 30.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = product.stock.toString(), fontSize = 24.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = product.price.toString(), fontSize = 24.sp)
        }
    }
}