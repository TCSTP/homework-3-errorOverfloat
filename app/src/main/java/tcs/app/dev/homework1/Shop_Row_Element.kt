package tcs.app.dev.homework1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tcs.app.dev.exercise.university.data.Option
import tcs.app.dev.exercise.university.solution.RadioRow
import tcs.app.dev.homework1.data.Euro
import tcs.app.dev.homework1.data.MockData.Apple
import tcs.app.dev.homework1.data.MockData.ExampleShop
import tcs.app.dev.homework1.data.MockData.getImage
import tcs.app.dev.homework1.data.MockData.getName
import tcs.app.dev.ui.theme.AppTheme


@Composable
fun Row(
    image: Painter,
    title: String,
    price: Euro,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onSelected: () -> Unit = {}
) {
    Row(
        image = { modifier -> Image(image, contentDescription = null, modifier = modifier) },
        title = { modifier -> Text(title, modifier = modifier) },
        price = { modifier -> Text(price.toString(), modifier = modifier) },
        selected = selected,
        onSelected = onSelected,
        modifier = modifier
    )
}

@Composable
fun Row(
    image: @Composable (Modifier) -> Unit,
    title: @Composable (Modifier) -> Unit,
    price: @Composable (Modifier) -> Unit,
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

@Preview(showBackground = true)
@Composable
fun RadioRowSelectedPreviewShop() {
    AppTheme {
        Row(
            image = painterResource(getImage(Apple)),
            title = stringResource(getName(Apple)),
            price = ExampleShop.prices[Apple]?: Euro(0u),
            selected = true
        )
    }
}
