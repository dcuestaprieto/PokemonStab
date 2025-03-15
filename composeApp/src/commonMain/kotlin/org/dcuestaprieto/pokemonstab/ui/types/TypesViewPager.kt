package org.dcuestaprieto.pokemonstab.ui.types

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import kotlinx.coroutines.launch

@Composable
fun TwoViewsWithIndicator() {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 2 }
    )

    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        //box con pestañas
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            //el ancho entre 2
            val tabWidth = maxWidth / 2

            // calculos calculos
            val indicatorOffset = (pagerState.currentPage + pagerState.currentPageOffsetFraction) * tabWidth

            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Vista 1",
                        modifier = Modifier
                            .width(tabWidth)
                            .clickable {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(0)
                                }
                            },
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Vista 2",
                        modifier = Modifier
                            .width(tabWidth)
                            .clickable {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(1)
                                }
                            },
                        textAlign = TextAlign.Center
                    )
                }
                Box(
                    modifier = Modifier
                        .offset(x = indicatorOffset)
                        .width(tabWidth)
                        .height(4.dp)
                        .background(Color.Blue)
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> FirstScreen()
                1 -> SecondScreen()
            }
        }
    }
}

@Composable
fun FirstScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Contenido de la Vista 1")
    }
}

@Composable
fun SecondScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Contenido de la Vista 2")
    }
}