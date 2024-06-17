package com.khorzon.mybraviaremote.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.khorzon.mybraviaremote.ApplicationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppItem(app: ApplicationItem, onItemClick: () -> Unit) {
    ElevatedCard(
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surface,
            containerColor = Color.White,
        ),
        onClick = {onItemClick()},
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp)
            .padding(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ){
            Text(
                text = "Filled",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )
            Image(
                painter = rememberAsyncImagePainter(app.icon),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
        }
    }
}