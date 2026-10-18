package com.example.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Constituency
import com.example.data.model.District
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  districts: List<District>,
  selectedDistrict: District?,
  selectedConstituency: Constituency?,
  availableConstituencies: List<Constituency>,
  onDistrictSelected: (District) -> Unit,
  onConstituencySelected: (Constituency) -> Unit,
  onProceedToEvm: () -> Unit
) {
  var showDistrictSheet by remember { mutableStateOf(false) }
  var showConstituencySheet by remember { mutableStateOf(false) }
  val districtSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  val constituencySheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()

  val isProceedActive = selectedDistrict != null && selectedConstituency != null
  val scrollState = rememberScrollState()

  // Background matching the screenshot (very light clean gray-white #F8FAFC)
  val pageBgColor = Color(0xFFF8FAFC)

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("home_screen"),
    containerColor = pageBgColor,
    snackbarHost = { SnackbarHost(snackbarHostState) }
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .verticalScroll(scrollState)
        .padding(horizontal = 20.dp, vertical = 16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // 1. Top Header with Flag and Bell (Matching screenshot header)
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 8.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Spacer(modifier = Modifier.width(36.dp)) // balance layout

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Text(
            text = "🇮🇳",
            fontSize = 24.sp
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "जनता की राय (Neta Survey)",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFC02626) // Deep crimson red matching screenshot
          )
        }

        Icon(
          imageVector = Icons.Default.Notifications,
          contentDescription = "सूचनाएं",
          tint = Color(0xFFC02626),
          modifier = Modifier.size(26.dp)
        )
      }

      // Tagline in quotation marks (Matching screenshot tagline)
      Text(
        text = "“उत्तर प्रदेश की सभी 403 विधानसभाओं का निष्पक्ष जनमत सर्वेक्षण”",
        fontSize = 12.sp,
        color = Color(0xFF4B5563),
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(bottom = 20.dp)
      )

      // 2. Welcome Greeting Card (Matching "नमस्ते! 🙏" card from screenshot)
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 14.dp)
          .testTag("welcome_card"),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = CardDefaults.outlinedCardBorder()
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 22.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "नमस्ते! 🙏",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827)
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "आप इस ऐप में अपना जिला और विधानसभा चुनें:",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFFEA580C), // Vivid orange matching screenshot
            textAlign = TextAlign.Center
          )
        }
      }

      // 3. District Selection Card (Matching "मैं काम करता हूँ" card from screenshot)
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 14.dp)
          .clip(RoundedCornerShape(22.dp))
          .clickable { showDistrictSheet = true }
          .testTag("district_selector_card"),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = CardDefaults.outlinedCardBorder()
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Orange ring circular avatar/emblem (matching screenshot)
          Box(
            modifier = Modifier
              .size(66.dp)
              .clip(CircleShape)
              .background(Color(0xFFFFF7ED))
              .border(2.5.dp, Color(0xFFEA580C), CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Canvas(modifier = Modifier.size(46.dp)) {
              drawOrangeCitizenAvatar()
            }
          }

          Spacer(modifier = Modifier.width(16.dp))

          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = if (selectedDistrict != null) "जिला: ${selectedDistrict.districtNameHindi}"
                     else "अपना जिला चुनें 📍",
              fontSize = 17.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFFDC2626) // Red-orange heading
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = if (selectedDistrict != null)
                "चयनित: ${selectedDistrict.districtNameEnglish} • बदलने के लिए टैप करें"
              else
                "उत्तर प्रदेश के 75 जिलों में से अपना जिला चुनें",
              fontSize = 12.sp,
              color = Color(0xFF4B5563),
              lineHeight = 16.sp
            )
          }
        }
      }

      // 4. Constituency Selection Card (Matching "मुझे काम करवाना है" card from screenshot)
      val isConstituencyEnabled = selectedDistrict != null
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 14.dp)
          .clip(RoundedCornerShape(22.dp))
          .clickable {
            if (isConstituencyEnabled) {
              showConstituencySheet = true
            } else {
              scope.launch {
                snackbarHostState.showSnackbar("कृपया पहले अपना जिला चुनें।")
              }
            }
          }
          .testTag("constituency_selector_card"),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (!isConstituencyEnabled) Color(0xFFF9FAFB) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = CardDefaults.outlinedCardBorder()
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Blue ring circular magnifying glass icon (matching screenshot)
          Box(
            modifier = Modifier
              .size(66.dp)
              .clip(CircleShape)
              .background(if (isConstituencyEnabled) Color(0xFFEFF6FF) else Color(0xFFF3F4F6))
              .border(
                2.5.dp,
                if (isConstituencyEnabled) Color(0xFF2563EB) else Color(0xFF94A3B8),
                CircleShape
              ),
            contentAlignment = Alignment.Center
          ) {
            Canvas(modifier = Modifier.size(44.dp)) {
              drawBlueMagnifier(isConstituencyEnabled)
            }
          }

          Spacer(modifier = Modifier.width(16.dp))

          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = if (selectedConstituency != null)
                "विधानसभा: ${selectedConstituency.constituencyNameHindi}"
              else if (isConstituencyEnabled)
                "विधानसभा क्षेत्र चुनें 🗺️"
              else
                "विधानसभा क्षेत्र (पहले जिला चुनें)",
              fontSize = 17.sp,
              fontWeight = FontWeight.Bold,
              color = if (isConstituencyEnabled) Color(0xFF1D4ED8) else Color(0xFF64748B) // Blue heading
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = if (selectedConstituency != null)
                "सीट क्र.सं. ${selectedConstituency.officialSerialNumber} • ${selectedConstituency.constituencyNameEnglish}"
              else if (isConstituencyEnabled)
                "${selectedDistrict?.districtNameHindi} की सभी सीटें खोजें व चुनें"
              else
                "जिला चुनने के बाद यह विकल्प सक्रिय होगा",
              fontSize = 12.sp,
              color = Color(0xFF4B5563),
              lineHeight = 16.sp
            )
          }
        }
      }

      // 5. Action Card (Matching the "खाता व लॉगिन" style card from screenshot)
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 16.dp)
          .testTag("action_card"),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = CardDefaults.outlinedCardBorder()
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
        ) {
          Text(
            text = "जनमत सर्वे विकल्प (Survey Action):",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937),
            modifier = Modifier.padding(bottom = 12.dp)
          )

          Button(
            onClick = {
              if (isProceedActive) {
                onProceedToEvm()
              } else {
                scope.launch {
                  if (selectedDistrict == null) {
                    snackbarHostState.showSnackbar("कृपया पहले अपना जिला चुनें।")
                  } else if (selectedConstituency == null) {
                    snackbarHostState.showSnackbar("कृपया अपनी विधानसभा चुनें।")
                  }
                }
              }
            },
            enabled = isProceedActive,
            modifier = Modifier
              .fillMaxWidth()
              .height(52.dp)
              .testTag("proceed_to_evm_button"),
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFF10B981), // Fresh green action
              disabledContainerColor = Color(0xFFE2E8F0)
            ),
            shape = RoundedCornerShape(12.dp)
          ) {
            Text(
              text = "🟢 आगे बढ़ें ➜ (EVM मतपत्र खोलें)",
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold,
              color = if (isProceedActive) Color.White else Color(0xFF94A3B8)
            )
          }
        }
      }

      // 6. Bottom Admin / Guidelines Footer (Matching the subtle bottom link from screenshot)
      Row(
        modifier = Modifier
          .padding(top = 4.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
      ) {
        Icon(
          imageVector = Icons.Default.Security,
          contentDescription = null,
          tint = Color(0xFF4B5563),
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "प्रशासन / निष्पक्ष जनमत सर्वेक्षण दिशानिर्देश",
          fontSize = 13.sp,
          color = Color(0xFF374151),
          fontWeight = FontWeight.Medium
        )
      }
    }
  }

  // District Search & Select Sheet
  if (showDistrictSheet) {
    ModalBottomSheet(
      onDismissRequest = { showDistrictSheet = false },
      sheetState = districtSheetState
    ) {
      DistrictSearchList(
        districts = districts,
        selectedDistrict = selectedDistrict,
        onSelect = { district ->
          onDistrictSelected(district)
          showDistrictSheet = false
        },
        onClose = { showDistrictSheet = false }
      )
    }
  }

  // Constituency Search & Select Sheet
  if (showConstituencySheet && selectedDistrict != null) {
    ModalBottomSheet(
      onDismissRequest = { showConstituencySheet = false },
      sheetState = constituencySheetState
    ) {
      ConstituencySearchList(
        district = selectedDistrict,
        constituencies = availableConstituencies,
        selectedConstituency = selectedConstituency,
        onSelect = { constituency ->
          onConstituencySelected(constituency)
          showConstituencySheet = false
        },
        onClose = { showConstituencySheet = false }
      )
    }
  }
}

