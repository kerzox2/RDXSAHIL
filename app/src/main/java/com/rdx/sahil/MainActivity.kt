package com.rdx.sahil

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RDXSahilUI()
        }
    }
}

@Composable
fun RDXSahilUI() {
    val context = LocalContext.current
    var showPanel by remember { mutableStateOf(false) }
    var hawkMode by remember { mutableStateOf(false) }
    var ultraHDR by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF0A0005))) {

        // Left Stats Panel
        Column(modifier = Modifier
            .padding(16.dp)
            .width(160.dp)
            .align(Alignment.CenterStart)) {

            Text("RDX SAHIL", fontSize = 26.sp, color = Color.Red, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(30.dp))

            StatItem("CPU", "45%")
            StatItem("GPU", "72%")
            StatItem("FPS", "89")
            StatItem("TEMP", "37°C")
        }

        // Center Launch Button
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Button(
                onClick = { launchPubg(context) },
                modifier = Modifier.size(270.dp).clip(CircleShape),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("LAUNCH\nPUBG", fontSize = 28.sp, fontWeight = FontWeight.Bold, lineHeight = 36.sp)
            }
        }

        // Floating Settings Button
        FloatingActionButton(
            onClick = { showPanel = true },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 24.dp),
            containerColor = Color.Red
        ) {
            Text("⚙️", fontSize = 30.sp)
        }

        // Right Sliding Panel
        if (showPanel) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(310.dp)
                    .align(Alignment.CenterEnd),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1F0011)),
                shape = RoundedCornerShape(topStart = 28.dp, bottomStart = 28.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("FEATURES", fontSize = 22.sp, color = Color.White, fontWeight = FontWeight.Bold)

                    Spacer(Modifier.height(20.dp))

                    FeatureToggle("Hawk Mode", hawkMode) { hawkMode = it; toggleHawkMode(context, it) }
                    FeatureToggle("Ultra HDR Graphics", ultraHDR) { ultraHDR = it; enableUltraHDR(context, it) }
                    FeatureButton("GFX Ultra Boost", context)
                    FeatureButton("Touch Response", context)
                    FeatureButton("RAM Cleaner", context)
                    FeatureButton("Network Booster", context)
                    FeatureButton("Notification Blocker", context)
                    FeatureButton("Thermal Guard", context)

                    Spacer(Modifier.height(16.dp))

                    Button(
                        onClick = { showPanel = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Close Panel")
                    }
                }
            }
        }
    }
}

@Composable
fun StatItem(title: String, value: String) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(Color(0xFF2A0019))
    ) {
        Row(
            modifier = Modifier.padding(14.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, color = Color.White)
            Text(value, color = Color.Red, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun FeatureToggle(text: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text, color = Color.White, modifier = Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = onChange)
    }
}

@Composable
fun FeatureButton(text: String, context: Context) {
    Button(
        onClick = { Toast.makeText(context, "$text Activated", Toast.LENGTH_SHORT).show() },
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
    ) {
        Text(text)
    }
}

// Helper Functions
private fun launchPubg(context: Context) {
    val intent = context.packageManager.getLaunchIntentForPackage("com.tencent.ig")
    if (intent != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "PUBG Mobile Not Installed!", Toast.LENGTH_LONG).show()
    }
}

private fun toggleHawkMode(context: Context, enable: Boolean) {
    Toast.makeText(context, if (enable) "Hawk Mode ON 🔥" else "Hawk Mode OFF", Toast.LENGTH_SHORT).show()
}

private fun enableUltraHDR(context: Context, enable: Boolean) {
    if (enable) {
        Toast.makeText(context, "Ultra HDR Graphics Activated\nRich Colors Applied!", Toast.LENGTH_LONG).show()
    }
}