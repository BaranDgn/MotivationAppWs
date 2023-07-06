package com.example.psikoappws.presenter.view

import android.app.Application
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SettingScreen() {
    var darkModeEnabled by remember { mutableStateOf(false) }
    var notificationEnabled by remember { mutableStateOf(true) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Settings",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            SettingItem(
                title = "Dark Mode",
                description = "Enable or disable dark mode",
                switchValue = darkModeEnabled,
                onSwitchValueChanged = { darkModeEnabled = it }
            )

            SettingItem(
                title = "Notification",
                description = "Enable or disable notifications",
                switchValue = notificationEnabled,
                onSwitchValueChanged = { notificationEnabled = it }
            )
        }
    }
}

@Composable
fun SettingItem(
    title: String,
    description: String,
    switchValue: Boolean,
    onSwitchValueChanged: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = title, style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = description, style = TextStyle(color = Color.Gray))
        }
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = switchValue,
            onCheckedChange = onSwitchValueChanged
        )
    }
}