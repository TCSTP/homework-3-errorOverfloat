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
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tcs.app.dev.R
import tcs.app.dev.exercise.university.data.Option
import tcs.app.dev.homework1.data.Cart
import tcs.app.dev.homework1.data.MockData.ExampleShop
import tcs.app.dev.homework1.data.MockData.getImage
import tcs.app.dev.homework1.data.MockData.getName
import tcs.app.dev.ui.theme.AppTheme

@Preview(showBackground = true)
@Composable
fun ShopTab(modifier: Modifier = Modifier, cart : Cart = Cart(ExampleShop), lambdaCart: (Cart) -> Unit = {}, lambda: (String) -> Unit = {}) {
    //var cartVar: Cart by remember { mutableStateOf(cart) }

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
                Button(onClick = {
                    //cartVar.let(lambdaCart)
                    "cart".let(lambda)
                }) {
                    Icon(
                        Icons.Outlined.ShoppingCart,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(32.dp),
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }
                Text(
                    stringResource(R.string.name_shop),
                    modifier = Modifier.padding(horizontal = 4.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(16.dp)
            ) {
                Button(
                    onClick = {},
                    colors = buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSecondary,
                        contentColor = MaterialTheme.colorScheme.secondary,
                        disabledContainerColor =
                            MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f),
                        disabledContentColor =
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)
                    ),
                ) {
                    Text(stringResource(R.string.label_shop))
                }
                Button(
                    onClick = {
                        //cartVar.let(lambdaCart)
                        "discount".let(lambda)
                              },
                    colors = buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSecondary,
                        contentColor = MaterialTheme.colorScheme.secondary,
                        disabledContainerColor =
                            MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f),
                        disabledContentColor =
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)
                    ),
                ) {
                    Text(stringResource(R.string.label_discounts))
                }
            }
        }
    ){ contentPadding ->
    LazyColumn(
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(ExampleShop.items.toList()) { option ->
            Row(
                name = option,
                cart = cart,
                selected = true,
                onSelected = lambdaCart
            )
        }
    }
    }
}