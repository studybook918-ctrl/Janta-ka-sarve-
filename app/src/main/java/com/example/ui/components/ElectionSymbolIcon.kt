package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ElectionSymbolIcon(
  symbolKey: String,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .size(54.dp)
      .clip(RoundedCornerShape(8.dp))
      .background(Color.White)
      .border(1.dp, Color(0xFFCBD5E1), RoundedCornerShape(8.dp))
      .testTag("symbol_icon_$symbolKey"),
    contentAlignment = Alignment.Center
  ) {
    Canvas(modifier = Modifier.size(42.dp)) {
      when (symbolKey.lowercase()) {
        "lotus" -> drawLotus()
        "bicycle" -> drawBicycle()
        "elephant" -> drawElephant()
        "hand" -> drawHand()
        "broom" -> drawBroom()
        "handpump" -> drawHandpump()
        "kettle" -> drawKettle()
        "nota" -> drawNota()
        else -> drawFallback()
      }
    }
  }
}

private fun DrawScope.drawLotus() {
  val center = Offset(size.width / 2f, size.height * 0.55f)
  val saffron = Color(0xFFFF9933)
  val green = Color(0xFF138808)
  val pink = Color(0xFFE11D48)

  // Stem & Base Leaves
  val stemPath = Path().apply {
    moveTo(center.x, size.height * 0.9f)
    lineTo(center.x, size.height * 0.7f)
  }
  drawPath(stemPath, color = green, style = Stroke(width = 4f, cap = StrokeCap.Round))

  // Base calyx
  drawArc(
    color = green,
    startAngle = 0f,
    sweepAngle = 180f,
    useCenter = true,
    topLeft = Offset(center.x - 14f, size.height * 0.65f),
    size = Size(28f, 16f)
  )

  // Center petal
  val centerPetal = Path().apply {
    moveTo(center.x, size.height * 0.7f)
    cubicTo(center.x - 10f, size.height * 0.45f, center.x - 12f, size.height * 0.2f, center.x, size.height * 0.12f)
    cubicTo(center.x + 12f, size.height * 0.2f, center.x + 10f, size.height * 0.45f, center.x, size.height * 0.7f)
    close()
  }
  drawPath(centerPetal, color = pink, style = Fill)
  drawPath(centerPetal, color = Color(0xFF9F1239), style = Stroke(width = 1.5f))

  // Left petal
  val leftPetal = Path().apply {
    moveTo(center.x - 4f, size.height * 0.65f)
    cubicTo(center.x - 22f, size.height * 0.55f, center.x - 24f, size.height * 0.3f, center.x - 12f, size.height * 0.25f)
    cubicTo(center.x - 5f, size.height * 0.35f, center.x - 2f, size.height * 0.5f, center.x - 4f, size.height * 0.65f)
    close()
  }
  drawPath(leftPetal, color = saffron, style = Fill)
  drawPath(leftPetal, color = Color(0xFFC2410C), style = Stroke(width = 1.5f))

  // Right petal
  val rightPetal = Path().apply {
    moveTo(center.x + 4f, size.height * 0.65f)
    cubicTo(center.x + 22f, size.height * 0.55f, center.x + 24f, size.height * 0.3f, center.x + 12f, size.height * 0.25f)
    cubicTo(center.x + 5f, size.height * 0.35f, center.x + 2f, size.height * 0.5f, center.x + 4f, size.height * 0.65f)
    close()
  }
  drawPath(rightPetal, color = saffron, style = Fill)
  drawPath(rightPetal, color = Color(0xFFC2410C), style = Stroke(width = 1.5f))
}

