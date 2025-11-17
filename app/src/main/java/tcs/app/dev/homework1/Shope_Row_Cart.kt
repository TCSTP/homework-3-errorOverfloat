package tcs.app.dev.homework1

import android.widget.Button
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowCircleLeft
import androidx.compose.material.icons.outlined.ArrowCircleRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tcs.app.dev.homework1.data.Cart
import tcs.app.dev.homework1.data.Euro
import tcs.app.dev.homework1.data.Item
import tcs.app.dev.homework1.data.MockData.Banana
import tcs.app.dev.homework1.data.MockData.ExampleShop
import tcs.app.dev.homework1.data.MockData.getImage
import tcs.app.dev.homework1.data.MockData.getName
import tcs.app.dev.homework1.data.minus
import tcs.app.dev.homework1.data.plus
import tcs.app.dev.homework1.data.times
import tcs.app.dev.ui.theme.AppTheme

@Composable
fun Cart_Row_Item(
    item: Item,
    cart: Cart,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onSelected: (Cart) -> Unit = {}
) {
    Cart_Row_Item(
        image = { modifier -> Image(painterResource(getImage(item)), contentDescription = null, modifier = modifier) },
        title = { modifier -> Text(stringResource(getName(item)), modifier = modifier) },
        price = { modifier -> Text(((ExampleShop.prices[item] ?: Euro(0u)) * (cart.items[item]?:0u)).toString(), modifier = modifier) },
        count = { modifier -> Text((cart.items[item]?:0u).toString(), modifier = modifier) },
        cart = cart,
        item = item,
        enableRightButton = (cart.items[item]?:0u) < 12u,
        selected = selected,
        onSelected = onSelected,
        modifier = modifier
    )
}

@Composable
fun Cart_Row_Item(
    image: @Composable (Modifier) -> Unit,
    title: @Composable (Modifier) -> Unit,
    price: @Composable (Modifier) -> Unit,
    count: @Composable (Modifier) -> Unit,
    cart: Cart,
    item: Item,
    enableRightButton: Boolean,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onSelected: (Cart) -> Unit = {}
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
            image(
                Modifier
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.medium)
            )

            title(
                Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )

            price(
                Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )

            Button(onClick = {(cart - item).let(onSelected)}) {
                Icon(
                    Icons.Outlined.ArrowCircleLeft,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                )
            }

                count(
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                )


                Button(
                    onClick = {(cart + item).let(onSelected)},
                    enabled = enableRightButton,
                    modifier = modifier.then(

                            if (enableRightButton) Modifier else Modifier.alpha(0.5F)

                            )
                ) {
                    Icon(
                        Icons.Outlined.ArrowCircleRight,
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
    fun RadioRowSelectedPreviewCart() {
        AppTheme {
            Cart_Row_Item(
                item = Banana,
                cart = Cart(ExampleShop),
                selected = true
            )
        }
    }