// Drawing the cute citizen/worker avatar inside the orange ring (matching screenshot Card 1)
private fun DrawScope.drawOrangeCitizenAvatar() {
  val center = Offset(size.width / 2f, size.height * 0.52f)
  val saffron = Color(0xFFF59E0B)
  val darkSaffron = Color(0xFFD97706)
  val skin = Color(0xFFFCD34D)
  val blueShirt = Color(0xFF3B82F6)

  // Shoulders / shirt
  val shoulders = Path().apply {
    moveTo(center.x - 18f, size.height * 0.95f)
    cubicTo(center.x - 16f, size.height * 0.72f, center.x + 16f, size.height * 0.72f, center.x + 18f, size.height * 0.95f)
    close()
  }
  drawPath(shoulders, color = blueShirt, style = Fill)

  // Vest stripes (like construction/citizen vest in screenshot)
  drawLine(
    color = saffron,
    start = Offset(center.x - 6f, size.height * 0.76f),
    end = Offset(center.x - 8f, size.height * 0.95f),
    strokeWidth = 3f
  )
  drawLine(
    color = saffron,
    start = Offset(center.x + 6f, size.height * 0.76f),
    end = Offset(center.x + 8f, size.height * 0.95f),
    strokeWidth = 3f
  )

  // Face
  drawCircle(
    color = skin,
    radius = 11f,
    center = Offset(center.x, center.y - 2f)
  )

  // Eyes
  drawCircle(
    color = Color(0xFF1E293B),
    radius = 1.2f,
    center = Offset(center.x - 4f, center.y - 2f)
  )
  drawCircle(
    color = Color(0xFF1E293B),
    radius = 1.2f,
    center = Offset(center.x + 4f, center.y - 2f)
  )

  // Smile
  val smile = Path().apply {
    moveTo(center.x - 3.5f, center.y + 2f)
    quadraticTo(center.x, center.y + 5f, center.x + 3.5f, center.y + 2f)
  }
  drawPath(smile, color = Color(0xFF1E293B), style = Stroke(width = 1.2f, cap = StrokeCap.Round))

  // Hardhat / Citizen Cap (matching screenshot)
  val cap = Path().apply {
    moveTo(center.x - 13f, center.y - 6f)
    cubicTo(center.x - 12f, center.y - 18f, center.x + 12f, center.y - 18f, center.x + 13f, center.y - 6f)
    close()
  }
  drawPath(cap, color = saffron, style = Fill)
  drawPath(cap, color = darkSaffron, style = Stroke(width = 1.5f))

  // Cap brim
  drawRoundRect(
    color = darkSaffron,
    topLeft = Offset(center.x - 14f, center.y - 7f),
    size = androidx.compose.ui.geometry.Size(28f, 3.5f),
    cornerRadius = androidx.compose.ui.geometry.CornerRadius(2f, 2f)
  )
}