private fun DrawScope.drawBicycle() {
  val red = Color(0xFFDC2626)
  val black = Color(0xFF1E293B)
  val wheelRadius = size.width * 0.2f
  val yWheel = size.height * 0.68f
  val xLeftWheel = size.width * 0.25f
  val xRightWheel = size.width * 0.75f

  // Wheels
  drawCircle(color = black, radius = wheelRadius, center = Offset(xLeftWheel, yWheel), style = Stroke(width = 3.5f))
  drawCircle(color = black, radius = wheelRadius, center = Offset(xRightWheel, yWheel), style = Stroke(width = 3.5f))

  // Wheel hubs
  drawCircle(color = black, radius = 3f, center = Offset(xLeftWheel, yWheel))
  drawCircle(color = black, radius = 3f, center = Offset(xRightWheel, yWheel))

  val bottomBracket = Offset(size.width * 0.48f, yWheel)
  val seatPostTop = Offset(size.width * 0.42f, size.height * 0.38f)
  val headTubeTop = Offset(size.width * 0.68f, size.height * 0.32f)

  // Frame
  val frame = Path().apply {
    // rear stays
    moveTo(xLeftWheel, yWheel)
    lineTo(seatPostTop.x, seatPostTop.y)
    lineTo(bottomBracket.x, bottomBracket.y)
    lineTo(xLeftWheel, yWheel)
    // main triangle
    moveTo(seatPostTop.x, seatPostTop.y)
    lineTo(headTubeTop.x, headTubeTop.y)
    lineTo(bottomBracket.x, bottomBracket.y)
    // fork
    moveTo(headTubeTop.x, headTubeTop.y)
    lineTo(xRightWheel, yWheel)
  }
  drawPath(frame, color = red, style = Stroke(width = 3f, cap = StrokeCap.Round))

  // Saddle
  drawLine(
    color = black,
    start = Offset(seatPostTop.x - 7f, seatPostTop.y - 3f),
    end = Offset(seatPostTop.x + 9f, seatPostTop.y - 3f),
    strokeWidth = 4f,
    cap = StrokeCap.Round
  )

  // Handlebars
  drawLine(
    color = black,
    start = Offset(headTubeTop.x - 4f, headTubeTop.y - 6f),
    end = Offset(headTubeTop.x + 8f, headTubeTop.y - 6f),
    strokeWidth = 3.5f,
    cap = StrokeCap.Round
  )
}

private fun DrawScope.drawElephant() {
  val blue = Color(0xFF0284C7)
  val darkBlue = Color(0xFF0369A1)
  val body = Path().apply {
    moveTo(size.width * 0.22f, size.height * 0.78f)
    lineTo(size.width * 0.22f, size.height * 0.6f)
    // Back curve
    cubicTo(size.width * 0.2f, size.height * 0.3f, size.width * 0.6f, size.height * 0.22f, size.width * 0.72f, size.height * 0.32f)
    // Head & trunk
    cubicTo(size.width * 0.85f, size.height * 0.35f, size.width * 0.9f, size.height * 0.5f, size.width * 0.88f, size.height * 0.68f)
    cubicTo(size.width * 0.86f, size.height * 0.78f, size.width * 0.95f, size.height * 0.75f, size.width * 0.92f, size.height * 0.62f)
    // Under trunk
    lineTo(size.width * 0.82f, size.height * 0.55f)
    // Front leg
    lineTo(size.width * 0.78f, size.height * 0.78f)
    lineTo(size.width * 0.68f, size.height * 0.78f)
    lineTo(size.width * 0.68f, size.height * 0.6f)
    // Belly
    lineTo(size.width * 0.42f, size.height * 0.62f)
    // Back leg
    lineTo(size.width * 0.38f, size.height * 0.78f)
    lineTo(size.width * 0.28f, size.height * 0.78f)
    lineTo(size.width * 0.3f, size.height * 0.58f)
    lineTo(size.width * 0.22f, size.height * 0.78f)
    close()
  }
  drawPath(body, color = blue, style = Fill)
  drawPath(body, color = darkBlue, style = Stroke(width = 1.5f))

  // Ear flap
  drawOval(
    color = Color(0xFF38BDF8),
    topLeft = Offset(size.width * 0.6f, size.height * 0.35f),
    size = Size(size.width * 0.16f, size.height * 0.22f)
  )
  // Eye
  drawCircle(
    color = Color.White,
    radius = 2.5f,
    center = Offset(size.width * 0.76f, size.height * 0.38f)
  )
  drawCircle(
    color = Color.Black,
    radius = 1.2f,
    center = Offset(size.width * 0.77f, size.height * 0.38f)
  )
}

