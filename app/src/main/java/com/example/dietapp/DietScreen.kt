package com.example.dietapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dietapp.model.Diet


@Composable
fun DietList(
    diet: List<Diet>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {

    LazyColumn(contentPadding = contentPadding) {
        itemsIndexed(diet) { index, item ->
            DietListItem(item, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
        }
    }

}


@Composable
fun DietListItem(
    diet: Diet,
    modifier: Modifier = Modifier
) {

    var isDescriptionVisible by remember { mutableStateOf(false) }
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier.padding(16.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(diet.dayResourceId),
                style = MaterialTheme.typography.labelMedium,

                )
            Text(
                text = stringResource(diet.nameResourceId),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
            Image(
                painter = painterResource(diet.imageResourceId),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable { isDescriptionVisible = !isDescriptionVisible }
                    .clip(shape = RoundedCornerShape(10.dp))


            )
            AnimatedVisibility(
                visible = isDescriptionVisible,
                enter = slideInVertically(initialOffsetY = {it})+ fadeIn(),
                exit = slideOutVertically(targetOffsetY = {it})+ fadeOut()
            ) {
                Text(
                    text = stringResource(diet.descriptionResourceId),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 8.dp)

                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun DietItemPreview() {
    val diet = Diet(
        dayResourceId = R.string.day_1,
        nameResourceId = R.string.diet_name_1,
        descriptionResourceId = R.string.diet_description_1,
        imageResourceId = R.drawable.dish_1
    )
    DietListItem(diet)
}