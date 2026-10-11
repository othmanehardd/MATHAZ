package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.SchoolLevel
import com.example.ui.theme.DarkNavyText
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.TurquoisePrimary

@Composable
fun AZMathTopBar(
  onSearchClick: () -> Unit,
  onAdminClick: () -> Unit,
  onLevelSelected: (SchoolLevel) -> Unit,
  selectedLevel: SchoolLevel?,
  bookmarkCount: Int,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier.fillMaxWidth(),
    color = Color.White,
    shadowElevation = 2.dp
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Logo & Slogan
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          // A-Z badge
          Box(
            modifier = Modifier
              .size(40.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(DarkNavyText),
            contentAlignment = Alignment.Center
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(
                text = "A-Z",
                color = Color.White,
                fontWeight = FontWeight.Black,
                fontSize = 13.sp,
                lineHeight = 13.sp
              )
              Text(
                text = "π √",
                color = TurquoisePrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 9.sp,
                lineHeight = 9.sp
              )
            }
          }

          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "A-Z ",
                fontWeight = FontWeight.Black,
                fontSize = 19.sp,
                color = DarkNavyText
              )
              Text(
                text = "MATH",
                fontWeight = FontWeight.Black,
                fontSize = 19.sp,
                color = ElectricBlue
              )
            }
            Text(
              text = "Collège Maroc • 1AC · 2AC · 3AC",
              style = MaterialTheme.typography.labelSmall,
              color = Color(0xFF64748B),
              fontSize = 10.sp
            )
          }
        }

        // Actions: Search, Admin
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          IconButton(
            onClick = onSearchClick,
            modifier = Modifier.size(40.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Search,
              contentDescription = "Rechercher",
              tint = DarkNavyText
            )
          }

          IconButton(
            onClick = onAdminClick,
            modifier = Modifier.size(40.dp)
          ) {
            Icon(
              imageVector = Icons.Default.AdminPanelSettings,
              contentDescription = "Administration",
              tint = Color(0xFF475569)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Level quick selector tabs
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        SchoolLevel.entries.forEach { level ->
          val isSelected = selectedLevel == level
          Box(
            modifier = Modifier
              .weight(1f)
              .clip(RoundedCornerShape(8.dp))
              .background(
                if (isSelected) ElectricBlue else Color(0xFFF1F5F9)
              )
              .clickable { onLevelSelected(level) }
              .padding(vertical = 6.dp),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = level.code,
              fontSize = 12.sp,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
              color = if (isSelected) Color.White else Color(0xFF475569)
            )
          }
        }
      }
    }
  }
}
