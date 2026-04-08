package ru.vk.apps.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vk.apps.domain.model.App
import ru.vk.common.R
import ru.vk.common.presentation.theme.RuStoreBlue

@Composable
fun AppsSuccessScreen(
    apps: List<App>,
    onAppClick: (id: String) -> Unit,
    onAppLogoClick: (String) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .background(RuStoreBlue)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 48.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
//            AsyncImage(
//                Constants.RU_STORE_ICON_URL,
//                null,
//                modifier = Modifier.size(40.dp)
//            )
            Text(
                stringResource(R.string.ru_store),
                style = MaterialTheme.typography.titleLarge
            )
            Icon(
                Icons.Filled.QrCode,
                null,
                modifier = Modifier.size(40.dp),
                tint = Color.White
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(RuStoreBlue)
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
        ) {
            items(
                apps.size,
                { index -> apps[index].id }) { index ->
                Column {
                    AppCard(
                        apps[index],
                        {
                            onAppClick(apps[index].id)
                        },
                        {
                            onAppLogoClick(it)
                        },
                        iconSize = 50.dp
                    )
                    if (index < apps.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.onSurface,
                            thickness = 1.dp
                        )
                    }
                }
            }
        }
    }
}