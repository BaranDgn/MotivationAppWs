package com.example.psikoappws.presenter.features.component

import android.util.LongSparseArray
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.psikoappws.data.model.Diary
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DiaryItem(
    diary: Diary,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    onDeleteClick:() -> Unit
){
    Box(
        modifier = modifier
    ){
        Canvas(
            //matchParentSize: This will give our canvas the size
            //parent measured the constraints so after this box knows how much
            //width and height it occupies. Then this will retrun the size to the canvas

            modifier = Modifier.matchParentSize()
        ){
            val clipPath= Path().apply{
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
            clipPath(clipPath){
                drawRoundRect(
                    Color(diary.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
            clipPath(clipPath){
                drawRoundRect(
                    color = Color(diary.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(diary.color, 0x000000, 0.2f)
                    ),
                    topLeft = Offset(size.width - cutCornerSize.toPx(),-100f),
                    size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f ),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }

        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(end = 32.dp)
        ) {
            Text(
                text = getDateTime((diary.timeStamp)),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                //this will cut off when it gets too long
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = diary.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                maxLines = 10,
                //this will cut off when it gets too long
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription ="Delete Note"
            )
        }
    }


}

fun getDateTime(ts:Long?):String{
    if(ts == null) return ""
    //Get instance of calendar
    val calendar = Calendar.getInstance(Locale.getDefault())
    //get current date from ts
    calendar.timeInMillis = ts
    //return formatted date
    return android.text.format.DateFormat.format("E, dd MMM yyyy", calendar).toString()
}