private fun DrawScope.drawHand() {
  val handColor = Color(0xFF2563EB)
  val darkBlue = Color(0xFF1E3A8A)

  val handPath = Path().apply {
    // Wrist
    moveTo(size.width * 0.38f, size.height * 0.88f)
    lineTo(size.width * 0.62f, size.height * 0.88f)
    // Pinky side
    lineTo(size.width * 0.66f, size.height * 0.58f)
    // Pinky
    cubicTo(size.width * 0.68f, size.height * 0.4f, size.width * 0.64f, size.height * 0.35f, size.width * 0.61f, size.height * 0.36f)
    lineTo(size.width * 0.58f, size.height * 0.48f)
    // Ring finger
    lineTo(size.width * 0.58f, size.height * 0.28f)
    cubicTo(size.width * 0.58f, size.height * 0.22f, size.width * 0.52f, size.height * 0.22f, size.width * 0.52f, size.height * 0.28f)
    lineTo(size.width * 0.51f, size.height * 0.46f)
    // Middle finger (tallest)
    lineTo(size.width * 0.50f, size.height * 0.18f)
    cubicTo(size.width * 0.50f, size.height * 0.12f, size.width * 0.44f, size.height * 0.12f, size.width * 0.44f, size.height * 0.18f)
    lineTo(size.width * 0.43f, size.height * 0.46f)
    // Index finger
    lineTo(size.width * 0.42f, size.height * 0.26f)
    cubicTo(size.width * 0.42f, size.height * 0.20f, size.width * 0.36f, size.height * 0.20f, size.width * 0.36f, size.height * 0.26f)
    lineTo(size.width * 0.35f, size.height * 0.52f)
    // Thumb
    cubicTo(size.width * 0.22f, size.height * 0.48f, size.width * 0.18f, size.height * 0.52f, size.width * 0.24f, size.height * 0.62f)
    lineTo(size.width * 0.36f, size.height * 0.68f)
    lineTo(size.width * 0.38f, size.height * 0.88f)
    close()
  }
  drawPath(handPath, color = handColor, style = Fill)
  drawPath(handPath, color = darkBlue, style = Stroke(width = 1.8f))
}

private fun DrawScope.drawBroom() {
  val handleColor = Color(0xFF78350F)
  val bristlesColor = Color(0xFFD97706)

  // Handle
  drawLine(
    color = handleColor,
    start = Offset(size.width * 0.2f, size.height * 0.2f),
    end = Offset(size.width * 0.55f, size.height * 0.55f),
    strokeWidth = 5f,
    cap = StrokeCap.Round
  )

  // Bristles fan
  val broom = Path().apply {
    moveTo(size.width * 0.5f, size.height * 0.5f)
    lineTo(size.width * 0.82f, size.height * 0.62f)
    lineTo(size.width * 0.88f, size.height * 0.85f)
    lineTo(size.width * 0.62f, size.height * 0.82f)
    close()
  }
  drawPath(broom, color = bristlesColor, style = Fill)
  drawPath(broom, color = Color(0xFFB45309), style = Stroke(width = 1.5f))

  // Bristle lines
  for (i in 1..4) {
    val offset = i * 4f
    drawLine(
      color = Color(0xFF92400E),
      start = Offset(size.width * 0.52f + offset, size.height * 0.52f + offset),
      end = Offset(size.width * 0.68f + offset * 1.5f, size.height * 0.8f + offset * 0.5f),
      strokeWidth = 1.5f
    )
  }
}

