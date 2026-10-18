package com.example.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

data class District(
  val districtId: String = "",
  val districtNameHindi: String = "",
  val districtNameEnglish: String = "",
  val isActive: Boolean = true
)

data class Constituency(
  val constituencyId: String = "",
  val constituencyNameHindi: String = "",
  val constituencyNameEnglish: String = "",
  val districtId: String = "",
  val officialSerialNumber: Int = 0,
  val isActive: Boolean = true
)

data class Candidate(
  val candidateId: String = "",
  val surveyId: String = "",
  val constituencyId: String = "",
  val candidateName: String = "",
  val partyName: String = "",
  val electionSymbol: String = "",
  val symbolKey: String = "lotus",
  val electionYear: String = "2027",
  val candidateStatus: String = "सत्यापित",
  val verificationStatus: String = "सत्यापित"
)

data class Survey(
  val surveyId: String = "",
  val constituencyId: String = "",
  val title: String = "",
  val status: String = "active",
  val methodology: String = "ऑनलाइन जनमत सर्वेक्षण (एक नागरिक, एक राय)",
  val totalResponses: Long = 0L,
  val candidateCounts: Map<String, Long> = emptyMap(),
  val startAt: String = "2026-10-01",
  val endAt: String = "2027-03-31"
)

data class SurveyResponse(
  @DocumentId val responseId: String = "",
  val surveyId: String = "",
  val constituencyId: String = "",
  val candidateId: String = "",
  val userId: String = "",
  @ServerTimestamp val createdAt: Timestamp? = null
)
