package live.quang.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;                               // 0 == 'o' , 1 == 'x'
    int[] gameBoard = { 9, 9, 9, 9, 9, 9, 9, 9, 9 };    // 9 == empty place     (imagine that this is a 3x3 game board)
    int[][] winningPositions = { {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6} };  // Look at the 3x3 board
    boolean gameOver = false;

    public void tapOn( View view ) {
        ImageView anImage = (ImageView) view;               // "view" object from parameter
        int tappedImage = Integer.parseInt( anImage.getTag().toString() );  // get the location/the tag of the tapped image.

        if (gameBoard[tappedImage] == 9  &&  !gameOver) {   // If the place is empty ( == 9) and the GAME is not OVER, allow player to continue. If it is already reserved for a player 'x' or 'o', then do nothing.

            gameBoard[tappedImage] = activePlayer;          // Located the player on game board, according to the tapped place.

            if ( activePlayer == 0 ) {
                anImage.setImageResource( R.drawable.o );   // set an image of "o", change active player
                activePlayer = 1;
            } else {
                anImage.setImageResource( R.drawable.x );   // set an image of "x", change active player
                activePlayer = 0;
            }


            // We need to loop through all the winning positions to find out if they all got the same values (either all '0' or all '1')
            // AND they are not == to 9 (empty place)
            for ( int[] winningPos : winningPositions ) {
                if ( gameBoard[winningPos[0]] == gameBoard[winningPos[1]]  &&  gameBoard[winningPos[1]] == gameBoard[winningPos[2]]  &&  gameBoard[winningPos[0]] != 9 ) {     // We only need to check for one position != 9

                    // Someone has won. Check who it exactly is:
                    String winner = "";
                    if ( activePlayer == 1 ) {
                        winner = "Player O";        // Because we already change to active player after one of them click, so it is reversed.
                    } else {
                        winner = "Player X";
                    }

                    gameOver = true;

                    Button replayButton = (Button) findViewById( R.id.replayButton );
                    TextView winnerAnnounce = (TextView) findViewById( R.id.winnerTextView );
                    winnerAnnounce.setText( winner + " win!" );
                    winnerAnnounce.setVisibility( View.VISIBLE );
                    replayButton.setVisibility( View.VISIBLE );

                }
            }
        }

    }


    public void replay( View view ) {
        Button replayButton = (Button) findViewById( R.id.replayButton );
        TextView winnerAnnounce = (TextView) findViewById( R.id.winnerTextView );

        winnerAnnounce.setVisibility( View.INVISIBLE );
        replayButton.setVisibility( View.INVISIBLE );

        Toast.makeText(this, "LOADING...", Toast.LENGTH_SHORT).show();


        // Need to change all the player's imageView resource to empty (setImageDrawable == null)
        ImageView i1 = (ImageView) findViewById( R.id.O_1 );
        i1.setImageDrawable(null);
        ImageView i2 = (ImageView) findViewById( R.id.O_2 );
        i2.setImageDrawable(null);
        ImageView i3 = (ImageView) findViewById( R.id.O_3 );
        i3.setImageDrawable(null);
        ImageView i4 = (ImageView) findViewById( R.id.O_4 );
        i4.setImageDrawable(null);
        ImageView i5 = (ImageView) findViewById( R.id.O_5 );
        i5.setImageDrawable(null);
        ImageView i6 = (ImageView) findViewById( R.id.O_6 );
        i6.setImageDrawable(null);
        ImageView i7 = (ImageView) findViewById( R.id.O_7 );
        i7.setImageDrawable(null);
        ImageView i8 = (ImageView) findViewById( R.id.O_8 );
        i8.setImageDrawable(null);
        ImageView i9 = (ImageView) findViewById( R.id.O_9 );
        i9.setImageDrawable(null);


        // Resetting everything:
        for (int i = 0; i < gameBoard.length; ++i) {
            gameBoard[i] = 9;
        }
        activePlayer = 0;
        gameOver = false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}