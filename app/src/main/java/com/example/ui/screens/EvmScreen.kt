package com.example.ui.screens

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Candidate
import com.example.data.model.Constituency
import com.example.data.model.District
import com.example.ui.components.ElectionSymbolIcon
import com.example.ui.theme.EvmBlue
import com.example.ui.theme.EvmBlueDark
import com.example.ui.theme.EvmPaper
import com.example.ui.theme.EvmRedLed
import com.example.ui.theme.EvmRedLedOff
import com.example.ui.theme.EvmUnitCase
import com.example.ui.theme.GreenSuccess
import com.example.ui.theme.NavyPrimary

@Composable
fun EvmScreen(
  district: District,
  constituency: Constituency,
  candidates: List<Candidate>,
  onBackToHome: () -> Unit,
  onProceedWithCandidate: (Candidate) -> Unit
) {
  val context = LocalContext.current
  var selectedCandidate by remember { mutableStateOf<Candidate?>(null) }
  var showConfirmationDialog by remember { mutableStateOf(false) }

  BackHandler {
    if (showConfirmationDialog) {
      showConfirmationDialog = false
    } else {
      onBackToHome()
    }
  }

  val toneGenerator = remember {
    try {
      ToneGenerator(AudioManager.STREAM_NOTIFICATION, 70)
    } catch (_: Exception) {
      null
    }
  }

  androidx.compose.runtime.DisposableEffect(Unit) {
    onDispose {
      try {
        toneGenerator?.release()
      } catch (_: Exception) {}
    }
  }

  fun triggerEvmFeedback() {
    try {
      toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 100)
    } catch (_: Exception) {}
    try {
      val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator?.vibrate(VibrationEffect.createOneShot(80, VibrationEffect.DEFAULT_AMPLITUDE))
      } else {
        @Suppress("DEPRECATION")
        vibrator?.vibrate(80)
      }
    } catch (_: Exception) {}
  }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("evm_screen"),
    topBar = {
      Surface(
        color = NavyPrimary,
        tonalElevation = 6.dp
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
          ) {
            IconButton(
              onClick = onBackToHome,
              modifier = Modifier.testTag("evm_back_button")
            ) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "वापस जाएँ",
                tint = Color.White
              )
            }
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = "🗳️ इलेक्ट्रॉनिक जनमत मतपत्र (EVM)",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
              )
              Text(
                text = "विधानसभा: ${constituency.constituencyNameHindi} (क्र.सं. ${constituency.officialSerialNumber}) | जिला: ${district.districtNameHindi}",
                color = Color(0xFFBAE6FD),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
              )
            }
          }
        }
      }
    },
    bottomBar = {
      // Clear Statutory Disclaimer
      Surface(
        color = Color(0xFFFEF3C7),
        border = CardDefaults.outlinedCardBorder()
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = Color(0xFFB45309),
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "यह जनता की राय जानने के लिए जनमत सर्वेक्षण है, सरकारी मतदान नहीं।",
            fontSize = 12.sp,
            color = Color(0xFF78350F),
            fontWeight = FontWeight.Medium
          )
        }
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(EvmUnitCase)
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(8.dp)
      ) {
        // Machine Ballot Frame
        Box(
          modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(2.dp, Color(0xFF94A3B8), RoundedCornerShape(12.dp))
        ) {
          Column(modifier = Modifier.fillMaxSize()) {
            // Ballot Header Row
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0F172A))
                .padding(horizontal = 8.dp, vertical = 8.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "क्र.",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(36.dp),
                textAlign = TextAlign.Center
              )
              Text(
                text = "उम्मीदवार व दल का नाम",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start
              )
              Text(
                text = "चिह्न",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(60.dp),
                textAlign = TextAlign.Center
              )
              Text(
                text = "नीला बटन",
                color = Color(0xFF93C5FD),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(96.dp),
                textAlign = TextAlign.Center
              )
            }

            // Candidates list (EVM ballot rows)
            LazyColumn(
              modifier = Modifier
                .fillMaxSize()
                .background(EvmPaper)
            ) {
              itemsIndexed(candidates) { index, candidate ->
                val isSelected = selectedCandidate?.candidateId == candidate.candidateId
                EvmCandidateRow(
                  serialNumber = index + 1,
                  candidate = candidate,
                  isSelected = isSelected,
                  onVoteClick = {
                    triggerEvmFeedback()
                    selectedCandidate = candidate
                    showConfirmationDialog = true
                  }
                )
              }
            }
          }
        }
      }

      // Confirmation Bottom Sheet / Dialog
      AnimatedVisibility(
        visible = showConfirmationDialog && selectedCandidate != null,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
        modifier = Modifier.align(Alignment.BottomCenter)
      ) {
        selectedCandidate?.let { cand ->
          ConfirmationSheet(
            candidate = cand,
            constituency = constituency,
            onDismiss = { showConfirmationDialog = false },
            onConfirm = {
              showConfirmationDialog = false
              onProceedWithCandidate(cand)
            }
          )
        }
      }
    }
  }
}

