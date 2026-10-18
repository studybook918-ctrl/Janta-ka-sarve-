package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Candidate
import com.example.data.model.Constituency
import com.example.data.model.District
import com.example.data.model.Survey
import com.example.ui.components.ElectionSymbolIcon
import com.example.ui.theme.EvmBlue
import com.example.ui.theme.GreenSuccess
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted
import java.util.Locale

@Composable
fun ResultsScreen(
  district: District,
  constituency: Constituency,
  survey: Survey,
  candidates: List<Candidate>,
  onRefresh: () -> Unit,
  onBackToHome: () -> Unit
) {
  BackHandler {
    onBackToHome()
  }

  val totalVotes = survey.totalResponses
  val candidateCounts = survey.candidateCounts

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("results_screen"),
    topBar = {
      Surface(
        color = NavyPrimary,
        tonalElevation = 6.dp
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          IconButton(
            onClick = onBackToHome,
            modifier = Modifier.testTag("results_back_button")
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "वापस जाएँ",
              tint = Color.White
            )
          }
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = "📊 जनमत सर्वेक्षण परिणाम",
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
            Text(
              text = "विधानसभा: ${constituency.constituencyNameHindi} (क्र. ${constituency.officialSerialNumber}) | ${district.districtNameHindi}",
              fontSize = 12.sp,
              color = Color(0xFFBAE6FD),
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )
          }
          IconButton(
            onClick = onRefresh,
            modifier = Modifier.testTag("results_refresh_button")
          ) {
            Icon(
              imageVector = Icons.Default.Refresh,
              contentDescription = "ताज़ा करें",
              tint = Color.White
            )
          }
        }
      }
    }
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
      // 1. Summary Card
      item {
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp),
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = Color.White),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column {
                Text(
                  text = "कुल वैध राय दर्ज",
                  fontSize = 13.sp,
                  color = TextMuted,
                  fontWeight = FontWeight.Medium
                )
                Text(
                  text = if (totalVotes > 0) "$totalVotes वोट" else "0 वोट",
                  fontSize = 24.sp,
                  fontWeight = FontWeight.Black,
                  color = NavyPrimary
                )
              }
              Box(
                modifier = Modifier
                  .size(48.dp)
                  .clip(CircleShape)
                  .background(Color(0xFFE0F2FE)),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Default.Assessment,
                  contentDescription = null,
                  tint = EvmBlue,
                  modifier = Modifier.size(28.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFE2E8F0))
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = "स्थिति: सक्रिय सर्वे",
                fontSize = 12.sp,
                color = GreenSuccess,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "अवधि: ${survey.startAt} से ${survey.endAt}",
                fontSize = 11.sp,
                color = TextMuted
              )
            }
          }
        }
      }

      // 2. Candidate Vote Cards
      if (totalVotes == 0L) {
        item {
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF3C7)),
            shape = RoundedCornerShape(14.dp)
          ) {
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = Color(0xFFB45309),
                modifier = Modifier.size(36.dp)
              )
              Spacer(modifier = Modifier.height(8.dp))
              Text(
                text = "अभी परिणाम उपलब्ध नहीं हैं।",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF78350F)
              )
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = "इस विधानसभा में अभी तक कोई राय दर्ज नहीं हुई है। आप पहले नागरिक बनकर अपनी राय दर्ज कर सकते हैं!",
                fontSize = 13.sp,
                color = Color(0xFF92400E),
                textAlign = TextAlign.Center
              )
            }
          }
        }
      } else {
        // Sort candidates by vote count descending
        val sortedCandidates = candidates.sortedByDescending {
          candidateCounts[it.candidateId] ?: 0L
        }

        items(sortedCandidates, key = { it.candidateId }) { candidate ->
          val count = candidateCounts[candidate.candidateId] ?: 0L
          val percentage = if (totalVotes > 0) (count.toFloat() / totalVotes.toFloat()) * 100f else 0f
          val isLeader = count > 0 && count == (candidateCounts.values.maxOrNull() ?: 0L)

          CandidateResultCard(
            candidate = candidate,
            count = count,
            percentage = percentage,
            isLeader = isLeader
          )
        }
      }

      // 3. Methodology & Disclaimer
      item {
        Spacer(modifier = Modifier.height(16.dp))
        Card(
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9))
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            Text(
              text = "📌 सर्वे की पद्धति और सीमाएं:",
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF1E293B)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = "• ${survey.methodology}\n• प्रति नागरिक केवल एक वोट की अनुमति है।\n• परिणाम केवल ऑनलाइन सर्वे में भाग लेने वाले नागरिकों की राय दर्शाते हैं।",
              fontSize = 12.sp,
              color = Color(0xFF475569),
              lineHeight = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = "⚠️ अस्वीकरण (Disclaimer): सर्वे में दर्ज प्रतिक्रियाओं का प्रतिशत पूरे विधानसभा क्षेत्र के सभी मतदाताओं का निश्चित समर्थन नहीं माना जा सकता। परिणामों को आधिकारिक चुनाव परिणाम या निश्चित चुनावी भविष्यवाणी के रूप में प्रस्तुत न करें।",
              fontSize = 11.sp,
              color = Color(0xFF64748B),
              lineHeight = 15.sp,
              fontWeight = FontWeight.Medium
            )
          }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Button(
          onClick = onBackToHome,
          modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .testTag("return_home_button"),
          colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary),
          shape = RoundedCornerShape(12.dp)
        ) {
          Text(
            text = "🏠 होम स्क्रीन पर लौटें",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        }
        Spacer(modifier = Modifier.height(24.dp))
      }
    }
  }
}

