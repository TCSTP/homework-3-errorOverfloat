package tcs.app.dev.homework1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.SubdirectoryArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tcs.app.dev.R
import tcs.app.dev.homework1.data.Cart
import tcs.app.dev.homework1.data.Discount.*
import tcs.app.dev.homework1.data.Item
import tcs.app.dev.homework1.data.MockData.ExampleDiscounts
import tcs.app.dev.homework1.data.MockData.ExampleShop
import tcs.app.dev.homework1.data.euro

@Preview(showBackground = true)
@Composable
fun CartTab(
    modifier: Modifier = Modifier,
    cart : Cart = Cart(
        ExampleShop,
        mapOf(Item("Apple") to 5u),
        listOf(Fixed(2u.euro))
    ),
    lambdaCart: (Cart) -> Unit = {}, lambda: (String) -> Unit = {}) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondary)
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {"shop".let(lambda)}) {
                    Icon(
                        Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(32.dp),
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }
                Text(
                    stringResource(R.string.title_cart),
                    modifier = Modifier.padding(horizontal = 4.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        },
        bottomBar = {
            CheckoutPrice(
                cart = cart,
                lambdaCart = lambdaCart,
                lambda = lambda,
                selected = true,
                modifier = Modifier
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondaryContainer),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(cart.items.toList()) { option ->
                Cart_Row_Item(
                    item = option.first,
                    cart = cart,
                    onSelected = lambdaCart,
                    selected = true
                )
            }
            items(cart.discounts) { option ->
                Row(
                    title = option,
                    cart = cart,
                    onSelected = lambdaCart,
                    selected = true
                )
            }
        }
    }
}