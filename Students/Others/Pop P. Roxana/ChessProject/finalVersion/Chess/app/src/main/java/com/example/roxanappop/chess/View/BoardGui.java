package com.example.roxanappop.chess.View;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.example.roxanappop.chess.Model.Board.Board;
import com.example.roxanappop.chess.Model.Board.Cell;
import com.example.roxanappop.chess.Model.Colour;
import com.example.roxanappop.chess.Model.Pieces.Piece;
import com.example.roxanappop.chess.Model.Position;
import com.example.roxanappop.chess.R;

import java.util.ArrayList;

/**
 * Created by roxanappop on 12/21/2015.
 */
public class BoardGui {

    ImageButton[] buttons;

    public BoardGui(Context context) {


        buttons = new ImageButton[64];

        GridLayout gridLayout = new GridLayout(context);
        gridLayout.setColumnCount(8);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.setMargins(0, 0, 0, 80);
        RelativeLayout contentLayout = (RelativeLayout) ((Activity)context).findViewById(R.id.content_main_layout);

        for(int i=0;i<64;i++){

            buttons[i] = (ImageButton) ((Activity)context).getLayoutInflater().inflate(R.layout.image_button_layout,null);
            buttons[i].setId(i);

            GridLayout.LayoutParams gridLayoutParams = new GridLayout.LayoutParams();
            gridLayoutParams.setMargins(0, 0, 0, 0);
            gridLayout.setPadding(0, 0, 0, 0);
            gridLayout.addView(buttons[i], 50, 50);

        }

        contentLayout.addView(gridLayout, layoutParams);
        setDefaultButtonsColour();
    }

    public void syncBoards(Board board,int turn){

        for(ImageButton button: buttons){
            Position position = getPositionFromId(button.getId(),turn);
            Piece piece = board.getPieceAtPosition(position);
            if(piece==null){
                    button.setImageResource(0);
            }
            else{
                    int image = piece.getImage();
                    button.setImageResource(0);
                    button.setImageResource(image);

            }
        }

    }



    public void setImageButtonListeners(View.OnClickListener listener){
        for(ImageButton button: buttons){
            button.setOnClickListener(listener);
        }
    }

    public Position getPositionFromId(int id,int turn){
        int xForWhite = id/8;
        int yForWhite = id%8;
        if(turn== Colour.WHITE){
            return new Position(xForWhite,yForWhite);
        }
        else{
            int xForBlack = 7 - xForWhite;
            int yForBlack = 7 - yForWhite;
            return new Position(xForBlack,yForBlack);
        }
    }



    private int getIdFromPosition(Position position, int turn){

        int xForWhite = position.getX();
        int yForWhite = position.getY();

        int xForBlack = 7 - xForWhite;
        int yForBlack = 7 - yForWhite;

        if(turn==Colour.WHITE){

            return position.getX()*8+position.getY();
        }else{
            return xForBlack*8+yForBlack;
        }

    }

    public void emphasizeButtons(ArrayList<Position> positions,int turn){

        for(Position position: positions){

            int buttonId = getIdFromPosition(position,turn);
            ImageButton imageButton = buttons[buttonId];
            imageButton.setBackgroundColor(Color.GRAY);

        }

    }

    public void setDefaultButtonsColour(){

        for(int i=0;i<64;i++) {

            if ((i / 8) % 2 == 0) {//even row
                if (i % 2 == 0) {
                    buttons[i].setBackgroundColor(Color.rgb(245, 222, 179));//cream
                } else {
                    buttons[i].setBackgroundColor(Color.rgb(160, 82, 45));//brown
                }
            } else {

                if (i % 2 == 1) {
                    buttons[i].setBackgroundColor(Color.rgb(245, 222, 179));//cream
                } else {
                    buttons[i].setBackgroundColor(Color.rgb(160, 82, 45));//brown
                }

            }
        }
    }
}
