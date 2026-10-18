package com.example.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HowToVote
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.Constituency
import com.example.data.District
import com.example.data.UPData
import com.example.ui.theme.CardBorderBlue
import com.example.ui.theme.GreenHover
import com.example.ui.theme.NavyLight
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.SoftBlueBg
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun HomeScreen(
    selectedDistrict: District?,
    selectedConstituency: Constituency?,
    constituencies: List<Constituency>,
    onSelectDistrict: (District) -> Unit,
    onSelectConstituency: (Constituency) -> Unit,
    onProceed: () -> Unit
) {
    var showDistrictDialog by remember { mutableStateOf(false) }
    var showConstituencyDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
    ) {
        // Top Header Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(NavyPrimary)
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .background(Color.White.copy(alpha = 0.15f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("🗳️", fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "जनता की राय",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 0.5.sp
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "उत्तर प्रदेश विधानसभा जनमत सर्वेक्षण",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.12f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 14.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "🔒 निष्पक्ष • सुरक्षित • गोपनीय",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        // Body Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(
                text = "सर्वेक्षण में भाग लें",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            // Card 1: Select District
            SelectionCard(
                stepNumber = "1",
                label = "अपना जिला चुनें",
                selectedValue = selectedDistrict?.nameHi,
                placeholder = "सूची से अपना जिला चुनें",
                icon = Icons.Default.LocationOn,
                iconColor = NavyPrimary,
                iconBg = SoftBlueBg,
                isSelected = selectedDistrict != null,
                testTag = "district_selector_card",
                onClick = { showDistrictDialog = true }
            )

            // Card 2: Select Constituency
            SelectionCard(
                stepNumber = "2",
                label = "अपनी विधानसभा चुनें",
                selectedValue = selectedConstituency?.let { "${it.nameHi} (${it.number})" },
                placeholder = if (selectedDistrict == null) "पहले जिला चुनें" else "अपनी विधानसभा क्षेत्र चुनें",
                icon = Icons.Default.HowToVote,
                iconColor = if (selectedDistrict != null) SuccessGreen else TextMuted,
                iconBg = if (selectedDistrict != null) Color(0xFFDCFCE7) else Color(0xFFF1F5F9),
                isSelected = selectedConstituency != null,
                isEnabled = selectedDistrict != null,
                testTag = "constituency_selector_card",
                onClick = {
                    if (selectedDistrict != null) {
                        showConstituencyDialog = true
                    }
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            // Proceed Button
            val isReady = selectedDistrict != null && selectedConstituency != null
            Button(
                onClick = onProceed,
                enabled = isReady,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("proceed_button"),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SuccessGreen,
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFFCBD5E1),
                    disabledContentColor = Color(0xFF64748B)
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "आगे बढ़ें (EVM मतपत्र)",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "आगे बढ़ें",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Text(
                text = "सूचना: यह एक स्वतंत्र जनमत सर्वेक्षण है, वास्तविक निर्वाचन मतदान नहीं।",
                fontSize = 11.sp,
                color = TextMuted,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    // District Picker Dialog
    if (showDistrictDialog) {
        DistrictPickerDialog(
            onDismiss = { showDistrictDialog = false },
            onSelect = { district ->
                onSelectDistrict(district)
                showDistrictDialog = false
            }
        )
    }

    // Constituency Picker Dialog
    if (showConstituencyDialog && selectedDistrict != null) {
        ConstituencyPickerDialog(
            district = selectedDistrict,
            constituencies = constituencies,
            onDismiss = { showConstituencyDialog = false },
            onSelect = { constituency ->
                onSelectConstituency(constituency)
                showConstituencyDialog = false
            }
        )
    }
}

@Composable
fun SelectionCard(
    stepNumber: String,
    label: String,
    selectedValue: String?,
    placeholder: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    iconBg: Color,
    isSelected: Boolean,
    isEnabled: Boolean = true,
    testTag: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) NavyLight else CardBorderBlue,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(enabled = isEnabled, onClick = onClick)
            .testTag(testTag),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(iconBg, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .background(NavyPrimary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stepNumber,
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = label,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSecondary
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = selectedValue ?: placeholder,
                    fontSize = 16.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) TextPrimary else TextMuted
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Selected",
                    tint = SuccessGreen,
                    modifier = Modifier.size(22.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Open dropdown",
                    tint = TextMuted,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun DistrictPickerDialog(
    onDismiss: () -> Unit,
    onSelect: (District) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val filtered = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            UPData.districts
        } else {
            UPData.districts.filter {
                it.nameHi.contains(searchQuery.trim(), ignoreCase = true) ||
                it.nameEn.contains(searchQuery.trim(), ignoreCase = true)
            }
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            shape = RoundedCornerShape(20.dp),
            color = Color.White,
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "अपना जिला चुनें (${filtered.size})",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = NavyPrimary
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "बंद करें", tint = TextSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("जिले का नाम खोजें...", fontSize = 14.sp) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = TextMuted) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = NavyPrimary,
                        unfocusedBorderColor = CardBorderBlue
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp)
                ) {
                    items(filtered) { district ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { onSelect(district) }
                                .padding(horizontal = 14.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "📍",
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = district.nameHi,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = TextPrimary
                                )
                                Text(
                                    text = district.nameEn,
                                    fontSize = 12.sp,
                                    color = TextMuted
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ConstituencyPickerDialog(
    district: District,
    constituencies: List<Constituency>,
    onDismiss: () -> Unit,
    onSelect: (Constituency) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val filtered = remember(searchQuery, constituencies) {
        if (searchQuery.isBlank()) {
            constituencies
        } else {
            constituencies.filter {
                it.nameHi.contains(searchQuery.trim(), ignoreCase = true) ||
                it.nameEn.contains(searchQuery.trim(), ignoreCase = true) ||
                it.number.toString().contains(searchQuery.trim())
            }
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            shape = RoundedCornerShape(20.dp),
            color = Color.White,
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "विधानसभा क्षेत्र चुनें",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = NavyPrimary
                        )
                        Text(
                            text = "जिला: ${district.nameHi}",
                            fontSize = 13.sp,
                            color = TextSecondary
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "बंद करें", tint = TextSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("विधानसभा खोजें...", fontSize = 14.sp) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = TextMuted) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = NavyPrimary,
                        unfocusedBorderColor = CardBorderBlue
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp)
                ) {
                    items(filtered) { const ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { onSelect(const) }
                                .padding(horizontal = 14.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(SoftBlueBg, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${const.number}",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = NavyPrimary
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = const.nameHi,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = TextPrimary
                                )
                                Text(
                                    text = const.nameEn,
                                    fontSize = 12.sp,
                                    color = TextMuted
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