private fun DrawScope.drawHandpump() {
  val iron = Color(0xFF334155)
  // Main column
  drawRect(
    color = iron,
    topLeft = Offset(size.width * 0.44f, size.height * 0.25f),
    size = Size(size.width * 0.12f, size.height * 0.55f)
  )
  // Base
  drawRect(
    color = iron,
    topLeft = Offset(size.width * 0.32f, size.height * 0.80f),
    size = Size(size.width * 0.36f, size.height * 0.10f)
  )
  // Spout
  drawLine(
    color = iron,
    start = Offset(size.width * 0.44f, size.height * 0.55f),
    end = Offset(size.width * 0.22f, size.height * 0.60f),
    strokeWidth = 5f,
    cap = StrokeCap.Round
  )
  // Water drop
  drawCircle(
    color = Color(0xFF38BDF8),
    radius = 3f,
    center = Offset(size.width * 0.22f, size.height * 0.72f)
  )
  // Handle
  val handle = Path().apply {
    moveTo(size.width * 0.54f, size.height * 0.32f)
    lineTo(size.width * 0.75f, size.height * 0.22f)
    lineTo(size.width * 0.88f, size.height * 0.38f)
  }
  drawPath(handle, color = iron, style = Stroke(width = 4f, cap = StrokeCap.Round))
}

private fun DrawScope.drawKettle() {
  val metal = Color(0xFF0F766E)
  // Body
  drawOval(
    color = metal,
    topLeft = Offset(size.width * 0.28f, size.height * 0.42f),
    size = Size(size.width * 0.44f, size.height * 0.38f)
  )
  // Lid
  drawArc(
    color = Color(0xFF14B8A6),
    startAngle = 180f,
    sweepAngle = 180f,
    useCenter = true,
    topLeft = Offset(size.width * 0.38f, size.height * 0.34f),
    size = Size(size.width * 0.24f, size.height * 0.14f)
  )
  // Knob
  drawCircle(
    color = Color(0xFF042F2E),
    radius = 2.5f,
    center = Offset(size.width * 0.5f, size.height * 0.32f)
  )
  // Spout
  val spout = Path().apply {
    moveTo(size.width * 0.3f, size.height * 0.55f)
    lineTo(size.width * 0.14f, size.height * 0.42f)
    lineTo(size.width * 0.16f, size.height * 0.38f)
    lineTo(size.width * 0.34f, size.height * 0.48f)
  }
  drawPath(spout, color = metal, style = Fill)
  // Handle
  val handle = Path().apply {
    moveTo(size.width * 0.34f, size.height * 0.38f)
    cubicTo(size.width * 0.34f, size.height * 0.18f, size.width * 0.66f, size.height * 0.18f, size.width * 0.66f, size.height * 0.38f)
  }
  drawPath(handle, color = Color(0xFF042F2E), style = Stroke(width = 3.5f, cap = StrokeCap.Round))
}

private fun DrawScope.drawNota() {
  val red = Color(0xFFDC2626)
  // Ballot box outline
  drawRect(
    color = Color(0xFF475569),
    topLeft = Offset(size.width * 0.18f, size.height * 0.18f),
    size = Size(size.width * 0.64f, size.height * 0.64f),
    style = Stroke(width = 2.5f)
  )
  // Large Red Cross
  drawLine(
    color = red,
    start = Offset(size.width * 0.24f, size.height * 0.24f),
    end = Offset(size.width * 0.76f, size.height * 0.76f),
    strokeWidth = 5f,
    cap = StrokeCap.Round
  )
  drawLine(
    color = red,
    start = Offset(size.width * 0.76f, size.height * 0.24f),
    end = Offset(size.width * 0.24f, size.height * 0.76f),
    strokeWidth = 5f,
    cap = StrokeCap.Round
  )
}

private fun DrawScope.drawFallback() {
  drawCircle(
    color = Color(0xFF2563EB),
    radius = size.width * 0.35f,
    center = Offset(size.width / 2f, size.height / 2f),
    style = Stroke(width = 3f)
  )
}