@Composable
private fun EvmCandidateRow(
  serialNumber: Int,
  candidate: Candidate,
  isSelected: Boolean,
  onVoteClick: () -> Unit
) {
  val rowBgColor by animateColorAsState(
    targetValue = if (isSelected) Color(0xFFE0F2FE) else EvmPaper,
    animationSpec = tween(200),
    label = "row_bg"
  )

  val ledColor by animateColorAsState(
    targetValue = if (isSelected) EvmRedLed else EvmRedLedOff,
    animationSpec = tween(200),
    label = "led_color"
  )

  Column {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .background(rowBgColor)
        .clickable { onVoteClick() }
        .padding(horizontal = 6.dp, vertical = 6.dp)
        .testTag("candidate_row_${candidate.candidateId}"),
      verticalAlignment = Alignment.CenterVertically
    ) {
      // 1. Serial Number Box
      Box(
        modifier = Modifier
          .width(36.dp)
          .height(44.dp)
          .clip(RoundedCornerShape(4.dp))
          .background(Color.White)
          .border(1.5.dp, Color(0xFF334155), RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = "$serialNumber",
          fontSize = 18.sp,
          fontWeight = FontWeight.Black,
          color = Color(0xFF0F172A)
        )
      }

      Spacer(modifier = Modifier.width(8.dp))

      // 2. Candidate Name & Party Name
      Column(
        modifier = Modifier
          .weight(1f)
          .padding(end = 4.dp)
      ) {
        Text(
          text = candidate.candidateName,
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = if (isSelected) Color(0xFF1E3A8A) else Color(0xFF0F172A),
          maxLines = 2,
          overflow = TextOverflow.Ellipsis,
          lineHeight = 18.sp
        )
        Text(
          text = candidate.partyName,
          fontSize = 12.sp,
          fontWeight = FontWeight.Medium,
          color = Color(0xFF475569),
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
      }

      // 3. Election Symbol
      ElectionSymbolIcon(
        symbolKey = candidate.symbolKey,
        modifier = Modifier.width(54.dp)
      )

      Spacer(modifier = Modifier.width(8.dp))

      // 4. Red LED Indicator
      Box(
        modifier = Modifier
          .size(16.dp)
          .clip(CircleShape)
          .background(ledColor)
          .border(
            width = 1.dp,
            color = if (isSelected) Color(0xFFFCA5A5) else Color(0xFF1E293B),
            shape = CircleShape
          )
          .shadow(
            elevation = if (isSelected) 8.dp else 0.dp,
            shape = CircleShape,
            ambientColor = EvmRedLed,
            spotColor = EvmRedLed
          )
          .testTag("led_indicator_${candidate.candidateId}")
      )

      Spacer(modifier = Modifier.width(10.dp))

      // 5. Blue EVM Button
      EvmBlueButton(
        onClick = onVoteClick,
        testTag = "vote_button_${candidate.candidateId}"
      )
    }

    // Ballot Divider line
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(Color(0xFFCBD5E1))
    )
  }
}

@Composable
private fun EvmBlueButton(
  onClick: () -> Unit,
  testTag: String
) {
  val interactionSource = remember { MutableInteractionSource() }
  val isPressed by interactionSource.collectIsPressedAsState()

  Box(
    modifier = Modifier
      .width(68.dp)
      .height(48.dp)
      .clip(RoundedCornerShape(8.dp))
      .background(
        Brush.verticalGradient(
          colors = if (isPressed) {
            listOf(Color(0xFF1E40AF), EvmBlueDark)
          } else {
            listOf(Color(0xFF3B82F6), EvmBlueDark)
          }
        )
      )
      .border(
        width = 2.dp,
        color = if (isPressed) Color(0xFF1E3A8A) else Color(0xFF1D4ED8),
        shape = RoundedCornerShape(8.dp)
      )
      .shadow(elevation = if (isPressed) 1.dp else 4.dp, shape = RoundedCornerShape(8.dp))
      .clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
      )
      .testTag(testTag),
    contentAlignment = Alignment.Center
  ) {
    // Arrow pointing to candidate
    Text(
      text = "◀ बटन",
      color = Color.White,
      fontSize = 12.sp,
      fontWeight = FontWeight.Black
    )
  }
}

@Composable
private fun ConfirmationSheet(
  candidate: Candidate,
  constituency: Constituency,
  onDismiss: () -> Unit,
  onConfirm: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp)
      .testTag("selection_confirmation_card"),
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
      ) {
        Icon(
          imageVector = Icons.Default.CheckCircle,
          contentDescription = null,
          tint = GreenSuccess,
          modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "आपने इस उम्मीदवार को चुना है",
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF0F172A)
        )
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Candidate Summary Card
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FDF4)),
        border = CardDefaults.outlinedCardBorder()
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          ElectionSymbolIcon(
            symbolKey = candidate.symbolKey,
            modifier = Modifier.size(60.dp)
          )
          Spacer(modifier = Modifier.width(14.dp))
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = candidate.candidateName,
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF065F46)
            )
            Text(
              text = candidate.partyName,
              fontSize = 13.sp,
              fontWeight = FontWeight.Medium,
              color = Color(0xFF047857)
            )
            Text(
              text = "चुनाव चिह्न: ${candidate.electionSymbol}",
              fontSize = 12.sp,
              color = Color(0xFF065F46)
            )
            Text(
              text = "विधानसभा: ${constituency.constituencyNameHindi}",
              fontSize = 12.sp,
              color = Color(0xFF047857),
              fontWeight = FontWeight.SemiBold
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(18.dp))

      // Action Buttons: 🔙 चयन बदलें | 🟢 आगे बढ़ें
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        OutlinedButton(
          onClick = onDismiss,
          modifier = Modifier
            .weight(1f)
            .height(52.dp)
            .testTag("change_selection_button"),
          shape = RoundedCornerShape(12.dp)
        ) {
          Text(
            text = "🔙 चयन बदलें",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF334155)
          )
        }

        Button(
          onClick = onConfirm,
          modifier = Modifier
            .weight(1f)
            .height(52.dp)
            .testTag("proceed_confirm_button"),
          colors = ButtonDefaults.buttonColors(containerColor = GreenSuccess),
          shape = RoundedCornerShape(12.dp)
        ) {
          Text(
            text = "आगे बढ़ें ➜",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        }
      }
    }
  }
}
