package com.example.ui

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Candidate
import com.example.data.Constituency
import com.example.data.District
import com.example.ui.theme.CardBorderBlue
import com.example.ui.theme.EvmBlueButton
import com.example.ui.theme.EvmCardBg
import com.example.ui.theme.EvmGrayBorder
import com.example.ui.theme.EvmGreenLed
import com.example.ui.theme.EvmRedLed
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.SoftBlueBg
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvmScreen(
    district: District,
    constituency: Constituency,
    candidates: List<Candidate>,
    hasAlreadyVoted: Boolean,
    onCandidateClick: (Candidate) -> Unit,
    onViewResults: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "जनमत सर्वेक्षण मतपत्र (EVM)",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "${constituency.nameHi} (${constituency.number}) • ${district.nameHi}",
                            fontSize = 12.sp,
                            color = Color.White.copy(alpha = 0.85f)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "वापस",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onViewResults) {
                        Icon(
                            imageVector = Icons.Default.BarChart,
                            contentDescription = "परिणाम",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF1F5F9))
        ) {
            // Already voted banner
            if (hasAlreadyVoted) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF3C7)),
                    shape = RoundedCornerShape(12.dp),
                    border = CardDefaults.outlinedCardBorder().copy(width = 1.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = "Info",
                            tint = Color(0xFFD97706),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "आप अपना मत दर्ज कर चुके हैं",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF92400E)
                            )
                            Text(
                                text = "इस विधानसभा के वास्तविक जनमत परिणाम देखने के लिए नीचे बटन दबाएं।",
                                fontSize = 12.sp,
                                color = Color(0xFF78350F)
                            )
                        }
                        Button(
                            onClick = onViewResults,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD97706)),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("परिणाम", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Balloting Unit Header Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE2E8F0))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .background(EvmGreenLed, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "तैयार (READY)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessGreen
                        )
                    }

                    Text(
                        text = "बटन दबाकर अपनी राय चुनें",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSecondary
                    )
                }
            }

            // Candidates EVM List
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(candidates) { candidate ->
                    EvmCandidateRow(
                        candidate = candidate,
                        onVoteClick = { onCandidateClick(candidate) }
                    )
                }
            }

            // Bottom Disclaimer
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "यह एक स्वतंत्र जनमत सर्वेक्षण है, आधिकारिक चुनाव आयोग मतदान नहीं।",
                    fontSize = 11.sp,
                    color = TextMuted,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun EvmCandidateRow(
    candidate: Candidate,
    onVoteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, EvmGrayBorder, RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Serial Number
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFFE2E8F0), RoundedCornerShape(6.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${candidate.serialNumber}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = TextPrimary
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            // Candidate & Party Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = candidate.nameHi,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = candidate.partyHi,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }

            Spacer(modifier = Modifier.width(6.dp))

            // Symbol
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(54.dp)
            ) {
                Text(
                    text = candidate.symbolEmoji,
                    fontSize = 24.sp
                )
                Text(
                    text = candidate.symbolNameHi,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextMuted
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // LED & EVM Blue Vote Button
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Red LED Indicator
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(EvmRedLed, CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Iconic Blue EVM Button
                Box(
                    modifier = Modifier
                        .size(width = 64.dp, height = 44.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(EvmBlueButton)
                        .clickable(onClick = onVoteClick)
                        .testTag("vote_button_${candidate.serialNumber}"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "वोट",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
