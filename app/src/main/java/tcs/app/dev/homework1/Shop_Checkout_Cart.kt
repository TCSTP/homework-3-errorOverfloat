package tcs.app.dev.homework1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tcs.app.dev.homework1.data.Euro
import tcs.app.dev.R
import tcs.app.dev.homework1.data.Cart
import tcs.app.dev.homework1.data.Discount
import tcs.app.dev.homework1.data.MockData.ExampleDiscounts
import tcs.app.dev.homework1.data.MockData.ExampleShop
import tcs.app.dev.ui.theme.AppTheme

@Composable
fun CheckoutPrice(
    cart: Cart,
    originalCart: Cart = Cart(ExampleShop),
    discountList: List<Discount> = ExampleDiscounts,
    lambdaDiscount: (List<Discount>) -> Unit = {},
    lambdaCart: (Cart) -> Unit = {},
    lambda: (String) -> Unit = {},
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        title = { modifier -> Text(String.format(stringResource(R.string.total_price), (cart.price).toString()), modifier = modifier) },
        lambdaCart = lambdaCart,
        originalCart = originalCart,
        discountList = discountList,
        lambda = lambda,
        lambdaDiscount = lambdaDiscount,
        enableButton = cart.itemCount > 0u,
        selected = selected,
        modifier = modifier
    )
}

@Composable
fun Row(
    title: @Composable (Modifier) -> Unit,
    originalCart: Cart,
    discountList: List<Discount>,
    lambdaDiscount: (List<Discount>) -> Unit = {},
    lambdaCart: (Cart) -> Unit = {},
    lambda: (String) -> Unit = {},
    enableButton: Boolean,
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    val border = BorderStroke(
        width = 1.dp,
        color =
            if (selected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.outline
    )

    val color =
        if (selected) MaterialTheme.colorScheme.primaryContainer
        else MaterialTheme.colorScheme.surface

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        border = border,
        color = color
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            title(
                Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )

            Button(onClick = {
                discountList.let(lambdaDiscount)
                originalCart.let(lambdaCart)
                "shop".let(lambda)
            },
                enabled = enableButton,
                modifier = modifier.then(

                    if (enableButton) Modifier else Modifier.alpha(0.5F)

                )
                ) {
                Icon(
                    Icons.Outlined.Paid,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckoutPreviewCart() {
    AppTheme {
        CheckoutPrice(
            cart = Cart(ExampleShop),
            selected = false
        )
    }
}