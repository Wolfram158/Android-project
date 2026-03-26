package ru.vk.apps.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.vk.apps.domain.model.App

@Composable
fun AppCard(
    app: App,
    onAppClick: () -> Unit,
    onAppLogoClick: (String) -> Unit,
    iconSize: Dp = 30.dp
) {
    Card(
        onClick = {
            onAppClick()
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                app.iconUrl,
                null,
                modifier = Modifier
                    .size(iconSize)
                    .clickable {
                        onAppLogoClick(app.name)
                    }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = app.name,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = app.description,
                    style = MaterialTheme.typography.labelMedium.copy(color = Color.Black)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = app.category,
                    style = MaterialTheme.typography.labelMedium.copy(color = Color.Gray)
                )
            }
        }
    }
}