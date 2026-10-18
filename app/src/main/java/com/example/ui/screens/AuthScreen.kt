package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Candidate
import com.example.data.model.Constituency
import com.example.ui.components.ElectionSymbolIcon
import com.example.ui.theme.GreenSuccess
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted

@Composable
fun AuthScreen(
  candidate: Candidate?,
  constituency: Constituency,
  isLoading: Boolean,
  errorMessage: String?,
  voteSuccess: Boolean,
  alreadyVoted: Boolean,
  onSignInClick: () -> Unit,
  onViewResultsClick: () -> Unit,
  onBackToEvm: () -> Unit
) {
  BackHandler {
    onBackToEvm()
  }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("auth_screen"),
    topBar = {
      Surface(
        color = NavyPrimary,
        tonalElevation = 4.dp
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          IconButton(
            onClick = onBackToEvm,
            modifier = Modifier.testTag("auth_back_button")
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "वापस जाएँ",
              tint = Color.White
            )
          }
          Column {
            Text(
              text = "🔐 पहचान सत्यापन एवं लॉगिन",
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
            Text(
              text = "विधानसभा: ${constituency.constituencyNameHindi}",
              fontSize = 12.sp,
              color = Color(0xFFBAE6FD)
            )
          }
        }
      }
    }
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .padding(horizontal = 20.dp, vertical = 20.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      if (voteSuccess) {
        // Vote Recorded Success State
        SuccessVoteCard(
          candidate = candidate,
          constituency = constituency,
          onViewResultsClick = onViewResultsClick
        )
      } else if (alreadyVoted) {
        // Already Voted State
        AlreadyVotedCard(
          constituency = constituency,
          onViewResultsClick = onViewResultsClick
        )
      } else {
        // Pending Auth State
        PendingAuthCard(
          candidate = candidate,
          constituency = constituency,
          isLoading = isLoading,
          errorMessage = errorMessage,
          onSignInClick = onSignInClick,
          onViewResultsClick = onViewResultsClick
        )
      }
    }
  }
}

