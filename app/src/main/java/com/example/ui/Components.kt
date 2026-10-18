package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.window.Dialog
import com.example.data.Candidate
import com.example.ui.theme.CardBorderBlue
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.SoftBlueBg
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun ConfirmVoteDialog(
    candidate: Candidate,
    isVoting: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { if (!isVoting) onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Color.White,
            tonalElevation = 6.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(SoftBlueBg, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = candidate.symbolEmoji, fontSize = 32.sp)
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "अपनी राय की पुष्टि करें",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrimary
                )

                Spacer(modifier = Modifier.height(10.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = candidate.nameHi,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = candidate.partyHi,
                            fontSize = 13.sp,
                            color = TextSecondary
                        )
                        Text(
                            text = "चुनाव चिह्न: ${candidate.symbolNameHi}",
                            fontSize = 12.sp,
                            color = TextMuted
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "क्या आप इस उम्मीदवार के पक्ष में अपनी राय दर्ज करना चाहते हैं?",
                    fontSize = 13.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                if (isVoting) {
                    CircularProgressIndicator(color = SuccessGreen)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("दर्ज किया जा रहा है...", fontSize = 13.sp, color = TextMuted)
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = onDismiss,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("रद्द करें", color = TextSecondary)
                        }

                        Button(
                            onClick = onConfirm,
                            modifier = Modifier
                                .weight(1.2f)
                                .testTag("confirm_vote_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("हाँ, दर्ज करें", fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GoogleSignInDialog(
    onSignInClick: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Color.White,
            tonalElevation = 6.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(SoftBlueBg, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🔐", fontSize = 28.sp)
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "सत्यापन आवश्यक है",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "फर्जी वोटिंग रोकने और निष्पक्ष जनमत सुनिश्चित करने के लिए Google खाते से एक बार प्रमाणीकरण आवश्यक है। एक ईमेल से केवल एक ही वोट दर्ज हो सकता है।",
                    fontSize = 13.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onSignInClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("google_signin_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary)
                ) {
                    Text(
                        text = "Google से आगे बढ़ें",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("बाद में", color = TextMuted)
                }
            }
        }
    }
}
