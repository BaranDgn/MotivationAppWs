package com.example.psikoappws.presenter.features.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.psikoappws.domain.util.DiaryOrder
import com.example.psikoappws.domain.util.OrderType

@Composable
fun OrderSection(
    modifier : Modifier = Modifier,
    noteOrder: DiaryOrder = DiaryOrder.Date(OrderType.Descending),
    onOrderChange: (DiaryOrder) -> Unit
){
    Column(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Content",
                //this radio buttonshould be selected if our node order is noteOrder.Title
                //because we want to order by title
                selected = noteOrder is DiaryOrder.Title,
                //order change if we click on this radio button, we want to trigger
                //our callback function with new noteOrder
                onSelect = { onOrderChange(DiaryOrder.Title(noteOrder.orderType))
                })
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Date",
                selected = noteOrder is DiaryOrder.Date,
                onSelect = { onOrderChange(DiaryOrder.Date(noteOrder.orderType))
                })
            Spacer(modifier = Modifier.width(8.dp))


        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            //Ascending
            DefaultRadioButton(
                text = "Ascending",
                //This will be selected if noteOrder's ordertype is OrderType's Ascending object.
                selected = noteOrder.orderType is OrderType.Ascending,
                //we want to keep the current node order but we want to change the node order ordertype
                //and we don't make this noteOrder.orderType here mutable
                //we need to create new function to change only ordetype.
                onSelect = { onOrderChange(noteOrder.copy(OrderType.Ascending))
                })

            Spacer(modifier = Modifier.width(8.dp))

            //Descending
            DefaultRadioButton(
                text = "Descending",
                selected = noteOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(noteOrder.copy(OrderType.Descending))
                })
        }
    }
}