@Composable
private fun PendingAuthCard(
  candidate: Candidate?,
  constituency: Constituency,
  isLoading: Boolean,
  errorMessage: String?,
  onSignInClick: () -> Unit,
  onViewResultsClick: () -> Unit
) {
  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Box(
      modifier = Modifier
        .size(68.dp)
        .clip(CircleShape)
        .background(Color(0xFFE0F2FE)),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = Icons.Default.Security,
        contentDescription = null,
        tint = NavyPrimary,
        modifier = Modifier.size(36.dp)
      )
    }

    Spacer(modifier = Modifier.height(14.dp))

    Text(
      text = "सत्यापित नागरिक जनमत",
      fontSize = 20.sp,
      fontWeight = FontWeight.Bold,
      color = TextDark
    )

    Text(
      text = "सर्वे में निष्पक्षता बनाए रखने के लिए, प्रत्येक नागरिक केवल एक बार अपनी राय दे सकता है।",
      fontSize = 13.sp,
      color = TextMuted,
      textAlign = TextAlign.Center,
      modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
    )

    Spacer(modifier = Modifier.height(14.dp))

    // Preserved Candidate Selection Preview
    if (candidate != null) {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
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
            modifier = Modifier.size(50.dp)
          )
          Spacer(modifier = Modifier.width(12.dp))
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = "आपकी चुनी हुई पसंद:",
              fontSize = 11.sp,
              color = Color(0xFF64748B),
              fontWeight = FontWeight.Medium
            )
            Text(
              text = candidate.candidateName,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF0F172A)
            )
            Text(
              text = candidate.partyName,
              fontSize = 12.sp,
              color = Color(0xFF475569)
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    if (errorMessage != null) {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF2F2)),
        border = BorderStroke(1.dp, Color(0xFFFCA5A5))
      ) {
        Row(
          modifier = Modifier.padding(12.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            tint = Color(0xFFDC2626)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = errorMessage,
            color = Color(0xFF991B1B),
            fontSize = 13.sp
          )
        }
      }
    }

    // Google Sign-In Button
    Button(
      onClick = onSignInClick,
      enabled = !isLoading,
      modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .testTag("google_signin_button"),
      colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary),
      shape = RoundedCornerShape(14.dp)
    ) {
      if (isLoading) {
        CircularProgressIndicator(
          color = Color.White,
          modifier = Modifier.size(24.dp),
          strokeWidth = 2.5.dp
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "प्रमाणीकरण हो रहा है...", fontSize = 16.sp, fontWeight = FontWeight.Bold)
      } else {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(26.dp)
              .clip(CircleShape)
              .background(Color.White),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "G",
              fontWeight = FontWeight.Black,
              color = Color(0xFF4285F4),
              fontSize = 16.sp
            )
          }
          Spacer(modifier = Modifier.width(12.dp))
          Column(horizontalAlignment = Alignment.Start) {
            Text(
              text = "Google / ईमेल से सुरक्षित लॉगिन",
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
            Text(
              text = "ईमेल पहचान स्वतः 100% सत्यापित होती है",
              fontSize = 11.sp,
              color = Color(0xFFBAE6FD)
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Direct result view button
    OutlinedButton(
      onClick = onViewResultsClick,
      modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .testTag("view_results_direct_button"),
      shape = RoundedCornerShape(12.dp)
    ) {
      Text(
        text = "📊 केवल परिणाम देखें",
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        color = NavyPrimary
      )
    }

    Spacer(modifier = Modifier.height(18.dp))

    Text(
      text = "🔒 आपकी ईमेल पहचान और वोट पूरी तरह गोपनीय व सुरक्षित रहते हैं।",
      fontSize = 12.sp,
      color = Color(0xFF64748B),
      textAlign = TextAlign.Center
    )
  }
}

@Composable
private fun SuccessVoteCard(
  candidate: Candidate?,
  constituency: Constituency,
  onViewResultsClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("vote_success_card"),
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Box(
        modifier = Modifier
          .size(72.dp)
          .clip(CircleShape)
          .background(Color(0xFFDCFCE7)),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.CheckCircle,
          contentDescription = null,
          tint = GreenSuccess,
          modifier = Modifier.size(46.dp)
        )
      }

      Spacer(modifier = Modifier.height(16.dp))

      Text(
        text = "✅ आपकी राय सफलतापूर्वक दर्ज हो गई!",
        fontSize = 20.sp,
        fontWeight = FontWeight.Black,
        color = Color(0xFF065F46),
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = "विधानसभा ${constituency.constituencyNameHindi} (क्र. ${constituency.officialSerialNumber}) के जनमत सर्वेक्षण में भाग लेने के लिए धन्यवाद।",
        fontSize = 14.sp,
        color = Color(0xFF334155),
        textAlign = TextAlign.Center
      )

      if (candidate != null) {
        Spacer(modifier = Modifier.height(16.dp))
        Card(
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FDF4))
        ) {
          Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            ElectionSymbolIcon(
              symbolKey = candidate.symbolKey,
              modifier = Modifier.size(44.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = "दर्ज राय: ${candidate.candidateName}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF065F46)
              )
              Text(
                text = candidate.partyName,
                fontSize = 12.sp,
                color = Color(0xFF047857)
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(24.dp))

      // Button to view results
      Button(
        onClick = onViewResultsClick,
        modifier = Modifier
          .fillMaxWidth()
          .height(56.dp)
          .testTag("success_view_results_button"),
        colors = ButtonDefaults.buttonColors(containerColor = GreenSuccess),
        shape = RoundedCornerShape(14.dp)
      ) {
        Text(
          text = "📊 जनमत परिणाम देखें ➜",
          fontSize = 17.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
      }
    }
  }
}

@Composable
private fun AlreadyVotedCard(
  constituency: Constituency,
  onViewResultsClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("already_voted_card"),
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Box(
        modifier = Modifier
          .size(72.dp)
          .clip(CircleShape)
          .background(Color(0xFFFEF3C7)),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.Warning,
          contentDescription = null,
          tint = Color(0xFFD97706),
          modifier = Modifier.size(42.dp)
        )
      }

      Spacer(modifier = Modifier.height(16.dp))

      Text(
        text = "आपकी राय पहले ही दर्ज हो चुकी है",
        fontSize = 20.sp,
        fontWeight = FontWeight.Black,
        color = Color(0xFF92400E),
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = "विधानसभा ${constituency.constituencyNameHindi} के लिए आपकी राय पहले से दर्ज है। निष्पक्षता बनाए रखने के लिए प्रति नागरिक एक राय मान्य है।",
        fontSize = 14.sp,
        color = Color(0xFF451A03),
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(24.dp))

      Button(
        onClick = onViewResultsClick,
        modifier = Modifier
          .fillMaxWidth()
          .height(56.dp)
          .testTag("already_voted_view_results_button"),
        colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary),
        shape = RoundedCornerShape(14.dp)
      ) {
        Text(
          text = "📊 परिणाम देखें ➜",
          fontSize = 17.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
      }
    }
  }
}

private fun BorderStroke(width: androidx.compose.ui.unit.Dp, color: Color) =
  androidx.compose.foundation.BorderStroke(width, color)