// Drawing the blue magnifying glass with glass reflection (matching screenshot Card 2)
private fun DrawScope.drawBlueMagnifier(isEnabled: Boolean) {
  val blue = if (isEnabled) Color(0xFF2563EB) else Color(0xFF94A3B8)
  val glass = if (isEnabled) Color(0xFFDBEAFE) else Color(0xFFE2E8F0)
  val rim = if (isEnabled) Color(0xFF1E40AF) else Color(0xFF64748B)

  val glassCenter = Offset(size.width * 0.42f, size.height * 0.42f)
  val glassRadius = size.width * 0.25f

  // Glass lens
  drawCircle(
    color = glass,
    radius = glassRadius,
    center = glassCenter,
    style = Fill
  )

  // Glass rim
  drawCircle(
    color = rim,
    radius = glassRadius,
    center = glassCenter,
    style = Stroke(width = 3.5f)
  )

  // Handle
  val handleStart = Offset(glassCenter.x + glassRadius * 0.72f, glassCenter.y + glassRadius * 0.72f)
  val handleEnd = Offset(size.width * 0.84f, size.height * 0.84f)
  drawLine(
    color = rim,
    start = handleStart,
    end = handleEnd,
    strokeWidth = 5f,
    cap = StrokeCap.Round
  )

  // Glass reflection shine
  val shine = Path().apply {
    moveTo(glassCenter.x - glassRadius * 0.5f, glassCenter.y - glassRadius * 0.3f)
    quadraticTo(glassCenter.x - glassRadius * 0.3f, glassCenter.y - glassRadius * 0.6f, glassCenter.x, glassCenter.y - glassRadius * 0.6f)
  }
  drawPath(shine, color = Color.White, style = Stroke(width = 2f, cap = StrokeCap.Round))
}