@Composable
private fun CandidateResultCard(
  candidate: Candidate,
  count: Long,
  percentage: Float,
  isLeader: Boolean
) {
  val animatedProgress by animateFloatAsState(
    targetValue = percentage / 100f,
    animationSpec = tween(durationMillis = 600),
    label = "result_progress"
  )

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 6.dp)
      .testTag("result_card_${candidate.candidateId}"),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(
      containerColor = if (isLeader) Color(0xFFF0FDF4) else Color.White
    ),
    border = if (isLeader) androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFF86EFAC))
             else CardDefaults.outlinedCardBorder()
  ) {
    Column(modifier = Modifier.padding(14.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        ElectionSymbolIcon(
          symbolKey = candidate.symbolKey,
          modifier = Modifier.size(46.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = candidate.candidateName,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold,
              color = TextDark,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis,
              modifier = Modifier.weight(1f, fill = false)
            )
            if (isLeader) {
              Spacer(modifier = Modifier.width(6.dp))
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(4.dp))
                  .background(Color(0xFFDCFCE7))
                  .padding(horizontal = 6.dp, vertical = 2.dp)
              ) {
                Text(
                  text = "आगे 🏆",
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF166534)
                )
              }
            }
          }
          Text(
            text = candidate.partyName,
            fontSize = 12.sp,
            color = TextMuted
          )
        }
        Column(horizontalAlignment = Alignment.End) {
          Text(
            text = String.format(Locale.US, "%.1f%%", percentage),
            fontSize = 17.sp,
            fontWeight = FontWeight.Black,
            color = if (isLeader) GreenSuccess else NavyPrimary
          )
          Text(
            text = "$count राय",
            fontSize = 12.sp,
            color = TextMuted,
            fontWeight = FontWeight.Medium
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Percentage Progress Bar
      LinearProgressIndicator(
        progress = { animatedProgress },
        modifier = Modifier
          .fillMaxWidth()
          .height(10.dp)
          .clip(RoundedCornerShape(5.dp)),
        color = if (isLeader) GreenSuccess else EvmBlue,
        trackColor = Color(0xFFE2E8F0)
      )
    }
  }
}
