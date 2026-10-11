package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.MathRepository
import com.example.model.SchoolLevel
import com.example.model.SearchResult
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.TurquoisePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDialog(
  onDismiss: () -> Unit,
  onSelectResult: (SearchResult) -> Unit,
  repository: MathRepository = MathRepository.instance
) {
  var searchQuery by remember { mutableStateOf("") }
  var selectedLevelFilter by remember { mutableStateOf<SchoolLevel?>(null) }
  val results = remember(searchQuery, selectedLevelFilter) {
    repository.search(searchQuery, selectedLevelFilter)
  }

  Dialog(onDismissRequest = onDismiss) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .heightIn(max = 600.dp),
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Rechercher une notion",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0F172A)
          )
          IconButton(onClick = onDismiss) {
            Icon(Icons.Default.Clear, contentDescription = "Fermer")
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
          value = searchQuery,
          onValueChange = { searchQuery = it },
          placeholder = { Text("Ex: Pythagore, Racines, Thalès...") },
          leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = ElectricBlue) },
          trailingIcon = {
            if (searchQuery.isNotEmpty()) {
              IconButton(onClick = { searchQuery = "" }) {
                Icon(Icons.Default.Clear, contentDescription = "Effacer")
              }
            }
          },
          singleLine = true,
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Level chips filter
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          FilterChip(
            selected = selectedLevelFilter == null,
            onClick = { selectedLevelFilter = null },
            label = { Text("Tous", fontSize = 12.sp) }
          )
          SchoolLevel.entries.forEach { level ->
            FilterChip(
              selected = selectedLevelFilter == level,
              onClick = { selectedLevelFilter = if (selectedLevelFilter == level) null else level },
              label = { Text(level.code, fontSize = 12.sp) }
            )
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Preset quick searches
        if (searchQuery.isBlank()) {
          Text(
            text = "Recherches suggérées :",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF64748B)
          )
          Spacer(modifier = Modifier.height(6.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            listOf("Pythagore", "Racines", "Thalès", "Fonctions").forEach { suggestion ->
              SuggestionChip(
                onClick = { searchQuery = suggestion },
                label = { Text(suggestion, fontSize = 11.sp) }
              )
            }
          }
          Spacer(modifier = Modifier.height(16.dp))
        }

        // Results list
        if (searchQuery.isNotBlank() && results.isEmpty()) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 32.dp),
            contentAlignment = Alignment.Center
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text("🔍", fontSize = 32.sp)
              Spacer(modifier = Modifier.height(8.dp))
              Text(
                text = "Aucun résultat pour « $searchQuery »",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF64748B)
              )
              Text(
                text = "Vérifie l'orthographe ou choisis un mot-clé général",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF94A3B8)
              )
            }
          }
        } else {
          LazyColumn(
            modifier = Modifier.weight(1f, fill = false),
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            items(results) { item ->
              Card(
                modifier = Modifier
                  .fillMaxWidth()
                  .clip(RoundedCornerShape(12.dp))
                  .clickable {
                    onSelectResult(item)
                    onDismiss()
                  },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC))
              ) {
                Row(
                  modifier = Modifier.padding(12.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Box(
                    modifier = Modifier
                      .size(36.dp)
                      .background(
                        when (item.level) {
                          SchoolLevel.AC1 -> Color(0xFFDCFCE7)
                          SchoolLevel.AC2 -> Color(0xFFEDE9FE)
                          SchoolLevel.AC3 -> Color(0xFFDBEAFE)
                        },
                        RoundedCornerShape(8.dp)
                      ),
                    contentAlignment = Alignment.Center
                  ) {
                    Text(
                      text = item.level.code,
                      fontWeight = FontWeight.Bold,
                      fontSize = 11.sp,
                      color = when (item.level) {
                        SchoolLevel.AC1 -> Color(0xFF15803D)
                        SchoolLevel.AC2 -> Color(0xFF6D28D9)
                        SchoolLevel.AC3 -> ElectricBlue
                      }
                    )
                  }

                  Spacer(modifier = Modifier.width(12.dp))

                  Column(modifier = Modifier.weight(1f)) {
                    Text(
                      text = item.title,
                      style = MaterialTheme.typography.bodyMedium,
                      fontWeight = FontWeight.Bold,
                      color = Color(0xFF0F172A)
                    )
                    Text(
                      text = item.snippet,
                      style = MaterialTheme.typography.bodySmall,
                      color = Color(0xFF64748B),
                      maxLines = 1
                    )
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