@Composable
private fun DistrictSearchList(
  districts: List<District>,
  selectedDistrict: District?,
  onSelect: (District) -> Unit,
  onClose: () -> Unit
) {
  var searchQuery by remember { mutableStateOf("") }
  val filteredDistricts = remember(searchQuery, districts) {
    if (searchQuery.isBlank()) districts
    else {
      districts.filter {
        it.districtNameHindi.contains(searchQuery.trim(), ignoreCase = true) ||
          it.districtNameEnglish.contains(searchQuery.trim(), ignoreCase = true)
      }
    }
  }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 18.dp, vertical = 10.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "📍 अपना जिला चुनें (75 जिले)",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFC02626)
      )
      IconButton(onClick = onClose) {
        Icon(imageVector = Icons.Default.Close, contentDescription = "बंद करें")
      }
    }

    OutlinedTextField(
      value = searchQuery,
      onValueChange = { searchQuery = it },
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)
        .testTag("district_search_input"),
      placeholder = { Text("जिले का नाम खोजें (उदा: लखनऊ, आगरा, वाराणसी...)") },
      leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription = null)
      },
      singleLine = true,
      shape = RoundedCornerShape(12.dp)
    )

    LazyColumn(
      modifier = Modifier
        .fillMaxWidth()
        .height(380.dp)
    ) {
      items(filteredDistricts, key = { it.districtId }) { district ->
        val isChosen = selectedDistrict?.districtId == district.districtId
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect(district) }
            .background(if (isChosen) Color(0xFFFFF7ED) else Color.Transparent)
            .padding(horizontal = 14.dp, vertical = 14.dp)
            .testTag("district_item_${district.districtId}"),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(if (isChosen) Color(0xFFEA580C) else Color(0xFFF3F4F6)),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = "📍",
                fontSize = 16.sp
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Text(
                text = district.districtNameHindi,
                fontSize = 16.sp,
                fontWeight = if (isChosen) FontWeight.Bold else FontWeight.Medium,
                color = Color(0xFF0F172A)
              )
              Text(
                text = district.districtNameEnglish,
                fontSize = 12.sp,
                color = Color(0xFF64748B)
              )
            }
          }
          if (isChosen) {
            Icon(
              imageVector = Icons.Default.Check,
              contentDescription = "चयनित",
              tint = Color(0xFFEA580C)
            )
          }
        }
      }
    }
  }
}

@Composable
private fun ConstituencySearchList(
  district: District,
  constituencies: List<Constituency>,
  selectedConstituency: Constituency?,
  onSelect: (Constituency) -> Unit,
  onClose: () -> Unit
) {
  var searchQuery by remember { mutableStateOf("") }
  val filteredConstituencies = remember(searchQuery, constituencies) {
    if (searchQuery.isBlank()) constituencies
    else {
      constituencies.filter {
        it.constituencyNameHindi.contains(searchQuery.trim(), ignoreCase = true) ||
          it.constituencyNameEnglish.contains(searchQuery.trim(), ignoreCase = true) ||
          it.officialSerialNumber.toString().contains(searchQuery.trim())
      }
    }
  }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 18.dp, vertical = 10.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text(
          text = "🗺️ विधानसभा क्षेत्र चुनें",
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF1D4ED8)
        )
        Text(
          text = "जिला: ${district.districtNameHindi} (कुल ${constituencies.size} सीटें)",
          fontSize = 13.sp,
          color = Color(0xFF0369A1)
        )
      }
      IconButton(onClick = onClose) {
        Icon(imageVector = Icons.Default.Close, contentDescription = "बंद करें")
      }
    }

    OutlinedTextField(
      value = searchQuery,
      onValueChange = { searchQuery = it },
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)
        .testTag("constituency_search_input"),
      placeholder = { Text("विधानसभा खोजें (उदा: पश्चिम, कैंट, 171...)") },
      leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription = null)
      },
      singleLine = true,
      shape = RoundedCornerShape(12.dp)
    )

    LazyColumn(
      modifier = Modifier
        .fillMaxWidth()
        .height(380.dp)
    ) {
      items(filteredConstituencies, key = { it.constituencyId }) { constituency ->
        val isChosen = selectedConstituency?.constituencyId == constituency.constituencyId
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect(constituency) }
            .background(if (isChosen) Color(0xFFEFF6FF) else Color.Transparent)
            .padding(horizontal = 14.dp, vertical = 14.dp)
            .testTag("constituency_item_${constituency.constituencyId}"),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(if (isChosen) Color(0xFF2563EB) else Color(0xFFE2E8F0)),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = "${constituency.officialSerialNumber}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = if (isChosen) Color.White else Color(0xFF1E293B)
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Text(
                text = constituency.constituencyNameHindi,
                fontSize = 16.sp,
                fontWeight = if (isChosen) FontWeight.Bold else FontWeight.Medium,
                color = Color(0xFF0F172A)
              )
              Text(
                text = constituency.constituencyNameEnglish,
                fontSize = 12.sp,
                color = Color(0xFF64748B)
              )
            }
          }
          if (isChosen) {
            Icon(
              imageVector = Icons.Default.Check,
              contentDescription = "चयनित",
              tint = Color(0xFF2563EB)
            )
          }
        }
      }
    }
  }
}
