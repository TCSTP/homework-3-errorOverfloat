package tcs.app.dev.homework1

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.Discount
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Percent
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tcs.app.dev.homework1.data.MockData.ExampleDiscounts
import tcs.app.dev.ui.theme.AppTheme
import tcs.app.dev.R
import tcs.app.dev.homework1.data.Discount

@Composable
fun Row(
    title: Discount,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onSelected: () -> Unit = {}
) {
    Row(
        image = if (title.toString()[0] == 'P') Icons.Outlined.Percent
        else if (title.toString()[0] == 'F') Icons.Outlined.Payments
        else Icons.Outlined.Discount,
        title = { modifier -> Text(getDiscountString(title), modifier = modifier) },
        selected = selected,
        onSelected = onSelected,
        modifier = modifier
    )
}

@Composable
fun Row(
    image: ImageVector,
    title: @Composable (Modifier) -> Unit,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onSelected: () -> Unit = {}
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
        color = color,
        onClick = onSelected
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                image,
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp))

            title(
                Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )

            Button(onClick = {}) {
                Icon(
                    Icons.Outlined.AddShoppingCart,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                )
            }
        }
    }
}


@Composable
fun getDiscountString(discount: Discount): String = when (discount) {
    is Discount.Percentage -> String.format(stringResource(R.string.percentage_off),discount.value)
    is Discount.Fixed -> String.format(stringResource(R.string.amount_off),discount.amount)
    is Discount.Bundle -> String.format(stringResource(R.string.pay_n_items_and_get),discount.amountItemsPay,discount.item.id,discount.amountItemsGet)
    else -> error("unexpected case in fun getDiscount")
}

@Preview(showBackground = true)
@Composable
fun RadioRowSelectedPreviewDiscount0() {
    AppTheme {
        Row(
            title = ExampleDiscounts[0],
            selected = true
        )
    }
}
@Preview(showBackground = true)
@Composable
fun RadioRowSelectedPreviewDiscount1() {
    AppTheme {
        Row(
            title = ExampleDiscounts[1],
            selected = true
        )
    }
}
@Preview(showBackground = true)
@Composable
fun RadioRowSelectedPreviewDiscount2() {
    AppTheme {
        Row(
            title = ExampleDiscounts[2],
            selected = true
        )
    }
}
//Icons.Outlined.Percent
//Icons.Outlined.Payments
//image = Icons.Outlined.Discount