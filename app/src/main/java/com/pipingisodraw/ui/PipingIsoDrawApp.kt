package com.pipingisodraw.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PipingIsoDrawApp() {
    Surface(modifier = Modifier.fillMaxSize()) {
        DrawingScreen()
    }
}

@Composable
private fun DrawingScreen() {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isCompact = maxWidth < 600.dp
        Scaffold(
            topBar = { DrawingTopBar() },
            bottomBar = { StatusBar() }
        ) { padding ->
            if (isCompact) {
                CompactDrawingLayout(modifier = Modifier.padding(padding))
            } else {
                TabletDrawingLayout(modifier = Modifier.padding(padding))
            }
        }
    }
}

@Composable
private fun DrawingTopBar() {
    TopAppBar(
        title = { Text(text = "Piping ISO Draw") },
        actions = {
            TextButton(onClick = {}) { Text("Undo") }
            TextButton(onClick = {}) { Text("Redo") }
            TextButton(onClick = {}) { Text("Zoom Fit") }
            TextButton(onClick = {}) { Text("Grid") }
            TextButton(onClick = {}) { Text("Exportar") }
        }
    )
}

@Composable
private fun StatusBar() {
    BottomAppBar {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "DN atual: 100")
            Text(text = "Norma: ASME")
            Text(text = "Componentes: 0")
        }
    }
}

@Composable
private fun TabletDrawingLayout(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxSize()) {
        PanelCard(title = "Componentes", modifier = Modifier.weight(0.22f))
        CanvasPlaceholder(modifier = Modifier.weight(0.56f))
        PanelCard(title = "Propriedades", modifier = Modifier.weight(0.22f))
    }
}

@Composable
private fun CompactDrawingLayout(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        CanvasPlaceholder(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(8.dp))
        PanelCard(title = "Componentes", modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun PanelCard(title: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .border(1.dp, MaterialTheme.colorScheme.outline)
            .padding(12.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Conteúdo em implementação")
    }
}

@Composable
private fun CanvasPlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .border(1.dp, MaterialTheme.colorScheme.outline)
            .background(Color(0xFFEFF2F6)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Canvas isométrico")
    }
}
