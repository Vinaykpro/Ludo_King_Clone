package com.vinaykpro.ludoking;

import static android.view.View.GONE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    DisplayMetrics displayMetrics;
    double dpHeight;
    double dpWidth;
    double pxWidth;
    double pxHeight;
    ImageView ludoBoard,redHomeBlink,red1activecircle,red2activecircle,red3activecircle,red4activecircle;
    View step1,step2,step3,step4,step5,step6;
    ConstraintLayout completeBackground,redpiece1,redpiece2,redpiece3,redpiece4,greenpiece1,greenpiece2,greenpiece3,greenpiece4,bluepiece1,bluepiece2,bluepiece3,bluepiece4,yellowpiece1,yellowpiece2,yellowpiece3,yellowpiece4;

    float onePercentWidth,onePercentHeight;

    TextView playername1,playername2,playername3,playername4;

    EditText editText;

    int gametype = 1;

    int nop;

    double[] dx = {53.32,53.32,53.32,53.32,53.32,60.04,66.7,73.36,80.02,86.68,93.34,93.34,93.34,86.68,80.02,73.36,66.7 ,60.04,53.32,53.32,53.32,53.32,53.32,53.32,46.66,40   ,40   ,40   ,40   ,40   ,40
                  ,33.34,26.68,20.02,13.36,6.7  ,0.04 ,0.04 ,0.04,6.7  ,13.36,20.02,26.68,33.34, 40  , 40  , 40  , 40  , 40  , 40  ,46.66,53.32,46.66,46.66,46.66,46.66,46.66,46.66,86.68,80.02,73.36,66.7 ,60.04,53.38,46.66,46.66,46.66,46.66,46.66,46.66,6.66 ,13.32,19.98,26.64,33.3 ,39.96};
    double[] dy = { 6.66,13.32,19.98,26.64,33.3 , 40  , 40 , 40  ,  40 ,  40 ,  40 ,46.66,53.32,53.32,53.32,53.32,53.32,53.32,60.04,66.7 ,73.36,80.02,86.68,93.34,93.34,93.34,86.68,80.02,73.36,66.7 ,60.04
                  ,53.32,53.32,53.32,53.32,53.32,53.32,46.66, 40 , 40  , 40  , 40  , 40  , 40  ,33.34,26.68,20.02,13.36,6.7  ,0.04 ,0.04 ,0.04 ,6.66 ,13.32,19.98,26.64,33.3 ,39.96,46.66,46.66,46.66,46.66,46.66,46.66,86.68,80.02,73.36,66.7 ,60.04,53.38,46.66,46.66,46.66,46.66,46.66,46.66};

    float[] x = new float[dy.length];
    float[] y = new float[dy.length];

    float pushXForPieces;
    float pushYForPieces;

    ImageView mainDiceImageView,hintArrow;

    int reddicevalue,greendicevalue,bluedicevalue,yellowdicevalue;

    float pos1[][],pos2[][],pos3[][],pos4[][];

    float pieceWidth,pieceHeight;

    Drawable redCircle,greenCircle,blueCircle,yellowCircle;

    List<Player> players;

    static String currentPlayerColor = "";
    static  int currentPlayerPosition = 3;

    static int currentPlayerDice = 6;

    int currentPlayerIndex = 0;

    String currentPlayerName;

    int currentPlayerSelectedIndex = 0;

    List<Piece> rp,gp,bp,yp;

    int[] wBlocks52,wBlocks58,wBlocks64,wBlocks70;

    static boolean isDiceMovableExtraChance = false;
    Dice d;

    MediaPlayer gameStartSound,diceRollSound,stepSound,safeSound,deathSound,pantaSound,congratulationSound;
    int sizeOfBox;

    float blockSize;

    Piece autoMove;

    HashSet<Integer> safeSpots;

    boolean normalPiece = true;

    private AnimatorSet[] animatorSets;

    ImageView crownIndex1,crownIndex2,crownIndex3,crownIndex4;

    int currentWinnerPosition = 1;

    // ingame menu button and items
    ConstraintLayout ingamemenuitemslayout;
    ImageView ingamemenubtn,menuremoveplayersbtn,menuexitbtn;

    // ingameexitgamelayout
    ConstraintLayout quitgamelayout;
    ImageView ingameyesbtn,ingamenobtn,ingamesoundbtn,ingamemusicbtn;

    // ingame rmp layout
    ConstraintLayout ingamermplayout,rmp1bg,rmp2bg,rmp3bg,rmp4bg;
    ImageView rmpp1piece,rmpp2piece,rmpp3piece,rmpp4piece,rmpp1removeicon,rmpp2removeicon,rmpp3removeicon,rmpp4removeicon,rmpbackbtn,rmpmenubtn;
    TextView rmpp1name, rmpp2name,rmpp3name,rmpp4name;

    // ingmae rmp sublayout confirmrmplayout
    ConstraintLayout confirmrmplayout;
    ImageView selectedrmppiece,confirmrmpyesbtn,confirmrmpnobtn;

    int selectedconfirmrmpplayerindex = -1;

    int rmpindexforp1=1,rmpindexforp2=2,rmpindexforp3=3,rmpindexforp4=4;

    // players exit boxes
    ImageView p1exitbox,p2exitbox,p3exitbox,p4exitbox;
    String player1color,player2color,player3color,player4color;
    String player1name,player2name,player3name,player4name;

    int currentWinnerPlayerIndex = -1;

    // Congratulations screen
    ConstraintLayout congratulationslayout;
    ImageView congratsmenubtn,congratssoundbtn,congratssharebtn,congratsreplaybtn;

    ConstraintLayout winnerlistp1layout,winnerlistp2layout,winnerlistp3layout,winnerlistp4layout;
    ImageView wlistcrown1,wlistcrown2,wlistcrown3,wlistcrown4,wlistpiece1,wlistpiece2,wlistpiece3,wlistpiece4,wlistwinorlose1,wlistwinorlose2,wlistwinorlose3,wlistwinorlose4;
    TextView wlistname1,wlistname2,wlistname3,wlistname4;

    boolean isbluegreenreadytowin=false,isredyellowreadytowin=false;

    Handler globalHandler;

    SharedPreferences sharedPreferences;

    boolean isSoundOn=true,isMusicOn=false;

    int botwins=0,botloses=0;

    ImageView gameStartImageView;

    class Piece
    {
        // managed by constructor
        String colour;
        ConstraintLayout piece;
        ImageView readyToPick;
        int startPosition;

        int numberOfSteps = 0;

        int endPosition;
        float defX;
        float defY;

        int diceValue;

        // managed at runtime
        boolean isAlive = false;
        boolean isClickable = true;
        boolean isBotPiece;
        boolean isReadyToEnterWinnerZone = false;
        int currBlock = -1;

        boolean hasCompletedItsPurpose = false;

        int currWinnerBlock = 0;

        int[] winnerBlocks;

        ImageView pieceIcon,pieceStandIcon;
        boolean isThisPlayerWon = false;


        private final ObjectAnimator rotateAnimator;

        Piece(String color,ConstraintLayout piece,ImageView readyToPick,ImageView pieceIcon,ImageView pieceStandIcon,int startPosition,float defX,float defY,boolean isBotPiece)
        {
            this.colour = color;
            this.piece = piece;
            this.readyToPick = readyToPick;
            this.pieceIcon = pieceIcon;
            this.pieceStandIcon = pieceStandIcon;
            this.startPosition = startPosition;
            this.defX = defX;
            this.defY = defY;
            this.isBotPiece = isBotPiece;
            this.piece.setTranslationX(defX);
            this.piece.setTranslationY(defY);
            this.readyToPick.setVisibility(View.INVISIBLE);
            this.piece.setVisibility(View.VISIBLE);
            this.endPosition = startPosition!=0?startPosition-2:50;
            if(!isBotPiece) {
                this.piece.setOnClickListener(view -> {
                    if (isAlive && isClickable && currentPlayerColor.equals(colour)) {
                        diceValue = currentPlayerDice;
                        currentPlayerDice = -1;
                        for (Piece p : getPiecesByColor(colour)) {
                            p.inactiveState();
                        }
                        checkAdjustments(currBlock);
                        move(diceValue);
                        //Toast.makeText(MainActivity.this, x+"", Toast.LENGTH_SHORT).show();
                    } else if (!isAlive && currentPlayerColor.equals(colour) && currentPlayerDice == 6) {
                        currentPlayerDice = -1;
                        for (Piece p : getPiecesByColor(colour)) {
                            p.inactiveState();
                        }
                        makeAlive();
                    }
                });
            }
            if(!normalPiece) {
                pieceIcon.setScaleX(0.8f);pieceIcon.setScaleY(0.8f);
                pieceIcon.setImageDrawable(getStylishIconDrawableByColor(this.colour));
                pieceStandIcon.setVisibility(GONE);
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) readyToPick.getLayoutParams();
                params.topMargin = 0;
                params.topToTop = pieceIcon.getId();
                params.leftToLeft = pieceIcon.getId();
                params.bottomToBottom = pieceIcon.getId();
                params.rightToRight = pieceIcon.getId();
                this.readyToPick.setLayoutParams(params);
                readyToPick.setScaleX(1.2f);readyToPick.setScaleY(1.2f);
            }

            /*piece.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    die();
                    return false;
                }
            });*/
            rotateAnimator = ObjectAnimator.ofFloat(readyToPick,"rotation",360,0);
            rotateAnimator.setDuration(900);
            rotateAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            rotateAnimator.setRepeatMode(ObjectAnimator.RESTART);
            rotateAnimator.setInterpolator(new LinearInterpolator());
            rotateAnimator.start();
        }

        void onClickForBot() {
            if (isAlive && isClickable && currentPlayerColor.equals(colour)) {
                diceValue = currentPlayerDice;
                currentPlayerDice = -1;
                for (Piece p : getPiecesByColor(colour)) {
                    p.inactiveState();
                }
                checkAdjustments(currBlock);
                move(diceValue);
                //Toast.makeText(MainActivity.this, x+"", Toast.LENGTH_SHORT).show();
            } else if (!isAlive && currentPlayerColor.equals(colour) && currentPlayerDice == 6) {
                currentPlayerDice = -1;
                for (Piece p : getPiecesByColor(colour)) {
                    p.inactiveState();
                }
                makeAlive();
            }
        }

        void makeAlive()
        {
            isAlive = true;
            currBlock = startPosition;
            piece.animate().translationX(x[startPosition]+pushXForPieces).translationY(y[startPosition]-pushYForPieces).setDuration(400).start(); // 16 75
            globalHandler.postDelayed(() -> {
                isDiceMovableExtraChance = true;
                if(!isBotPiece) { hintArrow.setVisibility(View.VISIBLE); }
                checkAdjustments(currBlock);
                if(currBlock>=24 && currBlock<=51) {
                    piece.setElevation(51-currBlock);
                } else if(currBlock>=64 && currBlock<=69) {
                    piece.setElevation(70-currBlock);
                } else {
                    piece.setElevation(currBlock);
                }
            },400);
            globalHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(isBotPiece) {
                        d.roll();
                    }
                }
            },550);
        }

        void die()
        {
            isAlive = false;
            isClickable = false;
            isReadyToEnterWinnerZone = false;
            checkAdjustments(currBlock);
            currWinnerBlock=0;
            numberOfSteps = 0;
            if(isSoundOn) {
                deathSound = MediaPlayer.create(MainActivity.this, R.raw.death);
                deathSound.start();
                deathSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        deathSound.release();
                    }
                });
            }


            Runnable r = new Runnable() {
                @Override
                public void run() {
                    if(currBlock!=startPosition)
                    {
                        if(currBlock==0) currBlock=51; else --currBlock;
                        piece.setTranslationX(x[currBlock]+pushXForPieces);
                        piece.setTranslationY(y[currBlock]-pushYForPieces);
                        globalHandler.postDelayed(this,20);
                    } else {
                        if(piece.getScaleX()<1.0f) {
                            piece.setScaleX(1.0f);
                            piece.setScaleY(1.0f);
                        }
                        piece.animate().translationX(defX).translationY(defY).setDuration(400).start();
                        globalHandler.removeCallbacks(this);
                    }
                }
            };
            globalHandler.post(r);
            //piece.animate().translationX(defX).translationY(defY).setDuration(400).start();
        }

        void activeState()
        {
            isClickable = true;
            rotateAnimator.start();
            readyToPick.setVisibility(View.VISIBLE);
            if(piece.getScaleX()<1.0f) {
                piece.setScaleX(0.95f);
                piece.setScaleY(0.95f);
            } else {
                piece.setScaleX(1.0f);
                piece.setScaleY(1.0f);
            }
        }

        void inactiveState()
        {
            isClickable = false;
            rotateAnimator.cancel();
            readyToPick.setVisibility(View.INVISIBLE);
            if(currBlock!=-1) {
                checkAdjustments(currBlock);
            }
        }

        void move(int n) {
            isClickable = false;
            if(stepSound!=null) {
                if(isSoundOn) {
                    if (!stepSound.isPlaying()) {
                        stepSound.seekTo(120);
                        stepSound.start();
                    } else if (stepSound.isPlaying()) {
                        stepSound.pause();
                        stepSound.seekTo(120);
                        stepSound.start();
                    }
                }
            }



            if (currBlock == -1) {
                currBlock = startPosition;
            }

            if(currBlock>=51)
                currBlock = 0;
            else
                currBlock++;

            if(currBlock==0) { checkAdjustments(51); } else { checkAdjustments(currBlock-1); }

            if(isReadyToEnterWinnerZone)
            {
                currBlock = winnerBlocks[currWinnerBlock];
                currWinnerBlock++;
            }

            if(currBlock==endPosition) { winnerBlocks = getWinnerBlocks(endPosition); isReadyToEnterWinnerZone = true; }

            piece.animate().translationX(x[currBlock] + pushXForPieces).translationY(y[currBlock] - pushYForPieces).setDuration(220).start();
            /*if(!isReadyToEnterWinnerZone)
            {
                piece.animate().translationX(x[currBlock] + pushXForPieces).translationY(y[currBlock] - pushYForPieces).setDuration(300).start();
            } else {
                piece.animate().translationX(x[winnerBlocks[currWinnerBlock]] + pushXForPieces).translationY(y[winnerBlocks[currWinnerBlock]] - pushYForPieces).setDuration(300).start();
            }*/

            PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f, 1.4f);
            PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f, 1.4f);
            ObjectAnimator popUpAnimator = ObjectAnimator.ofPropertyValuesHolder(piece, scaleX, scaleY);
            popUpAnimator.setDuration(100);
            scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.4f, 1.0f);
            scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.4f, 1.0f);
            ObjectAnimator popDownAnimator = ObjectAnimator.ofPropertyValuesHolder(piece, scaleX, scaleY);
            popDownAnimator.setDuration(90);

            /*ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1.0f, 0.0f);
            alphaAnimator.setDuration(duration);*/

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(popUpAnimator,popDownAnimator);
            animatorSet.start();




            globalHandler.postDelayed(() -> {
                if(n>1) {
                    int step = currBlock;
                    globalHandler.postDelayed( ()-> {
                        showStep(n,this.colour,x[step],y[step]);
                    },100);
                    numberOfSteps++;
                    move(n - 1);
                } else {
                    showStep(n,this.colour,x[currBlock],y[currBlock]);
                    isThisPlayerWon = false;
                    if(currBlock>=24 && currBlock<=51) {
                        piece.setElevation(51-currBlock);
                    } else if(currBlock>=64 && currBlock<=69) {
                        piece.setElevation(70-currBlock);
                    } else {
                        piece.setElevation(currBlock);
                    }
                    stepSound.pause();
                    numberOfSteps++;

                    boolean isDeadChanceAvailable = false;

                    if(safeSpots.contains(currBlock) && isSoundOn)
                    {
                        safeSound = MediaPlayer.create(MainActivity.this,R.raw.safe);
                        safeSound.start();
                        safeSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                safeSound.release();
                            }
                        });
                    } else if(currWinnerBlock>0) {
                        int temp = 0;
                        if(currWinnerBlock>5)
                        {
                            hasCompletedItsPurpose = true;
                            if(isSoundOn) {
                                pantaSound = MediaPlayer.create(MainActivity.this, R.raw.panta);
                                pantaSound.start();
                                pantaSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                        pantaSound.release();
                                    }
                                });
                            }

                            List<Piece> pieces = getPiecesByColor(this.colour);
                            for(Piece p: pieces) { if(p.hasCompletedItsPurpose) { temp++; } }

                            int pantaValue = 4;
                            if(gametype==3) { pantaValue = 1; }

                            if(temp>=pantaValue) { //4
                                isThisPlayerWon = true;
                                isDeadChanceAvailable = isDiceMovableExtraChance = false;
                                if(players.size()==2) {
                                    int x;
                                    if(currentPlayerIndex==0) { x=players.size()-1; } else { x=currentPlayerIndex-1; }
                                    if(gametype!=3) {
                                        globalHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                checkPantaAdjustments(colour, currBlock);
                                            }
                                        }, 50);
                                    }
                                    redHomeBlink.clearAnimation();
                                    redHomeBlink.setVisibility(GONE);
                                    mainDiceImageView.setVisibility(GONE);
                                    players.remove(x);
                                    if(gametype==2) {
                                        for(Piece p: pieces) { p.piece.setVisibility(GONE); }
                                        if(colour.equals("red") || colour.equals("yellow")) {
                                            if(isredyellowreadytowin) {
                                                setRedYellowTeamAsWinners();
                                            } else { isredyellowreadytowin = true; }
                                        } else {
                                            if(isbluegreenreadytowin) {
                                                stopEverything();
                                                setBlueGreenTeamAsWinners();
                                                ((TextView)findViewById(R.id.team1name1)).setText(player3name);
                                                ((TextView)findViewById(R.id.team1name2)).setText(player2name);
                                                ((TextView)findViewById(R.id.team2name1)).setText(player1name);
                                                ((TextView)findViewById(R.id.team2name2)).setText(player4name);
                                                showGameOverScreen();
                                                mainDiceImageView.setVisibility(GONE);
                                                hintArrow.setVisibility(GONE);
                                            } else { isbluegreenreadytowin = true; }
                                        }
                                        return;
                                    } else {
                                        setCurrentPlayerAsWinnerAtCurrentPosition(currentPlayerSelectedIndex, colour, currentPlayerName);
                                    }
                                    String name = players.get(0).name,colour = players.get(0).color;
                                    int loserplayerselectedindex = players.get(0).index;

                                    if(gametype==4) {
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        if(loserplayerselectedindex!=0) {
                                            botwins += 1;
                                            editor.putInt("botwins",botwins);
                                        } else {
                                            botloses += 1;
                                            editor.putInt("botloses",botloses);
                                        }
                                        ((TextView)findViewById(R.id.userwins)).setText((botwins+""));
                                        ((TextView)findViewById(R.id.userloses)).setText((botloses+""));

                                        editor.apply();
                                    }

                                    hideThisPlayerDiceBg(loserplayerselectedindex);
                                    if(nop==2) {
                                        wlistcrown2.setVisibility(View.INVISIBLE);
                                        wlistname2.setText(name);
                                        wlistpiece2.setImageDrawable(getPieceDrawableByColor(colour));
                                        winnerlistp3layout.setVisibility(GONE);
                                        winnerlistp4layout.setVisibility(GONE);
                                    } else if(nop==3) {
                                        //makeThisPlayerLoserBasedOnSelectedIndexNOP3(colour,loserplayerselectedindex);
                                        wlistcrown3.setVisibility(View.INVISIBLE);
                                        wlistname3.setText(getLoserNameBasedOnSelectedIndex(loserplayerselectedindex));
                                        wlistpiece3.setImageDrawable(getPieceDrawableByColor(colour));
                                        winnerlistp4layout.setVisibility(GONE);
                                    } else {
                                        wlistcrown4.setVisibility(View.INVISIBLE);
                                        wlistname4.setText(getLoserNameBasedOnSelectedIndex(loserplayerselectedindex));
                                        wlistpiece4.setImageDrawable(getPieceDrawableByColor(colour));
                                    }
                                    showGameOverScreen();
                                    return;
                                } else {
                                    int x;
                                    if (currentPlayerIndex == 0) {
                                        x = players.size() - 1;
                                    } else {
                                        x = currentPlayerIndex - 1;
                                    }
                                    currentWinnerPlayerIndex = currentPlayerIndex;
                                    //checkAdjustments(currBlock);
                                    if(gametype==2) {
                                        for(Piece p: pieces) { p.piece.setVisibility(GONE); }
                                        if(colour.equals("red") || colour.equals("yellow")) {
                                            if(isredyellowreadytowin) {
                                                setRedYellowTeamAsWinners();
                                            } else { isredyellowreadytowin = true; }
                                        } else {
                                            if(isbluegreenreadytowin) {
                                                stopEverything();
                                                setBlueGreenTeamAsWinners();
                                                ((TextView)findViewById(R.id.team1name1)).setText(player3name);
                                                ((TextView)findViewById(R.id.team1name2)).setText(player2name);
                                                ((TextView)findViewById(R.id.team2name1)).setText(player1name);
                                                ((TextView)findViewById(R.id.team2name2)).setText(player4name);
                                                showGameOverScreen();
                                                mainDiceImageView.setVisibility(GONE);
                                                hintArrow.setVisibility(GONE);
                                            } else { isbluegreenreadytowin = true; }
                                        }
                                    } else {
                                        setCurrentPlayerAsWinnerAtCurrentPosition(currentPlayerSelectedIndex, colour, currentPlayerName);
                                    }

                                    currentPlayerIndex = x;
                                    menuremoveplayersbtn.setVisibility(GONE);
                                    players.remove(currentPlayerIndex);
                                    if (currentPlayerIndex == players.size()) {
                                        currentPlayerIndex=0;
                                    }
                                    //Toast.makeText(MainActivity.this, currentPlayerIndex + "", Toast.LENGTH_SHORT).show();
                                    switchPlayers();
                                    if(gametype!=3) {
                                        globalHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                checkPantaAdjustments(colour,currBlock);
                                            }
                                        }, 50);
                                    }
                                    d.isDiceClickable = true;
                                    return;
                                }
                            }
                        }
                    } else {
                        isDeadChanceAvailable = checkDeath(this,currBlock);
                    }

                    globalHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(!isThisPlayerWon) { checkAdjustments(currBlock); }
                        }
                    },10);
                    //checkAdjustments(currBlock);

                    if(diceValue == 6 || currWinnerBlock>5 || isDeadChanceAvailable && !isThisPlayerWon) {
                        isDiceMovableExtraChance = true;
                        if(!isBotPiece) { hintArrow.setVisibility(View.VISIBLE); } else {
                            globalHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    d.roll();
                                }
                            },150);
                        }
                    } else {
                        switchPlayers();
                        d.isDiceClickable = true;
                    }
                }
            }, 200); //325
        }



        public boolean check(int diceValue) {
            if(diceValue == 6)
            {
                if(!isAlive || (numberOfSteps+diceValue)<57)
                {
                    activeState();
                    return true;
                } return false;
            } else if(isAlive && (numberOfSteps+diceValue)<57 && !hasCompletedItsPurpose && !isThisPlayerWon) {
                activeState();
                return true;
            } else {
                inactiveState();
                return false;
            }
        }
    }

    private void setBlueGreenTeamAsWinners() {
        ((ImageView)findViewById(R.id.imageView132)).setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.team1header,null));

        ((ImageView)findViewById(R.id.imageView137)).setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.team2header,null));

        ((ImageView)findViewById(R.id.imageView133)).setImageDrawable(getPieceDrawableByColor("blue"));
        ((ImageView)findViewById(R.id.imageView135)).setImageDrawable(getPieceDrawableByColor("green"));
        ((ImageView)findViewById(R.id.imageView138)).setImageDrawable(getPieceDrawableByColor("red"));
        ((ImageView)findViewById(R.id.imageView140)).setImageDrawable(getPieceDrawableByColor("yellow"));

        ((TextView)findViewById(R.id.team1name1)).setText(player3name);
        ((TextView)findViewById(R.id.team1name2)).setText(player2name);
        ((TextView)findViewById(R.id.team2name1)).setText(player1name);
        ((TextView)findViewById(R.id.team2name2)).setText(player4name);
    }

    private void setRedYellowTeamAsWinners() {
        stopEverything();
        showGameOverScreen();
        mainDiceImageView.setVisibility(GONE);
        hintArrow.setVisibility(GONE);

        ((ImageView)findViewById(R.id.imageView132)).setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.team2header,null));

        ((ImageView)findViewById(R.id.imageView137)).setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.team1header,null));

        ((ImageView)findViewById(R.id.imageView133)).setImageDrawable(getPieceDrawableByColor("red"));
        ((ImageView)findViewById(R.id.imageView135)).setImageDrawable(getPieceDrawableByColor("yellow"));
        ((ImageView)findViewById(R.id.imageView138)).setImageDrawable(getPieceDrawableByColor("blue"));
        ((ImageView)findViewById(R.id.imageView140)).setImageDrawable(getPieceDrawableByColor("green"));

        ((TextView)findViewById(R.id.team1name1)).setText(player1name);
        ((TextView)findViewById(R.id.team1name2)).setText(player4name);
        ((TextView)findViewById(R.id.team2name1)).setText(player3name);
        ((TextView)findViewById(R.id.team2name2)).setText(player2name);
    }

    private String getLoserNameBasedOnSelectedIndex(int loserplayerselectedindex) {
        switch (loserplayerselectedindex+1) {
            case 1: return player3name;
            case 2: return player1name;
            case 3: return player2name;
            case 4: return player4name;
        }
        return player3name;
    }

    private void showGameOverScreen() {
        congratulationslayout.setVisibility(View.VISIBLE);

        if(isSoundOn) {
            congratulationSound = MediaPlayer.create(this, R.raw.congratulations);
            congratulationSound.setLooping(true);
            congratulationSound.start();
        }

        ImageView imageView = findViewById(R.id.fireworksview);
        Glide.with(this)
                .asGif()
                .load(R.drawable.fireworksimg)
                .into(new DrawableImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if (resource instanceof GifDrawable) {
                            GifDrawable gifDrawable = (GifDrawable) resource;
                            gifDrawable.setLoopCount(GifDrawable.LOOP_FOREVER);
                            gifDrawable.start();
                        }
                    }
                }.getView());
    }

    private void makeThisPlayerLoserBasedOnSelectedIndexNOP3(String colour, int loserplayerselectedindex) {
        switch ((loserplayerselectedindex+1)) {
            case 1:
                if(nop==3) {
                    wlistcrown3.setVisibility(View.INVISIBLE);
                    wlistpiece3.setImageDrawable(getPieceDrawableByColor(colour));
                    wlistname3.setText(player3name);
                    wlistwinorlose1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.loser1, null));
                }
                hideThisPlayerDiceBg(1);
                break;
            case 2:
                if(nop==3) {
                    wlistcrown3.setVisibility(View.INVISIBLE);
                    wlistpiece3.setImageDrawable(getPieceDrawableByColor(colour));
                    wlistname3.setText(player1name);
                    wlistwinorlose1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.loser1, null));
                }
                hideThisPlayerDiceBg(2);
                break;
            case 3:
                if(nop==3) {
                    wlistcrown3.setVisibility(View.INVISIBLE);
                    wlistpiece3.setImageDrawable(getPieceDrawableByColor(colour));
                    wlistname3.setText(player2name);
                    wlistwinorlose1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.loser1, null));
                }
                hideThisPlayerDiceBg(3);
                break;
            case 4:
                if(nop==3) {
                    wlistcrown3.setVisibility(View.INVISIBLE);
                    wlistpiece3.setImageDrawable(getPieceDrawableByColor(colour));
                    wlistname3.setText(player4name);
                    wlistwinorlose1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.loser1, null));
                }
                hideThisPlayerDiceBg(4);
                break;
        }
    }

    private void setCurrentPlayerAsWinnerAtCurrentPosition(int currentPlayerSelectedIndex,String colour,String playername) {
        switch ((currentPlayerSelectedIndex+1)) {
            case 1:
                crownIndex1.setVisibility(View.VISIBLE);
                findViewById(R.id.winnerindexnumber1).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.winnerindexnumber1)).setImageDrawable(getWinnerNumberDrawableByNumber(currentWinnerPosition));
                if(nop!=2) {
                    addCurrentPlayerToCongratsList(colour, player3name, currentWinnerPosition);
                }
                else {
                    addCurrentPlayerToCongratsList(colour, playername, currentWinnerPosition);
                }

                hideThisPlayerDiceBg(1);
                break;
            case 2:
                crownIndex2.setVisibility(View.VISIBLE);
                findViewById(R.id.winnerindexnumber2).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.winnerindexnumber2)).setImageDrawable(getWinnerNumberDrawableByNumber(currentWinnerPosition));
                if(nop!=2) {
                    addCurrentPlayerToCongratsList(colour, player1name, currentWinnerPosition);
                }
                else {
                    addCurrentPlayerToCongratsList(colour, playername, currentWinnerPosition);
                }
                hideThisPlayerDiceBg(2);
                break;
            case 3:
                crownIndex3.setVisibility(View.VISIBLE);
                findViewById(R.id.winnerindexnumber3).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.winnerindexnumber3)).setImageDrawable(getWinnerNumberDrawableByNumber(currentWinnerPosition));
                if(nop!=2) {
                    addCurrentPlayerToCongratsList(colour, player2name, currentWinnerPosition);
                }
                else {
                    addCurrentPlayerToCongratsList(colour, playername, currentWinnerPosition);
                }
                hideThisPlayerDiceBg(3);
                break;
            case 4:
                crownIndex4.setVisibility(View.VISIBLE);
                findViewById(R.id.winnerindexnumber4).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.winnerindexnumber4)).setImageDrawable(getWinnerNumberDrawableByNumber(currentWinnerPosition));
                addCurrentPlayerToCongratsList(colour,playername,currentWinnerPosition);
                hideThisPlayerDiceBg(4);
                break;
        }
        currentWinnerPosition++;
    }

    private void addCurrentPlayerToCongratsList(String color,String name,int currentWinnerPosition) {
        switch (currentWinnerPosition) {
            case 1:
                wlistcrown1.setVisibility(View.VISIBLE);
                wlistcrown2.setVisibility(View.INVISIBLE);
                wlistpiece1.setImageDrawable(getPieceDrawableByColor(color));
                wlistname1.setText(name);
                wlistwinorlose1.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.winner1,null));
                break;
            case 2:
                wlistcrown2.setVisibility(View.VISIBLE);
                wlistcrown3.setVisibility(View.INVISIBLE);
                wlistpiece2.setImageDrawable(getPieceDrawableByColor(color));
                wlistname2.setText(name);
                wlistwinorlose2.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.winner1,null));
                break;
            case 3:
                wlistcrown3.setVisibility(View.VISIBLE);
                wlistcrown4.setVisibility(View.INVISIBLE);
                wlistpiece3.setImageDrawable(getPieceDrawableByColor(color));
                wlistname3.setText(name);
                wlistwinorlose3.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.winner1,null));
                break;
            case 4:
                wlistcrown4.setVisibility(View.VISIBLE);
                wlistcrown4.setVisibility(View.INVISIBLE);
                wlistpiece1.setImageDrawable(getPieceDrawableByColor(color));
                wlistname1.setText(name);
                wlistwinorlose4.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.loser1,null));
                break;
        }
    }

    Drawable getWinnerNumberDrawableByNumber(int x) {
        switch (x) {
            case 1:
                return ResourcesCompat.getDrawable(getResources(),R.drawable.winner1number,null);
            case 2:
                return ResourcesCompat.getDrawable(getResources(),R.drawable.winner2number,null);
            case 3:
                return ResourcesCompat.getDrawable(getResources(),R.drawable.winner3number,null);
            case 4:
                return ResourcesCompat.getDrawable(getResources(),R.drawable.winner4number,null);
        }
        return null;
    }

    Drawable getWinnerCrownDrawableByNumber(int x) {
        switch (x) {
            case 1:
                return ResourcesCompat.getDrawable(getResources(),R.drawable.listwinner1crown,null);
            case 2:
                return ResourcesCompat.getDrawable(getResources(),R.drawable.listwinner2crown,null);
            case 3:
                return ResourcesCompat.getDrawable(getResources(),R.drawable.listwinner3crown,null);
            case 4:
                return null;
        }
        return null;
    }

    private Drawable getStylishIconDrawableByColor(String colour) {
        switch(colour){
            case "red":
                return ResourcesCompat.getDrawable(getResources(),R.drawable.redpiecestylish,null);
            case "blue":
                return ResourcesCompat.getDrawable(getResources(),R.drawable.bluepiecestylish,null);
            case "green":
                return ResourcesCompat.getDrawable(getResources(),R.drawable.greenpiecestylish,null);
            case "yellow":
                return ResourcesCompat.getDrawable(getResources(),R.drawable.yellowpiecestylish,null);
        }
        return null;
    }

    void checkPantaAdjustments(String color,int targetBox) {
        int pantaBox=targetBox;
        boolean horizontal = (pantaBox >= 0 && pantaBox <= 4) || (pantaBox >= 18 && pantaBox <= 30) || (pantaBox >= 44 && pantaBox <= 51) || (pantaBox >= 52 && pantaBox <= 57) || (pantaBox >= 64 && pantaBox <= 69);
        boolean isWon = false;
        List<Piece> pairs = getPiecesByColor(color);
        for(Piece p : pairs) { if(p.hasCompletedItsPurpose) { isWon = true; } else {isWon = false; break;} }
        if(isWon) {
            if(horizontal) {
                float elevation = pairs.get(0).piece.getElevation();
                float extra = 4 * displayMetrics.density, yextra = 17 * displayMetrics.density;
                float xt = x[targetBox] + pushXForPieces, yt = y[targetBox];
                float scale = 100f / ((4) * 30 + 75);
                float gap = 100f - scale;
                //Toast.makeText(this, scale + "", Toast.LENGTH_SHORT).show();
                float space = blockSize - (pieceWidth * scale * 0.35f * 4 + pieceWidth * scale * 0.65f) / 2;
                if (space <= 0) {
                    space = 0;
                }
                space = 0;
                //scale = 0.70f;
                for (int i = 0; i < 4; i++) {
                    Piece p = pairs.get(i);
                    ConstraintLayout piece = p.piece;
                    piece.setElevation(elevation++);
                    piece.setScaleX(scale);
                    piece.setScaleY(scale);
                    piece.setTranslationX(xt - ((blockSize - (pieceWidth * scale)) / 2) + ((pieceWidth * scale * 0.30f) * i) - (extra * scale * (4 / 2)) + space);
                    if(normalPiece) { piece.setTranslationY(yt - pieceHeight / 2); } else { piece.setTranslationY(yt - (pieceHeight/2) / 2); }
                }
            } else {
                float elevation = pairs.get(0).piece.getElevation();
                float extra = 4 * displayMetrics.density, yextra = 8 * displayMetrics.density;
                float xt = x[targetBox] + pushXForPieces, yt = y[targetBox]-pushYForPieces;
                float scale = 100f / ((4) * 30 + 75);
                float gap = 100f - scale;
                //Toast.makeText(this, scale + "", Toast.LENGTH_SHORT).show();
                float space = blockSize - (pieceWidth * scale * 0.35f * 4 + pieceWidth * scale * 0.65f) / 2;
                if (space <= 0) {
                    space = 0;
                }
                space = 0;
                //scale = 0.70f;
                for (int i = 0; i < 4; i++) {
                    Piece p = pairs.get(i);
                    ConstraintLayout piece = p.piece;
                    piece.setElevation(elevation++);
                    piece.setScaleX(scale);
                    piece.setScaleY(scale);
                    piece.setTranslationX(xt);
                    if(normalPiece) {piece.setTranslationY(yt - ((blockSize - (pieceWidth * scale)) / 2) + ((pieceWidth * scale * 0.30f) * i)); }
                    else { piece.setTranslationY((yt - ((blockSize - (pieceWidth * scale)) / 2) + ((pieceWidth * scale * 0.30f) * i))-(pieceWidth * scale * 0.30f)); }
                }
            }
        }
    }

    private void checkAdjustments(int targetBox) {
        int xi = currentPlayerIndex;
        if(currentWinnerPlayerIndex!=-1) {
            xi = currentWinnerPlayerIndex;
            currentWinnerPlayerIndex = -1;
        }
        List<Piece> pairs = new ArrayList<>();
        boolean horizontal = (targetBox >= 0 && targetBox <= 4) || (targetBox >= 18 && targetBox <= 30) || (targetBox >= 44 && targetBox <= 51) || (targetBox >= 52 && targetBox <= 57) || (targetBox >= 64 && targetBox <= 69);
        for(int i = 0; i<players.size(); i++)
        {
            if(xi>players.size()-1) { continue; }
            String colour = players.get(xi).getColor();
            List<Piece> pieces = getPiecesByColor(colour);
            if(horizontal)
                {
                    for (int j = 3; j >= 0; j--) {
                        Piece p = pieces.get(j);
                        if (p.currBlock == targetBox && targetBox != -1 && p.isAlive) {
                            pairs.add(p);
                        }
                    }
                } else {
                    for(Piece p : pieces) {
                        if (p.currBlock == targetBox && targetBox != -1 && p.isAlive) {
                            pairs.add(p);
                        }
                    }
                }

            if(xi>=players.size()-1) { xi = 0; } else { xi++; }
        }
        int n = pairs.size();
        if(n>1) {
            if(horizontal) {
                float extra = 4 * displayMetrics.density, yextra = 17 * displayMetrics.density;
                float xt = x[targetBox] + pushXForPieces, yt = y[targetBox];
                float scale = 100f / ((n) * 30 + 75);
                float gap = 100f - scale;
                //Toast.makeText(this, scale + "", Toast.LENGTH_SHORT).show();
                float space = blockSize - (pieceWidth * scale * 0.35f * n + pieceWidth * scale * 0.65f) / 2;
                if (space <= 0) {
                    space = 0;
                }
                space = 0;
                //scale = 0.70f;
                for (int i = 0; i < n; i++) {
                    Piece p = pairs.get(i);
                    ConstraintLayout piece = p.piece;
                    piece.setScaleX(scale);
                    piece.setScaleY(scale);
                    piece.setTranslationX(xt - ((blockSize - (pieceWidth * scale)) / 2) + ((pieceWidth * scale * 0.30f) * i) - (extra * scale * (n / 2)) + space);
                    if(normalPiece) { piece.setTranslationY(yt - pieceHeight / 2); } else { piece.setTranslationY(yt - (pieceHeight/2) / 2); }
                }
            } else {
                float elevation = pairs.get(0).piece.getElevation();
                float extra = 4 * displayMetrics.density, yextra = 8 * displayMetrics.density;
                float xt = x[targetBox] + pushXForPieces, yt = y[targetBox]-pushYForPieces;
                float scale = 100f / ((n) * 30 + 75);
                float gap = 100f - scale;
                //Toast.makeText(this, scale + "", Toast.LENGTH_SHORT).show();
                float space = blockSize - (pieceWidth * scale * 0.35f * n + pieceWidth * scale * 0.65f) / 2;
                if (space <= 0) {
                    space = 0;
                }
                space = 0;
                //scale = 0.70f;
                for (int i = 0; i < n; i++) {
                    Piece p = pairs.get(i);
                    ConstraintLayout piece = p.piece;
                    piece.setElevation(elevation++);
                    piece.setScaleX(scale);
                    piece.setScaleY(scale);
                    piece.setTranslationX(xt);
                    if(normalPiece) {piece.setTranslationY(yt - ((blockSize - (pieceWidth * scale)) / 2) + ((pieceWidth * scale * 0.30f) * i)); }
                    else { piece.setTranslationY((yt - ((blockSize - (pieceWidth * scale)) / 2) + ((pieceWidth * scale * 0.30f) * i))-(pieceWidth * scale * 0.30f)); }
                }
            }
        } else if(pairs.size()==1) {
            Piece p = pairs.get(0);
            p.piece.setScaleX(1.0f);
            p.piece.setScaleY(1.0f);
            p.piece.setTranslationX(x[targetBox]+pushXForPieces);
            p.piece.setTranslationY(y[targetBox]-pushYForPieces);
        }
    }

    private boolean checkDeath(Piece killer, int targetBox) {
        int x = currentPlayerIndex;
        if(x>players.size()-1) { x = players.size()-1; }
        for(int i=0;i<players.size();i++)
        {
            String colour = players.get(x).getColor();
            if(!colour.equals(killer.colour))
            {
                List<Piece> pieces = getPiecesByColor(colour);
                for(Piece p : pieces)
                {
                    if(p.currBlock==targetBox)
                    {
                        if(gametype==2) {
                            if((killer.colour.equals("yellow") && p.colour.equals("red")) || (killer.colour.equals("red") && p.colour.equals("yellow")) || (killer.colour.equals("blue") && p.colour.equals("green")) || (killer.colour.equals("green") && p.colour.equals("blue")))
                            { continue; }
                        }
                        if(!safeSpots.contains(targetBox)) {
                            p.die();
                            return true;
                        }
                    }
                }
            }
            if(x>=players.size()-1) { x = 0; } else { x++; }
        }
        return false;
    }

    class Dice
    {
        ImageView diceImgView,tLeftImgColor,tRightImgColor,bLeftImgColor,bRightImgColor;
        AnimationDrawable diceAnimationDrawable;

        Handler diceHandler;
        boolean isRolling, isDiceClickable;

        ConstraintLayout tLeftLayout,tRightLayout,bLeftLayout,bRightLayout;

        String mainColor;

        int player = 0,numberOfPlayers;
        Dice(ImageView diceImgView,int nop,String color)
        {
            this.diceImgView = diceImgView;
            this.numberOfPlayers = nop;
            diceAnimationDrawable = new AnimationDrawable();
            diceAnimationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(),R.drawable.dice0001,null),50);
            diceAnimationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(),R.drawable.dice0002,null),50);
            diceAnimationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(),R.drawable.dice0003,null),50);
            diceAnimationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(),R.drawable.dice0004,null),50);
            diceAnimationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(),R.drawable.dice0005,null),50);
            diceAnimationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(),R.drawable.dice0006,null),50);
            diceAnimationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(),R.drawable.dice0007,null),50);
            diceAnimationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(),R.drawable.dice0008,null),50);

            tLeftLayout = findViewById(R.id.tleftdicebg);
            tRightLayout = findViewById(R.id.trightdicebg);
            bLeftLayout = findViewById(R.id.bleftdicebg);
            bRightLayout = findViewById(R.id.brightdicebg);

            tLeftImgColor = findViewById(R.id.tleftpieceimage);
            tRightImgColor = findViewById(R.id.trightpieceimage);
            bLeftImgColor = findViewById(R.id.bleftpieceimage);
            bRightImgColor = findViewById(R.id.brightpieceimage);

            mainColor = color;

            diceHandler = new Handler();
            isRolling = false;
            isDiceClickable = true;

            diceImgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isRolling && (isDiceClickable || isDiceMovableExtraChance)) {
                        hintArrow.setVisibility(GONE);
                        roll();
                    }
                }
            });

            if(nop==2) {
                menuremoveplayersbtn.setVisibility(GONE);
                tRightLayout.setVisibility(View.VISIBLE);
                bLeftLayout.setVisibility(View.VISIBLE);
                tLeftLayout.setVisibility(GONE);
                bRightLayout.setVisibility(GONE);

                switch (mainColor) {
                    case "red":
                        bLeftImgColor.setImageDrawable(getPieceDrawableByColor("red"));
                        tRightImgColor.setImageDrawable(getPieceDrawableByColor("yellow"));
                        break;
                    case "green":
                        bLeftImgColor.setImageDrawable(getPieceDrawableByColor("green"));
                        tRightImgColor.setImageDrawable(getPieceDrawableByColor("blue"));
                        break;
                    case "blue":
                        bLeftImgColor.setImageDrawable(getPieceDrawableByColor("blue"));
                        tRightImgColor.setImageDrawable(getPieceDrawableByColor("green"));
                        break;
                    case "yellow":
                        bLeftImgColor.setImageDrawable(getPieceDrawableByColor("yellow"));
                        tRightImgColor.setImageDrawable(getPieceDrawableByColor("red"));
                        break;
                }
            }
            else if(nop == 3) {
                rmp4bg.setAlpha(0.5f);
                rmpp4removeicon.setOnClickListener(null);
                tRightLayout.setVisibility(View.VISIBLE);
                bLeftLayout.setVisibility(View.VISIBLE);
                tLeftLayout.setVisibility(View.VISIBLE);
                bRightLayout.setVisibility(GONE);
                switch (mainColor) {
                    case "red":
                        bLeftImgColor.setImageDrawable(getPieceDrawableByColor("green")); //3
                        tLeftImgColor.setImageDrawable(getPieceDrawableByColor("yellow")); //1
                        tRightImgColor.setImageDrawable(getPieceDrawableByColor("blue")); //2
                        break;
                    case "green":
                        bLeftImgColor.setImageDrawable(getPieceDrawableByColor("yellow")); //2
                        tLeftImgColor.setImageDrawable(getPieceDrawableByColor("blue")); //3
                        tRightImgColor.setImageDrawable(getPieceDrawableByColor("red")); //1

                        break;
                    case "blue":
                        bLeftImgColor.setImageDrawable(getPieceDrawableByColor("red")); //3
                        tLeftImgColor.setImageDrawable(getPieceDrawableByColor("green")); //2
                        tRightImgColor.setImageDrawable(getPieceDrawableByColor("yellow")); //1
                        break;
                    case "yellow":
                        bLeftImgColor.setImageDrawable(getPieceDrawableByColor("blue")); //2
                        tLeftImgColor.setImageDrawable(getPieceDrawableByColor("red")); //1
                        tRightImgColor.setImageDrawable(getPieceDrawableByColor("green")); //3
                        break;
                }
            }
            else {
                tRightLayout.setVisibility(View.VISIBLE);
                bLeftLayout.setVisibility(View.VISIBLE);
                tLeftLayout.setVisibility(View.VISIBLE);
                bRightLayout.setVisibility(View.VISIBLE);
                switch (mainColor) {
                    case "red":
                        bLeftImgColor.setImageDrawable(getPieceDrawableByColor("red")); //3
                        tLeftImgColor.setImageDrawable(getPieceDrawableByColor("green")); //2
                        tRightImgColor.setImageDrawable(getPieceDrawableByColor("yellow")); //1
                        bRightImgColor.setImageDrawable(getPieceDrawableByColor("blue")); //4
                        break;
                    case "green":
                        bLeftImgColor.setImageDrawable(getPieceDrawableByColor("green")); //3
                        tLeftImgColor.setImageDrawable(getPieceDrawableByColor("yellow")); //1
                        tRightImgColor.setImageDrawable(getPieceDrawableByColor("blue")); //2
                        bRightImgColor.setImageDrawable(getPieceDrawableByColor("red")); //4
                        break;
                    case "blue":
                        bLeftImgColor.setImageDrawable(getPieceDrawableByColor("blue")); //3
                        tLeftImgColor.setImageDrawable(getPieceDrawableByColor("red")); //2
                        tRightImgColor.setImageDrawable(getPieceDrawableByColor("green")); //1
                        bRightImgColor.setImageDrawable(getPieceDrawableByColor("yellow")); //4
                        break;
                    case "yellow":
                        bLeftImgColor.setImageDrawable(getPieceDrawableByColor("yellow")); //2
                        tLeftImgColor.setImageDrawable(getPieceDrawableByColor("blue")); //1
                        tRightImgColor.setImageDrawable(getPieceDrawableByColor("red")); //3
                        bRightImgColor.setImageDrawable(getPieceDrawableByColor("green")); //4
                        break;
                }
            }
        }

        void roll() {
            isRolling = true;
            isDiceClickable = false;
            isDiceMovableExtraChance = false;
            if(isSoundOn) {
                if (diceRollSound.isPlaying()) {
                    diceRollSound.pause();
                }
                diceRollSound.seekTo(5);
                diceRollSound.start();
            }
            mainDiceImageView.setImageDrawable(diceAnimationDrawable);
            diceAnimationDrawable.setOneShot(true);
            diceAnimationDrawable.start();
            diceHandler.postDelayed(() -> {
                int ch = (int) Math.ceil(Math.random()*6);
                String number = editText.getText().toString();
                try {
                    int n = Integer.parseInt(number);
                    ch = n;
                } catch (Exception e) {}
                switch (ch) {
                    case 1:
                        mainDiceImageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice1, null));
                        break;
                    case 2:
                        mainDiceImageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice2, null));
                        break;
                    case 3:
                        mainDiceImageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice3, null));
                        break;
                    case 4:
                        mainDiceImageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice4, null));
                        break;
                    case 5:
                        mainDiceImageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice5, null));
                        break;
                    case 6:
                        mainDiceImageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice6, null));
                        break;
                }
                isRolling = false;

                currentPlayerDice = ch;

                List<Piece> pieces = getPiecesByColor(currentPlayerColor);
                int x;
                if(currentPlayerIndex==0) { x=players.size()-1; } else { x=currentPlayerIndex-1; }
                Player currentPlayer = players.get(x);
                //Toast.makeText(MainActivity.this, currentPlayer.color+"", Toast.LENGTH_SHORT).show();

                 int chances = 0;
                 int autoMoveBlock = -1;

                 List<Piece> movablePieces = new ArrayList<>();

                for(Piece p : pieces)
                {
                    if(p.check(ch))
                    {
                        if(chances==0) {
                            autoMoveBlock = p.currBlock;
                            autoMove = p;
                        }
                        else if(autoMoveBlock!=-1 && autoMoveBlock==p.currBlock) {

                        }
                        else {
                            autoMove = null;
                        }
                        if(currentPlayer.isBot) { movablePieces.add(p); }
                        ++chances;
                    }
                }

                currentPlayer.chances = chances;

                if(autoMove!=null) {

                    if(autoMove.isAlive && autoMove.isClickable) {
                        autoMove.diceValue = currentPlayerDice;
                        currentPlayerDice = -1;
                        for(Piece p : pieces) { p.inactiveState(); }
                        checkAdjustments(autoMove.currBlock);
                        autoMove.move(ch);
                        autoMove=null;
                        //Toast.makeText(MainActivity.this, x+"", Toast.LENGTH_SHORT).show();
                    }
                    else if(!autoMove.isAlive && currentPlayerDice==6) {
                        currentPlayerDice = -1;
                        for(Piece p : pieces) { p.inactiveState(); }
                        autoMove.makeAlive();
                    }
                } else if(currentPlayer.isBot && chances>0) {
                    Piece bestMovablePiece = movablePieces.get(0);
                    int bestKillingTargetSteps = 0;
                    int safetomovepoints = 15;
                    int pantapriority=0;
                    int maxprioritytoputinsafeplace = 0;
                    int safePoints = 0;
                    int y = currentPlayerIndex;

                    // choosing one with max steps blindly for faster panta minimum priority
                    int maxsteps = 0;
                    for(Piece p : movablePieces) {
                        if(p.isAlive && p.numberOfSteps>=maxsteps && !p.hasCompletedItsPurpose) {
                            maxsteps = p.numberOfSteps;
                            bestMovablePiece = p;
                        }
                    }

                    // making pieces alive minimum+0.5 priority
                    if(ch==6) {
                    for(Piece p : pieces) {
                        if(!p.isAlive) {
                            bestMovablePiece = p;
                            break;
                        }
                    } }


                    // panta chesing purpose min+1 priority
                    for(Piece p : movablePieces) {
                        if(p.isReadyToEnterWinnerZone) {
                            int thispantapriority = p.currWinnerBlock+ch;
                            if(thispantapriority>pantapriority) {
                                pantapriority = thispantapriority;
                                bestMovablePiece = p;
                            }
                        }
                    }

                    // moving to safe spots medium
                    for(Piece p : movablePieces) {
                        if(safeSpots.contains(p.currBlock+ch) && p.isAlive)
                        {
                            if(p.numberOfSteps>maxprioritytoputinsafeplace) {
                                maxprioritytoputinsafeplace = p.numberOfSteps;
                                bestMovablePiece = p;
                            }
                        }
                    }


                    // not going ahead for risky moves & running away from dangerous places max-1 priority
                    y = x;
                    for (int i = 0; i < players.size(); i++) {
                        String colour = players.get(y).getColor();
                        if (!colour.equals(currentPlayerColor)) {
                            List<Piece> enemypieces = getPiecesByColor(colour);
                            Toast.makeText(MainActivity.this, colour, Toast.LENGTH_SHORT).show();
                            for (Piece tp : enemypieces) {
                                if (tp.isAlive) {
                                    for (Piece mp : movablePieces) {
                                        if (mp.isAlive) {
                                            int dangerdiff = mp.currBlock - tp.currBlock;
                                            int safetypoints = 10;
                                            if (safeSpots.contains(mp.currBlock)) {
                                                safetypoints = 15;
                                            }
                                            /*if (mp.currBlock == tp.currBlock && safeSpots.contains(mp.currBlock)) {
                                                if(bestMovablePiece.numberOfSteps<=mp.numberOfSteps) { safetypoints = 15; } else { safetypoints = 9; }
                                            } else*/ if (mp.currBlock >= 0 && mp.currBlock <= 10 && (tp.currBlock >= 51 - (10 - mp.currBlock))) {
                                                if (tp.currBlock >= 51 - (10 - mp.currBlock)) {
                                                    safetypoints -= 10 - mp.currBlock;
                                                }
                                            } else if (tp.currBlock >= 0 && (tp.currBlock >= mp.currBlock - 10 && tp.currBlock < mp.currBlock)) {
                                                safetypoints -= mp.currBlock - tp.currBlock;
                                            } else if (dangerdiff > 10) {
                                                if (safeSpots.contains(mp.currBlock)) {
                                                    if (mp.numberOfSteps < bestMovablePiece.numberOfSteps) {
                                                        safetypoints = 9;
                                                    } else {
                                                        safetypoints = 15;
                                                    }
                                                }
                                            } else if (dangerdiff <= -10) {
                                                if (dangerdiff + ch <= 0) {
                                                    if (safeSpots.contains(mp.currBlock) && safeSpots.contains(tp.currBlock)) {
                                                        safetypoints = 15;
                                                    } else if (!safeSpots.contains(mp.currBlock) && !safeSpots.contains(tp.currBlock)) {
                                                        safetypoints = 5;
                                                    } else if (!safeSpots.contains(mp.currBlock) && safeSpots.contains(tp.currBlock)) {
                                                        safetypoints = 15;
                                                    } else if (safeSpots.contains(mp.currBlock) && !safeSpots.contains(tp.currBlock)) {
                                                        safetypoints = 9;
                                                    }
                                                }
                                            }
                                        if (safetypoints < safetomovepoints) {
                                            bestMovablePiece = mp;
                                            safetomovepoints = safetypoints;
                                        }
                                    }
                                }
                                }
                            }
                            if (y >= (players.size() - 1)) {
                                y = 0;
                            } else {
                                y++;
                            }
                        }
                    }

                    // killing the best piece max priority
                    y = x;
                    for (int i = 0; i < players.size(); i++) {
                        String color = players.get(y).getColor();
                        if (!color.equals(currentPlayerColor)) {
                            List<Piece> enemypieces = getPiecesByColor(color);
                            for (Piece tp : enemypieces) {
                                if (tp.isAlive && !safeSpots.contains(tp.currBlock)) {
                                    for (Piece mp : movablePieces) {
                                        if (tp.currBlock == (mp.currBlock+ch)) {
                                            if (tp.numberOfSteps > bestKillingTargetSteps) {
                                                bestKillingTargetSteps = tp.numberOfSteps;
                                                bestMovablePiece = mp;  // should not be commented at deployment stage
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (y >= (players.size() - 1)) {
                            y = 0;
                        } else {
                            y++;
                        }
                    }
                    //Toast.makeText(MainActivity.this, bestMovablePiece.colour+","+bestMovablePiece.startPosition, Toast.LENGTH_SHORT).show();
                    bestMovablePiece.onClickForBot();
                } else if (chances == 0) {
                        diceHandler.postDelayed(() -> {
                            switchPlayers();
                            isDiceClickable = true;
                        }, 500);
                    }
            }, 350);
            }
    }

    private Drawable getPieceDrawableByColor(String color) {
        switch (color) {
            case "red":
                if (normalPiece) {
                    return ResourcesCompat.getDrawable(getResources(), R.drawable.redpiece, null);
                } else {
                    return getStylishIconDrawableByColor(color);
                }
            case "green":
                if (normalPiece) {
                    return ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiece, null);
                } else {
                    return getStylishIconDrawableByColor(color);
                }
            case "blue":
                if (normalPiece) {
                    return ResourcesCompat.getDrawable(getResources(), R.drawable.bluepiece, null);
                } else {
                    return getStylishIconDrawableByColor(color);
                }
            case "yellow":
                if (normalPiece) {
                    return ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiece, null);
                } else {
                    return getStylishIconDrawableByColor(color);
                }
        } return null;
    }

    private List<Piece> getPiecesByColor(String currentPlayerColor) {
        switch(currentPlayerColor)
        {
            case "red":
                return rp;
            case "green":
                return gp;
            case "blue":
                return bp;
            case "yellow":
                return yp;
        }
        return rp;
    }

    class Player {
        int position;
        String color;
        int index;
        String name;
        boolean isBot;
        TextView playerNameTextView;

        int chances = 0;

        public int getPosition() {
            return position;
        }

        public String getColor() {
            return color;
        }

        public int getDiceValue() {
            return diceValue;
        }

        int diceValue;

        public Player(int position, String color,String playerName,boolean isBot,int index) {
            this.position = position;
            this.color = color;
            this.name = playerName;
            this.index = index;
            this.isBot = isBot;
            playerNameTextView = getPlayerNameByPosition(this.position);
            if(this.isBot) {
                //name = "Bot";
                //playerNameTextView.setText(name);
            }
        }
        public int getIndex() {
            return index;
        }

        void setActive() {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(((int)(pxWidth*0.4)),((int)(pxWidth*0.4)));
            redHomeBlink.setLayoutParams(lp);

            //lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            if(position == 1)
            {    lp.addRule(RelativeLayout.ALIGN_TOP,R.id.imageView); lp.addRule(RelativeLayout.ALIGN_LEFT,R.id.imageView); }
            else if(position == 2)
            {    lp.addRule(RelativeLayout.ALIGN_TOP,R.id.imageView); lp.addRule(RelativeLayout.ALIGN_RIGHT,R.id.imageView); }
            else if(position == 3)
            {    lp.addRule(RelativeLayout.ALIGN_BOTTOM,R.id.imageView); lp.addRule(RelativeLayout.ALIGN_LEFT,R.id.imageView);}
            else if(position == 4)
            {    lp.addRule(RelativeLayout.ALIGN_BOTTOM,R.id.imageView); lp.addRule(RelativeLayout.ALIGN_RIGHT,R.id.imageView);}

            redHomeBlink.setLayoutParams(lp);

            Animation blink = AnimationUtils.loadAnimation(MainActivity.this, R.anim.blinkanimation);
            redHomeBlink.startAnimation(blink);
        }


    }

    private TextView getPlayerNameByPosition(int position) {
        switch (position)
        {
            case 1:
                return playername1;
            case 2:
                return playername2;
            case 3:
                return playername3;
            case 4:
                return playername4;
            default:
                return playername1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_main);
        //setContentView(new GameCanvas(this));
        Objects.requireNonNull(getSupportActionBar()).hide();

        initViews();

        globalHandler = new Handler();

        ObjectAnimator animator = ObjectAnimator.ofFloat(hintArrow, "translationX", -20, 20);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setDuration(250);
        animator.start();


        boolean isPLayer1Bot, isPLayer2Bot, isPLayer3Bot, isPLayer4Bot;

        Bundle extras = getIntent().getExtras();
        nop = extras.getInt("nop");
        String color = extras.getString("color");
        player1name = extras.getString("player1name");
        player2name = extras.getString("player2name");
        player3name = extras.getString("player3name");
        player4name = extras.getString("player4name");

        player1color = extras.getString("player1color");
        player2color = extras.getString("player2color");
        player3color = extras.getString("player3color");
        player4color = extras.getString("player4color");

        isPLayer1Bot = extras.getBoolean("player1bot");
        isPLayer2Bot = extras.getBoolean("player2bot");
        isPLayer3Bot = extras.getBoolean("player3bot");
        isPLayer4Bot = extras.getBoolean("player4bot");

        normalPiece = extras.getBoolean("normalPiece");

        gametype = extras.getInt("type"); // 1.classic , 2.teamup , 3.quick , 4.computer

        playername1.setText(player1name);
        playername2.setText(player2name);
        playername3.setText(player3name);
        playername4.setText(player4name);

        reddicevalue = greendicevalue = bluedicevalue = yellowdicevalue = 0;

        int length = dy.length;
        for (int i = 0; i < dy.length; i++) {
            x[i] = (float) ((pxWidth / 100) * dx[i]);
            y[i] = (float) ((pxWidth / 100) * dy[i]);
        }

        float total = onePercentWidth * 40;
        float homeCircleWidth = (float) ((total * 0.64) * 0.25);
        float pieceBlockSize = (float) (pxWidth * 0.065);
        float lifty = (float) (24.5 * displayMetrics.density);
        pieceWidth = 34 * displayMetrics.density;
        pieceHeight = 41 * displayMetrics.density;
        float pushx;
        if (homeCircleWidth >= pieceWidth) {
            pushx = (float) ((homeCircleWidth - pieceWidth) / 2);
        } else {
            pushx = (float) -((pieceWidth - homeCircleWidth) / 2);
        }
        if (pieceBlockSize >= pieceWidth) {
            pushXForPieces = (float) ((pieceBlockSize - pieceWidth) / 2);
        } else {
            pushXForPieces = (float) -((pieceWidth - pieceBlockSize) / 2);
        }
        pushYForPieces = lifty;
        if (!normalPiece) {
            pushx /= 1.5;
            lifty /= 3;
            pushXForPieces = pushx;
            pushYForPieces = lifty;
        }
        float base = (float) pxWidth - total;

        //

        crownIndex1.getLayoutParams().height = (int) total;
        crownIndex2.getLayoutParams().height = (int) total;
        crownIndex3.getLayoutParams().height = (int) total;
        crownIndex4.getLayoutParams().height = (int) total;

        p1exitbox.getLayoutParams().height = (int) (total * 0.985);
        p2exitbox.getLayoutParams().height = (int) (total * 0.985);
        p3exitbox.getLayoutParams().height = (int) (total * 0.985);
        p4exitbox.getLayoutParams().height = (int) (total * 0.985);
        p1exitbox.getLayoutParams().width = (int) (total * 0.985);
        p2exitbox.getLayoutParams().width = (int) (total * 0.985);
        p3exitbox.getLayoutParams().width = (int) (total * 0.985);
        p4exitbox.getLayoutParams().width = (int) (total * 0.985);

        if(gametype!=4) {
            playername1.getLayoutParams().width = (int) (total);
            playername1.getLayoutParams().height = (int) (total);
            playername1.setRotation(90);
            playername1.setGravity(Gravity.BOTTOM);
            playername1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            ((ConstraintLayout.LayoutParams)playername1.getLayoutParams()).topMargin = (int) (30 * displayMetrics.density);

            playername2.setRotation(180);
            ((ConstraintLayout.LayoutParams)playername2.getLayoutParams()).topMargin =  (int) (30 * displayMetrics.density);

            playername4.getLayoutParams().width = (int) (total);
            playername4.getLayoutParams().height = (int) (total);
            playername4.setRotation(-90);
            playername4.setGravity(Gravity.BOTTOM);
            playername4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }


        pos1[0][0] = (float) (total * 0.18 + ((total * 0.64) * 0.12)) + pushx;
        pos1[0][1] = (float) (total * 0.18 + ((total * 0.64) * 0.12) - lifty);
        double corDist = total * 0.18 + ((total * 0.64) * 0.12) + ((total * 0.64) * 0.25);
        pos1[1][0] = (float) (total - corDist + pushx);
        pos1[1][1] = (float) pos1[0][1];
        pos1[2][0] = (float) (total * 0.18 + ((total * 0.64) * 0.12) + pushx);
        pos1[2][1] = (float) (total - corDist - lifty);
        pos1[3][0] = (float) pos1[1][0];
        pos1[3][1] = (float) (total - corDist - lifty);

        pos2[0][0] = (float) base + pos1[2][0];
        pos2[0][1] = (float) pos1[0][1];
        pos2[1][0] = (float) base + (pos1[1][0]);
        pos2[1][1] = (float) pos1[0][1];
        pos2[2][0] = (float) base + pos1[2][0];
        pos2[2][1] = (float) (total - corDist - lifty);
        pos2[3][0] = (float) base + (pos1[1][0]);
        pos2[3][1] = (float) (total - corDist - lifty);

        pos3[0][0] = (float) pos1[2][0];
        pos3[0][1] = (float) base + pos1[0][1];
        pos3[1][0] = (float) pos1[1][0];
        pos3[1][1] = (float) base + pos1[0][1];
        pos3[2][0] = (float) pos1[2][0];
        pos3[2][1] = (float) (base + total - corDist - lifty);
        pos3[3][0] = (float) pos1[1][0];
        pos3[3][1] = (float) (base + total - corDist - lifty);

        pos4[0][0] = (float) base + pos1[2][0];
        pos4[0][1] = (float) base + pos1[0][1];
        pos4[1][0] = (float) base + pos1[1][0];
        pos4[1][1] = (float) base + pos1[0][1];
        pos4[2][0] = (float) base + pos1[2][0];
        pos4[2][1] = (float) (base + total - corDist - lifty);
        pos4[3][0] = (float) base + pos1[1][0];
        pos4[3][1] = (float) (base + total - corDist - lifty);

        if (nop == 2) {
            winnerlistp3layout.setVisibility(GONE);
            winnerlistp4layout.setVisibility(GONE);
            wlistwinorlose2.setImageDrawable(getResources().getDrawable(R.drawable.loser1));
            player1name = player3name;
            player2name = player2name;
            switch (color) {
                case "red":
                    ludoBoard.setRotation(0);
                    createPieces("red", 3, player1name, false);
                    createPieces("yellow", 2, player2name, isPLayer2Bot);
                    break;
                case "blue":
                    ludoBoard.setRotation(90);
                    createPieces("blue", 3, player1name, false);
                    createPieces("green", 2, player2name, isPLayer2Bot);
                    break;
                case "green":
                    ludoBoard.setRotation(270);
                    createPieces("green", 3, player1name, false);
                    createPieces("blue", 2, player2name, isPLayer2Bot);
                    break;
                case "yellow":
                    ludoBoard.setRotation(180);
                    createPieces("yellow", 3, player1name, false);
                    createPieces("red", 2, player2name, isPLayer2Bot);
                    break;
            }
        } else if (nop == 3) {
            addPiecesToRmpLayout(color, "Player 4", 4);
            player4color = color;
            switch (color) {
                case "red":
                    ludoBoard.setRotation(270);
                    createPieces("green", 3, player1name, isPLayer1Bot);
                    player1color = "green";
                    createPieces("yellow", 1, player2name, isPLayer2Bot);
                    player2color = "yellow";
                    createPieces("blue", 2, player3name, isPLayer3Bot);
                    player3color = "blue";
                    break;
                case "green":
                    ludoBoard.setRotation(180);
                    createPieces("yellow", 3, player1name, isPLayer1Bot);
                    player1color = "yellow";
                    createPieces("blue", 1, player2name, isPLayer2Bot);
                    player2color = "blue";
                    createPieces("red", 2, player3name, isPLayer3Bot);
                    player3color = "red";
                    break;
                case "blue": // 0
                    ludoBoard.setRotation(0);
                    createPieces("red", 3, player1name, isPLayer1Bot);
                    player1color = "red";
                    createPieces("green", 1, player2name, isPLayer2Bot);
                    player2color = "green";
                    createPieces("yellow", 2, player3name, isPLayer3Bot);
                    player3color = "yellow";
                    break;
                case "yellow":// 90
                    ludoBoard.setRotation(90);
                    createPieces("blue", 3, player1name, isPLayer1Bot);
                    player1color = "blue";
                    createPieces("red", 1, player2name, isPLayer2Bot);
                    player2color = "red";
                    createPieces("green", 2, player3name, isPLayer3Bot);
                    player3color = "green";
                    break;
            }
        } else {
            //Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
            switch (color) {
                case "red":
                    ludoBoard.setRotation(0);
                    createPieces("red", 3, player1name, isPLayer1Bot);
                    player1color = "red";
                    createPieces("green", 1, player2name, isPLayer2Bot);
                    player2color = "green";
                    createPieces("yellow", 2, player3name, isPLayer3Bot);
                    player3color = "yellow";
                    createPieces("blue", 4, player4name, isPLayer4Bot);
                    player4color = "blue";
                    break;
                case "blue":
                    ludoBoard.setRotation(90);
                    createPieces("blue", 3, player1name, isPLayer1Bot);
                    player1color = "blue";
                    createPieces("red", 1, player2name, isPLayer2Bot);
                    player2color = "red";
                    createPieces("green", 2, player3name, isPLayer3Bot);
                    player3color = "green";
                    createPieces("yellow", 4, player4name, isPLayer4Bot);
                    player4color = "yellow";
                    break;
                case "green":
                    ludoBoard.setRotation(270);
                    createPieces("green", 3, player1name, isPLayer1Bot);
                    player1color = "green";
                    createPieces("yellow", 1, player2name, isPLayer2Bot);
                    player2color = "yellow";
                    createPieces("blue", 2, player3name, isPLayer3Bot);
                    player3color = "blue";
                    createPieces("red", 4, player4name, isPLayer4Bot);
                    player4color = "red";
                    break;
                case "yellow":
                    ludoBoard.setRotation(180);
                    createPieces("yellow", 3, player1name, isPLayer1Bot);
                    player1color = "yellow";
                    createPieces("blue", 1, player2name, isPLayer2Bot);
                    player2color = "blue";
                    createPieces("red", 2, player3name, isPLayer3Bot);
                    player3color = "red";
                    createPieces("green", 4, player4name, isPLayer4Bot);
                    player4color = "green";
                    break;
                default:
                    ludoBoard.setRotation(0);
                    createPieces("blue", 3, player4name, isPLayer4Bot);
                    player1color = "blue";
                    createPieces("red", 1, player1name, isPLayer1Bot);
                    player2color = "red";
                    createPieces("green", 2, player2name, isPLayer2Bot);
                    player3color = "green";
                    createPieces("yellow", 4, player3name, isPLayer3Bot);
                    player4color = "yellow";
            }
        }
        View.OnTouchListener clickEffect = new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ((ImageView) v).setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color, null), PorterDuff.Mode.SRC_ATOP);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        ((ImageView) v).clearColorFilter();
                        break;
                }
                return false;
            }
        };

        ingamemenubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ingamemenuitemslayout.getVisibility() == View.VISIBLE) {
                    ingamemenuitemslayout.setVisibility(GONE);
                } else {
                    ingamemenuitemslayout.setVisibility(View.VISIBLE);
                }
            }
        });

        menuremoveplayersbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingamemenuitemslayout.setVisibility(GONE);
                ingamermplayout.setScaleX(0.0f);
                ingamermplayout.setScaleY(0.0f);
                ingamermplayout.setVisibility(View.VISIBLE);
                ingamermplayout.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ingamermplayout.animate().setListener(null);
                    }
                }).start();
            }
        });

        menuexitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingamemenuitemslayout.setVisibility(GONE);
                quitgamelayout.setScaleX(0.0f);
                quitgamelayout.setScaleY(0.0f);
                quitgamelayout.setVisibility(View.VISIBLE);
                quitgamelayout.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        quitgamelayout.animate().setListener(null);
                    }
                }).start();
            }
        });

        // exit layout views
        quitgamelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        ingameyesbtn.setOnTouchListener(clickEffect);
        ingamenobtn.setOnTouchListener(clickEffect);
        ingamesoundbtn.setOnTouchListener(clickEffect);
        ingamemusicbtn.setOnTouchListener(clickEffect);

        ingameyesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopEverything();
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                overridePendingTransition(0,0);
                finish();
            }
        });

        ingamenobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quitgamelayout.animate().scaleX(0.0f).scaleY(0.0f).setInterpolator(new AnticipateInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        quitgamelayout.setVisibility(GONE);
                    }
                }).start();
            }
        });

        ingamesoundbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSoundOn) {
                    isSoundOn = false;
                    sharedPreferences.edit().putBoolean("sound",false).apply();
                    ingamesoundbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.soundoff,null));
                    if(congratulationSound!=null) { if(congratulationSound.isPlaying()) { congratulationSound.pause(); } }
                } else {
                    isSoundOn = true;
                    sharedPreferences.edit().putBoolean("sound",true).apply();
                    ingamesoundbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.soundon,null));
                    if(congratulationSound!=null) { if(!congratulationSound.isPlaying()) { congratulationSound.start(); } }
                }
            }
        });

        ingamemusicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isMusicOn) {
                    isMusicOn = false;
                    sharedPreferences.edit().putBoolean("music",false).apply();
                    ingamemusicbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.musicoff,null));
                } else {
                    isMusicOn = true;
                    sharedPreferences.edit().putBoolean("music",true).apply();
                    ingamemusicbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.musicon,null));
                }
            }
        });

        // rmplayout views
        rmpbackbtn.setOnTouchListener(clickEffect);
        rmpmenubtn.setOnTouchListener(clickEffect);
        ingamermplayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        confirmrmplayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        String finalPlayer1color = player1color;
        rmpp1removeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmrmplayout.setScaleX(0.0f);
                confirmrmplayout.setScaleY(0.0f);
                confirmrmplayout.setVisibility(View.VISIBLE);
                confirmrmplayout.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        confirmrmplayout.animate().setListener(null);
                    }
                }).start();
                selectedconfirmrmpplayerindex = 1;
                selectedrmppiece.setImageDrawable(getPieceDrawableByColor(finalPlayer1color));
            }
        });

        String finalPlayer2color = player2color;
        rmpp2removeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmrmplayout.setScaleX(0.0f);
                confirmrmplayout.setScaleY(0.0f);
                confirmrmplayout.setVisibility(View.VISIBLE);
                confirmrmplayout.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        confirmrmplayout.animate().setListener(null);
                    }
                }).start();
                selectedconfirmrmpplayerindex = 2;

                selectedrmppiece.setImageDrawable(getPieceDrawableByColor(finalPlayer2color));
            }
        });

        String finalPlayer3color = player3color;
        rmpp3removeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmrmplayout.setScaleX(0.0f);
                confirmrmplayout.setScaleY(0.0f);
                confirmrmplayout.setVisibility(View.VISIBLE);
                confirmrmplayout.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        confirmrmplayout.animate().setListener(null);
                    }
                }).start();
                selectedconfirmrmpplayerindex = 3;
                selectedrmppiece.setImageDrawable(getPieceDrawableByColor(finalPlayer3color));
            }
        });

        String finalPlayer4color = player4color;
        rmpp4removeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmrmplayout.setScaleX(0.0f);
                confirmrmplayout.setScaleY(0.0f);
                confirmrmplayout.setVisibility(View.VISIBLE);
                confirmrmplayout.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        confirmrmplayout.animate().setListener(null);
                    }
                }).start();
                selectedconfirmrmpplayerindex = 4;
                selectedrmppiece.setImageDrawable(getPieceDrawableByColor(finalPlayer4color));
            }
        });

        if (nop == 3) {
            rmp4bg.setAlpha(0.5f);
            rmpp4removeicon.setOnClickListener(null);
        }

        rmpbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingamermplayout.animate().scaleX(0.0f).scaleY(0.0f).setInterpolator(new AnticipateInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ingamermplayout.setVisibility(GONE);
                    }
                }).start();
            }
        });

        rmpmenubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuexitbtn.callOnClick();
            }
        });

        // confirm rmp layout views
        confirmrmpyesbtn.setOnTouchListener(clickEffect);
        confirmrmpnobtn.setOnTouchListener(clickEffect);
        confirmrmpyesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeThisPlayerByIndex(selectedconfirmrmpplayerindex);
                confirmrmplayout.setVisibility(GONE);
                ingamermplayout.setVisibility(GONE);
            }
        });

        confirmrmpnobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmrmplayout.animate().scaleX(0.0f).scaleY(0.0f).setInterpolator(new AnticipateInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        confirmrmplayout.setVisibility(GONE);
                    }
                }).start();
            }
        });

        // Congratulations screen
        congratulationslayout.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {}});

        congratsmenubtn.setOnTouchListener(clickEffect);
        congratsreplaybtn.setOnTouchListener(clickEffect);
        congratssoundbtn.setOnTouchListener(clickEffect);
        congratssharebtn.setOnTouchListener(clickEffect);
        congratsmenubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingameyesbtn.callOnClick();
            }
        });

        congratssoundbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSoundOn) {
                    isSoundOn = false;
                    sharedPreferences.edit().putBoolean("sound",false).apply();
                    congratssoundbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.soundoff,null));
                    if(congratulationSound!=null) { if(congratulationSound.isPlaying()) { congratulationSound.pause(); } }
                } else {
                    isSoundOn = true;
                    sharedPreferences.edit().putBoolean("sound",true).apply();
                    congratssoundbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.soundon,null));
                    if(congratulationSound!=null) { if(!congratulationSound.isPlaying()) { congratulationSound.start(); } }
                }
            }
        });

        congratssharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        congratsreplaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopEverything();
                Intent i = getIntent();
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtras(i.getExtras());
                startActivity(i);
                overridePendingTransition(0,0);
                finish();
            }
        });

        if(gametype==1) {
            findViewById(R.id.constraintLayout8).setVisibility(View.VISIBLE);
            findViewById(R.id.imageView114).setVisibility(View.GONE);
            findViewById(R.id.teamslayout).setVisibility(View.GONE);
        }

        if(gametype==2) {
            findViewById(R.id.constraintLayout8).setVisibility(GONE);
            findViewById(R.id.imageView114).setVisibility(View.GONE);
            findViewById(R.id.teamslayout).setVisibility(View.VISIBLE);
            ((ImageView)findViewById(R.id.imageView132)).setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.team1header,null));

            ((ImageView)findViewById(R.id.imageView137)).setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.team2header,null));

            ((ImageView)findViewById(R.id.imageView133)).setImageDrawable(getPieceDrawableByColor("blue"));
            ((ImageView)findViewById(R.id.imageView135)).setImageDrawable(getPieceDrawableByColor("green"));
            ((ImageView)findViewById(R.id.imageView138)).setImageDrawable(getPieceDrawableByColor("red"));
            ((ImageView)findViewById(R.id.imageView140)).setImageDrawable(getPieceDrawableByColor("yellow"));

            ((TextView)findViewById(R.id.team1name1)).setText(player3name);
            ((TextView)findViewById(R.id.team1name2)).setText(player2name);
            ((TextView)findViewById(R.id.team2name1)).setText(player1name);
            ((TextView)findViewById(R.id.team2name2)).setText(player4name);
        }

        if (gametype == 3) {
            findViewById(R.id.constraintLayout8).setVisibility(View.VISIBLE);
            findViewById(R.id.imageView114).setVisibility(GONE);
            findViewById(R.id.teamslayout).setVisibility(GONE);
            for (Player p : players) {
                List<Piece> pieces = getPiecesByColor(p.getColor());
                for (int i = 0; i < 2; i++) {
                    pieces.get(i).makeAlive();
                }
                globalHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        checkAdjustments(pieces.get(0).startPosition);
                    }
                },420);
            }
        }

        if(gametype==4) {
            findViewById(R.id.constraintLayout8).setVisibility(View.VISIBLE);
            findViewById(R.id.imageView114).setVisibility(View.VISIBLE);
            findViewById(R.id.teamslayout).setVisibility(GONE);
        }


            Runnable blinkAnim = new Runnable() {
                @Override
                public void run() {
                    if(gameStartImageView.getVisibility()==View.INVISIBLE) {
                        gameStartImageView.setVisibility(View.VISIBLE);
                    } else {
                        gameStartImageView.setVisibility(View.INVISIBLE);
                    }
                    globalHandler.postDelayed(this,220);
                }
            };

            globalHandler.post(blinkAnim);

            gameStartSound = MediaPlayer.create(this,R.raw.gamestartsound);
            gameStartSound.start();
            gameStartSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    globalHandler.removeCallbacks(blinkAnim);
                    gameStartImageView.setVisibility(GONE);
                    mainDiceImageView.setVisibility(View.VISIBLE);
                    hintArrow.setVisibility(View.VISIBLE);
                    switchPlayers();
                    gameStartSound.release();
                }
            });
            mainDiceImageView.setVisibility(GONE);
            hintArrow.setVisibility(GONE);
            d = new Dice(mainDiceImageView, nop, color);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(((int)(pxWidth*0.4)),((int)(pxWidth*0.4)));
        redHomeBlink.setLayoutParams(lp);
        lp.addRule(RelativeLayout.ALIGN_TOP,R.id.imageView); lp.addRule(RelativeLayout.ALIGN_LEFT,R.id.imageView);

        }


    private void stopEverything() {
        globalHandler.removeCallbacksAndMessages(null);
        d.diceHandler.removeCallbacksAndMessages(null);
        if(congratulationSound!=null) {
            if(congratulationSound.isPlaying()) {
                congratulationSound.stop();
            }
            try { congratulationSound.release(); } catch (Exception e) {}
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void hideThisPlayerDiceBg(int index) {
        switch (index) {
            case 1:
                findViewById(R.id.bleftdicebg).setVisibility(GONE);
                break;
            case 2:
                findViewById(R.id.tleftdicebg).setVisibility(GONE);
                break;
            case 3:
                findViewById(R.id.trightdicebg).setVisibility(GONE);
                break;
            case 4:
                findViewById(R.id.brightdicebg).setVisibility(GONE);
                break;
        }
    }

    private void removeThisPlayerByIndex(int index) {
        if(currentPlayerIndex!=0) { currentPlayerIndex--; }
        switch (index) {
            case 1:
                rmp1bg.setAlpha(0.5f);
                rmpp1removeicon.setOnClickListener(null);
                if(currentPlayerIndex==(rmpindexforp1-1)) {
                    players.remove(rmpindexforp1-1);
                    rmpindexforp2--;
                    rmpindexforp3--;
                    rmpindexforp4--;
                    switchPlayers();
                } else {
                    currentPlayerIndex++;
                    players.remove(rmpindexforp1-1);
                    rmpindexforp3--;
                    rmpindexforp4--;
                }
                hideThisPlayerDiceBg(1);
                for(Piece p : getPiecesByColor(player1color)) {
                    p.piece.setVisibility(GONE);
                }
                p1exitbox.setImageDrawable(getExitBoxByColor(player1color));
                p1exitbox.setVisibility(View.VISIBLE);
                break;
            case 2:
                rmp2bg.setAlpha(0.5f);
                rmpp2removeicon.setOnClickListener(null);
                if(currentPlayerIndex==(rmpindexforp2-1)) {
                    players.remove(rmpindexforp2-1);
                    rmpindexforp3--;
                    rmpindexforp4--;
                    switchPlayers();
                } else {
                    currentPlayerIndex++;
                    players.remove(rmpindexforp2-1);
                    rmpindexforp3--;
                    rmpindexforp4--;
                }
                hideThisPlayerDiceBg(2);
                for(Piece p : getPiecesByColor(player2color)) {
                    p.piece.setVisibility(GONE);
                }
                p2exitbox.setImageDrawable(getExitBoxByColor(player2color));
                p2exitbox.setVisibility(View.VISIBLE);
                break;
            case 3:
                rmp3bg.setAlpha(0.5f);
                rmpp3removeicon.setOnClickListener(null);
                if(currentPlayerIndex==(rmpindexforp3-1)) {
                    players.remove(rmpindexforp3-1);
                    rmpindexforp4--;
                    switchPlayers();
                } else {
                    currentPlayerIndex++;
                    players.remove(rmpindexforp3-1);
                    rmpindexforp4--;
                }
                hideThisPlayerDiceBg(3);
                for(Piece p : getPiecesByColor(player3color)) {
                    p.piece.setVisibility(GONE);
                }
                p3exitbox.setImageDrawable(getExitBoxByColor(player3color));
                p3exitbox.setVisibility(View.VISIBLE);
                break;
            case 4:
                rmp4bg.setAlpha(0.5f);
                rmpp4removeicon.setOnClickListener(null);
                if(currentPlayerIndex==(rmpindexforp4-1-1)) {
                    players.remove(rmpindexforp4-1);
                    switchPlayers();
                } else {
                    currentPlayerIndex++;
                    players.remove(rmpindexforp4-1);
                }
                hideThisPlayerDiceBg(4);
                for(Piece p : getPiecesByColor(player4color)) {
                    p.piece.setVisibility(GONE);
                }
                p4exitbox.setImageDrawable(getExitBoxByColor(player4color));
                p4exitbox.setVisibility(View.VISIBLE);
                break;
        }

        if(players.size()==2) {
            menuremoveplayersbtn.setVisibility(GONE);
        }

    }

    private Drawable getExitBoxByColor(String color) {
        switch (color)
        {
            case "red":
                return ResourcesCompat.getDrawable(getResources(),R.drawable.redexitbox,null);
            case "blue":
                return ResourcesCompat.getDrawable(getResources(),R.drawable.blueexitbox,null);
            case "green":
                return ResourcesCompat.getDrawable(getResources(),R.drawable.greenexitbox,null);
            case "yellow":
                return ResourcesCompat.getDrawable(getResources(),R.drawable.yellowexitbox,null);
        }
        return ResourcesCompat.getDrawable(getResources(),R.drawable.redexitbox,null);
    }

    void addPiecesToRmpLayout(String color,String name,int index) {
        switch (index) {
            case 1:
                rmpp1piece.setImageDrawable(getPieceDrawableByColor(color));
                rmpp1name.setText(name);
                break;
            case 2:
                rmpp2piece.setImageDrawable(getPieceDrawableByColor(color));
                rmpp2name.setText(name);
                break;
            case 3:
                rmpp3piece.setImageDrawable(getPieceDrawableByColor(color));
                rmpp3name.setText(name);
                break;
            case 4:
                rmpp4piece.setImageDrawable(getPieceDrawableByColor(color));
                rmpp4name.setText(name);
                break;
        }
    }


    void switchPlayers()
    {
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayerPosition = currentPlayer.getPosition();
        currentPlayerColor = currentPlayer.getColor();
        currentPlayerSelectedIndex = currentPlayer.getIndex();
        currentPlayerName = currentPlayer.name;
        //Toast.makeText(this, currentPlayerColor+"", Toast.LENGTH_SHORT).show();
        currentPlayer.setActive();
        if(currentPlayer.isBot) {
            hintArrow.setVisibility(GONE);
            moveDice(currentPlayerPosition);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    d.roll();
                }
            },150);
        } else {
            moveDice(currentPlayerPosition);
            hintArrow.setVisibility(View.VISIBLE);
        }

        if(currentPlayerIndex>=(players.size()-1)) { currentPlayerIndex = 0; } else { currentPlayerIndex++; }
    }

    void moveDice(int position)
    {
        switch(position)
        {
            case 1:
                moveToTopLeft();
                break;
            case 2:
                moveToTopRight();
                break;
            case 3:
                moveToBottomLeft();
                break;
            case 4:
                moveToBottomRight();
                break;
        }

    }

    private void createPieces(String color, int position,String playername,boolean isBot) {
        if(nop==2) {
            if(players.size()==0)
            { players.add(new Player(position, color, playername, isBot, 0)); }
            else
            { players.add(new Player(position, color, playername, isBot, 2)); }
        }else {
            players.add(new Player(position, color, playername, isBot, players.size()));
            addPiecesToRmpLayout(color,getPlayerNameByPosition(position).getText().toString(),players.size());
        }
        float pos[][];
        int sPos[] = {39,0,26,13};
        switch (position) {case 1: pos = pos1; break; case 2: pos = pos2; break; case 3: pos = pos3; break; case 4: pos = pos4; break; default: pos = new float[4][2]; }
        switch (color) {
            case "red":
                rp.add(new Piece("red", redpiece1, findViewById(R.id.red1activecircle), findViewById(R.id.imageView6), findViewById(R.id.imageView7),  sPos[position - 1], pos[0][0], pos[0][1],isBot));
                rp.add(new Piece("red", redpiece2, findViewById(R.id.red2activecircle), findViewById(R.id.imageView62), findViewById(R.id.imageView72),  sPos[position - 1], pos[1][0], pos[1][1],isBot));
                rp.add(new Piece("red", redpiece3, findViewById(R.id.red3activecircle), findViewById(R.id.imageView63), findViewById(R.id.imageView73),  sPos[position - 1], pos[2][0], pos[2][1],isBot));
                rp.add(new Piece("red", redpiece4, findViewById(R.id.red4activecircle), findViewById(R.id.imageView64), findViewById(R.id.imageView74),  sPos[position - 1], pos[3][0], pos[3][1],isBot));
                break;
            case "green":
                gp.add(new Piece("green", greenpiece1, findViewById(R.id.green1activecircle), findViewById(R.id.imageView51), findViewById(R.id.imageView81),  sPos[position - 1], pos[0][0], pos[0][1],isBot));
                gp.add(new Piece("green", greenpiece2, findViewById(R.id.green2activecircle), findViewById(R.id.imageView52), findViewById(R.id.imageView82),  sPos[position - 1], pos[1][0], pos[1][1],isBot));
                gp.add(new Piece("green", greenpiece3, findViewById(R.id.green3activecircle), findViewById(R.id.imageView53), findViewById(R.id.imageView83),  sPos[position - 1], pos[2][0], pos[2][1],isBot));
                gp.add(new Piece("green", greenpiece4, findViewById(R.id.green4activecircle), findViewById(R.id.imageView54), findViewById(R.id.imageView84),  sPos[position - 1], pos[3][0], pos[3][1],isBot));
                break;
            case "blue":
                bp.add(new Piece("blue", bluepiece1, findViewById(R.id.blue1activecircle), findViewById(R.id.imageView41), findViewById(R.id.imageView91),  sPos[position - 1], pos[0][0], pos[0][1],isBot));
                bp.add(new Piece("blue", bluepiece2, findViewById(R.id.blue2activecircle), findViewById(R.id.imageView42), findViewById(R.id.imageView92),  sPos[position - 1], pos[1][0], pos[1][1],isBot));
                bp.add(new Piece("blue", bluepiece3, findViewById(R.id.blue3activecircle), findViewById(R.id.imageView43), findViewById(R.id.imageView93),  sPos[position - 1], pos[2][0], pos[2][1],isBot));
                bp.add(new Piece("blue", bluepiece4, findViewById(R.id.blue4activecircle), findViewById(R.id.imageView44), findViewById(R.id.imageView94),  sPos[position - 1], pos[3][0], pos[3][1],isBot));
                break;
            case "yellow":
                yp.add(new Piece("yellow", yellowpiece1, findViewById(R.id.yellow1activecircle), findViewById(R.id.imageView31), findViewById(R.id.imageView101),  sPos[position - 1], pos[0][0], pos[0][1],isBot));
                yp.add(new Piece("yellow", yellowpiece2, findViewById(R.id.yellow2activecircle), findViewById(R.id.imageView32), findViewById(R.id.imageView102),  sPos[position - 1], pos[1][0], pos[1][1],isBot));
                yp.add(new Piece("yellow", yellowpiece3, findViewById(R.id.yellow3activecircle), findViewById(R.id.imageView33), findViewById(R.id.imageView103),  sPos[position - 1], pos[2][0], pos[2][1],isBot));
                yp.add(new Piece("yellow", yellowpiece4, findViewById(R.id.yellow4activecircle), findViewById(R.id.imageView34), findViewById(R.id.imageView104),  sPos[position - 1], pos[3][0], pos[3][1],isBot));
                break;
        }
    }

    View getStepBubble(int n)
    {
        switch (n)
        {
            case 1:
                return step1;
            case 2:
                return step2;
            case 3:
                return step3;
            case 4:
                return step4;
            case 5:
                return step5;
            case 6:
                return step6;
        }
        return step1;
    }

    void initViews()
    {
        sharedPreferences = getSharedPreferences("LudoModUser",MODE_PRIVATE);

        botwins= sharedPreferences.getInt("botwins",0);
        botloses=sharedPreferences.getInt("botloses",0);
        ((TextView)findViewById(R.id.userwins)).setText((botwins+""));
        ((TextView)findViewById(R.id.userloses)).setText((botloses+""));

        isSoundOn = sharedPreferences.getBoolean("sound",true);
        isMusicOn = sharedPreferences.getBoolean("music",false);

        completeBackground = findViewById(R.id.activitybg);
        displayMetrics = getResources().getDisplayMetrics();
        dpHeight = displayMetrics.heightPixels/displayMetrics.density;
        dpWidth = displayMetrics.widthPixels/displayMetrics.density;
        pxWidth = displayMetrics.widthPixels;
        pxHeight = displayMetrics.heightPixels-getStatusBarHeight();
        ludoBoard = findViewById(R.id.imageView);
        red1activecircle = findViewById(R.id.red1activecircle);
        red2activecircle = findViewById(R.id.red2activecircle);
        red3activecircle = findViewById(R.id.red3activecircle);
        red4activecircle = findViewById(R.id.red4activecircle);
        redHomeBlink = findViewById(R.id.imageView3);
        redpiece1 = findViewById(R.id.redpiece1);
        redpiece2 = findViewById(R.id.redpiece2);
        redpiece3 = findViewById(R.id.redpiece3);
        redpiece4 = findViewById(R.id.redpiece4);

        greenpiece1 = findViewById(R.id.greenpiece1);
        greenpiece2 = findViewById(R.id.greenpiece2);
        greenpiece3 = findViewById(R.id.greenpiece3);
        greenpiece4 = findViewById(R.id.greenpiece4);

        bluepiece1 = findViewById(R.id.bluepiece1);
        bluepiece2 = findViewById(R.id.bluepiece2);
        bluepiece3 = findViewById(R.id.bluepiece3);
        bluepiece4 = findViewById(R.id.bluepiece4);

        yellowpiece1 = findViewById(R.id.yellowpiece1);
        yellowpiece2 = findViewById(R.id.yellowpiece2);
        yellowpiece3 = findViewById(R.id.yellowpiece3);
        yellowpiece4 = findViewById(R.id.yellowpiece4);

        // player names 1 to 4
        playername1 = findViewById(R.id.pname1);
        playername2 = findViewById(R.id.pname2);
        playername3 = findViewById(R.id.pname3);
        playername4 = findViewById(R.id.pname4);

        mainDiceImageView = findViewById(R.id.maindiceimageview);
        hintArrow = findViewById(R.id.hintarrowleft);

        crownIndex1 = findViewById(R.id.winnercrownindex1);
        crownIndex2 = findViewById(R.id.winnercrownindex2);
        crownIndex3 = findViewById(R.id.winnercrownindex3);
        crownIndex4 = findViewById(R.id.winnercrownindex4);

        // ingame menu items
        ingamemenubtn = findViewById(R.id.ingamemenubtn);
        ingamemenuitemslayout = findViewById(R.id.ingamemenuitemslayout);
        menuremoveplayersbtn = findViewById(R.id.rmpitemmenu);
        menuexitbtn = findViewById(R.id.exititemmenu);

        // ingame exitlayout views
        quitgamelayout = findViewById(R.id.ingamequitlayout);
        ingameyesbtn = findViewById(R.id.ingamequitlayoutyesbtn);
        ingamenobtn = findViewById(R.id.ingamequitlayoutnobtn);
        ingamesoundbtn = findViewById(R.id.ingamequitlayoutsoundbtn);
        ingamemusicbtn = findViewById(R.id.ingamequitlayoutmusicbtn);

        //in game rmplayout
        ingamermplayout = findViewById(R.id.ingamermplayout);
        rmp1bg = findViewById(R.id.rmp1);
        rmp2bg = findViewById(R.id.rmp2);
        rmp3bg = findViewById(R.id.rmp3);
        rmp4bg = findViewById(R.id.rmp4);
        rmpp1piece = findViewById(R.id.rmp1piece);
        rmpp2piece = findViewById(R.id.rmp2piece);
        rmpp3piece = findViewById(R.id.rmp3piece);
        rmpp4piece = findViewById(R.id.rmp4piece);
        rmpp1removeicon = findViewById(R.id.rmp1btn);
        rmpp2removeicon = findViewById(R.id.rmp2btn);
        rmpp3removeicon = findViewById(R.id.rmp3btn);
        rmpp4removeicon = findViewById(R.id.rmp4btn);
        rmpbackbtn = findViewById(R.id.rmpbackbtn);
        rmpmenubtn = findViewById(R.id.rmpmenubtn);
        rmpp1name = findViewById(R.id.rmp1name);
        rmpp2name = findViewById(R.id.rmp2name);
        rmpp3name = findViewById(R.id.rmp3name);
        rmpp4name = findViewById(R.id.rmp4name);


        //in gmae rmp sublayout confirmrmplayout
        confirmrmplayout = findViewById(R.id.confirmrmplayout);
        selectedrmppiece = findViewById(R.id.confirmremovepieceview);
        confirmrmpyesbtn = findViewById(R.id.confirmrmpyesbtn);
        confirmrmpnobtn = findViewById(R.id.confirmrmpnobtn);

        // players exit boxes
        p1exitbox = findViewById(R.id.p1exitbox);
        p2exitbox = findViewById(R.id.p2exitbox);
        p3exitbox = findViewById(R.id.p3exitbox);
        p4exitbox = findViewById(R.id.p4exitbox);

        // congratulaions layout

        congratulationslayout = findViewById(R.id.congratulationsscreen);

        congratsmenubtn = findViewById(R.id.congratsscreenmenubtn);
        congratssoundbtn = findViewById(R.id.congratsscreensoundbtn);
        congratssharebtn = findViewById(R.id.congratssharebtn);
        congratsreplaybtn = findViewById(R.id.congratsscreenreplaybtn);

        winnerlistp1layout = findViewById(R.id.winnerpositionlayout1);
        winnerlistp2layout = findViewById(R.id.winnerpositionlayout2);
        winnerlistp3layout = findViewById(R.id.winnerpositionlayout3);
        winnerlistp4layout = findViewById(R.id.winnerpositionlayout4);

        wlistcrown1 = findViewById(R.id.winnerliscrown1);
        wlistcrown2 = findViewById(R.id.winnerliscrown2);
        wlistcrown3 = findViewById(R.id.winnerliscrown3);
        wlistcrown4 = findViewById(R.id.winnerliscrown4);
        wlistpiece1 = findViewById(R.id.winnerlistp1);
        wlistpiece2 = findViewById(R.id.winnerlistp2);
        wlistpiece3 = findViewById(R.id.winnerlistp3);
        wlistpiece4 = findViewById(R.id.winnerlistp4);
        wlistwinorlose1 = findViewById(R.id.winorlose1);
        wlistwinorlose2 = findViewById(R.id.winorlose2);
        wlistwinorlose3 = findViewById(R.id.winorlose3);
        wlistwinorlose4 = findViewById(R.id.winorlose4);

        wlistname1 = findViewById(R.id.winnerlistpname1);
        wlistname2 = findViewById(R.id.winnerlistpname2);
        wlistname3 = findViewById(R.id.winnerlistpname3);
        wlistname4 = findViewById(R.id.winnerlistpname4);

        gameStartImageView = findViewById(R.id.gamestartimageview);

        pos1 = new float[4][2];
        pos2 = new float[4][2];
        pos3 = new float[4][2];
        pos4 = new float[4][2];

        onePercentWidth = (float) pxWidth/100;
        onePercentHeight = (float) pxHeight/100;

        players = new ArrayList<>();

        rp = new ArrayList<>();
        gp = new ArrayList<>();
        bp = new ArrayList<>();
        yp = new ArrayList<>();

        safeSpots = new HashSet<>();

        safeSpots.add(0);safeSpots.add(8);safeSpots.add(13);safeSpots.add(21);safeSpots.add(26);safeSpots.add(34);safeSpots.add(39);safeSpots.add(47);
        //safeSpots.add(57);safeSpots.add(63);safeSpots.add(69);safeSpots.add(75);

        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        step3 = findViewById(R.id.step3);
        step4 = findViewById(R.id.step4);
        step5 = findViewById(R.id.step5);
        step6 = findViewById(R.id.step6);

        int boxSize = (int)(Math.ceil(pxWidth*0.0667)*1.3);
        sizeOfBox = (int)(Math.ceil(pxWidth*0.0667)*0.15);

        blockSize = (float) (pxWidth*0.0667);

        RelativeLayout.LayoutParams stepsParams = new RelativeLayout.LayoutParams(boxSize,boxSize);
        step1.setLayoutParams(stepsParams);
        step2.setLayoutParams(stepsParams);
        step3.setLayoutParams(stepsParams);
        step4.setLayoutParams(stepsParams);
        step5.setLayoutParams(stepsParams);
        step6.setLayoutParams(stepsParams);


        redCircle = AppCompatResources.getDrawable(this,R.drawable.red_circle_shaded);
        greenCircle = AppCompatResources.getDrawable(this,R.drawable.green_circle_shaded);
        blueCircle = AppCompatResources.getDrawable(this,R.drawable.blue_circle_shaded);
        yellowCircle = AppCompatResources.getDrawable(this,R.drawable.yellow_circle_shaded);

        wBlocks52 = new int[] {52, 53, 54, 55, 56, 57};
        wBlocks58 = new int[] {58, 59, 60, 61, 62, 63};
        wBlocks64 = new int[] {64, 65, 66, 67, 68, 69};
        wBlocks70 = new int[] {70, 71, 72, 73, 74, 75};

        diceRollSound = MediaPlayer.create(this, R.raw.diceroll);
        stepSound = MediaPlayer.create(this, R.raw.step);
//        safeSound = MediaPlayer.create(this, R.raw.safe);
//        deathSound = MediaPlayer.create(this, R.raw.death);
//        pantaSound = MediaPlayer.create(this, R.raw.panta);
//        congratulationSound = MediaPlayer.create(this, R.raw.congratulations);

        if(isSoundOn) {
            ingamesoundbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.soundon,null));
            congratssoundbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.soundon,null));
        } else { ingamesoundbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.soundoff,null));
            congratssoundbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.soundoff,null));}

        if(isMusicOn) {
            ingamemusicbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.musicon,null));
        } else { ingamemusicbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.musicoff,null)); }


        editText = findViewById(R.id.editTextNumber);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(((int)(pxWidth)),((int)(pxWidth)));
        ludoBoard.setLayoutParams(layoutParams); // size of board set
    }

    void initializeAnimatorSets() {
        animatorSets = new AnimatorSet[6];

        for (int i = 0; i < animatorSets.length; i++) {
            View v = getStepBubble(i+1);
            animatorSets[i] = createAnimatorSet(v);
            animatorSets[i].addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    v.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    void showStep(int viewIndex,String color, float x, float y) {
        if (animatorSets == null || animatorSets.length != 6) {
            initializeAnimatorSets();
        }

        View view = null;
        view = getStepBubble(viewIndex);

        if (view != null) {
            view.setBackground(getColorDrawable(color));
            view.setTranslationX(x-sizeOfBox);
            view.setTranslationY(y-sizeOfBox);

            AnimatorSet animatorSet = animatorSets[viewIndex - 1];
            view.setVisibility(View.VISIBLE);
            animatorSet.start();
        }
    }

    private AnimatorSet createAnimatorSet(View view) {
        AnimatorSet animatorSet = new AnimatorSet();

        float initialScale = 0.5f;
        float finalScale = 1.0f;
        long duration = 700;
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, initialScale, finalScale);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, initialScale, finalScale);
        ObjectAnimator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY);
        scaleAnimator.setDuration(600);

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1.0f, 0.0f);
        alphaAnimator.setDuration(800);

        animatorSet.playTogether(scaleAnimator, alphaAnimator);

        return animatorSet;
    }

    Drawable getColorDrawable(String color)
    {
        switch (color)
        {
            case "red":
                return redCircle;
            case "green":
                return greenCircle;
            case "blue":
                return blueCircle;
            case "yellow":
                return yellowCircle;
        }
        return redCircle;
    }

    void setFullScreen() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            View dview = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            dview.setSystemUiVisibility(uiOptions);
        } else {
            View dview = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            dview.setSystemUiVisibility(uiOptions);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                WindowInsetsController insetsController = getWindow().getInsetsController();
                if(insetsController !=null) {
                    insetsController.hide(WindowInsets.Type.navigationBars());
                    insetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
                }
            }

        }
    }

    float getNavHeight()
    {
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public float getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    protected void onResume() {
        super.onResume();
        View dview = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        dview.setSystemUiVisibility(uiOptions);
    }

    void moveToTopLeft()
    {
        ConstraintLayout.LayoutParams lps = (ConstraintLayout.LayoutParams) mainDiceImageView.getLayoutParams();
        lps.startToStart = ConstraintLayout.LayoutParams.UNSET;
        lps.setMarginStart(-((int)displayMetrics.density));
        lps.endToEnd = R.id.tleftdicebg;
        lps.bottomToBottom = R.id.tleftdicebg;
        mainDiceImageView.setLayoutParams(lps);
        ConstraintLayout.LayoutParams arrowLps = (ConstraintLayout.LayoutParams) hintArrow.getLayoutParams();
        arrowLps.endToStart = ConstraintLayout.LayoutParams.UNSET;
        arrowLps.startToEnd = R.id.tleftdicebg;
        arrowLps.topToTop = R.id.tleftdicebg;
        arrowLps.bottomToBottom = R.id.tleftdicebg;
        hintArrow.setRotation(0);
        hintArrow.setLayoutParams(arrowLps);
    }

    void moveToTopRight()
    {
        ConstraintLayout.LayoutParams lps = (ConstraintLayout.LayoutParams) mainDiceImageView.getLayoutParams();
        lps.startToStart = R.id.trightdicebg;
        lps.setMarginStart(-((int)(2*displayMetrics.density)));
        lps.endToEnd = ConstraintLayout.LayoutParams.UNSET;
        lps.bottomToBottom = R.id.trightdicebg;
        mainDiceImageView.setLayoutParams(lps);
        ConstraintLayout.LayoutParams arrowLps = (ConstraintLayout.LayoutParams) hintArrow.getLayoutParams();
        arrowLps.startToEnd = ConstraintLayout.LayoutParams.UNSET;
        arrowLps.endToStart = R.id.trightdicebg;
        arrowLps.topToTop = R.id.trightdicebg;
        arrowLps.bottomToBottom = R.id.trightdicebg;
        hintArrow.setRotation(180);
        hintArrow.setLayoutParams(arrowLps);
    }

    void moveToBottomRight()
    {
        ConstraintLayout.LayoutParams lps = (ConstraintLayout.LayoutParams) mainDiceImageView.getLayoutParams();
        lps.startToStart = R.id.brightdicebg;
        lps.setMarginStart(-((int)(2*displayMetrics.density)));
        lps.endToEnd = ConstraintLayout.LayoutParams.UNSET;
        lps.bottomToBottom = R.id.brightdicebg;
        mainDiceImageView.setLayoutParams(lps);
        ConstraintLayout.LayoutParams arrowLps = (ConstraintLayout.LayoutParams) hintArrow.getLayoutParams();
        arrowLps.startToEnd = ConstraintLayout.LayoutParams.UNSET;
        arrowLps.endToStart = R.id.brightdicebg;
        arrowLps.topToTop = R.id.brightdicebg;
        arrowLps.bottomToBottom = R.id.brightdicebg;
        hintArrow.setRotation(180);
        hintArrow.setLayoutParams(arrowLps);
    }

    void moveToBottomLeft()
    {
        ConstraintLayout.LayoutParams lps = (ConstraintLayout.LayoutParams) mainDiceImageView.getLayoutParams();
        lps.startToStart = ConstraintLayout.LayoutParams.UNSET;
        lps.setMarginStart(-((int)displayMetrics.density));
        lps.endToEnd = R.id.bleftdicebg;
        lps.bottomToBottom = R.id.bleftdicebg;
        mainDiceImageView.setLayoutParams(lps);
        ConstraintLayout.LayoutParams arrowLps = (ConstraintLayout.LayoutParams) hintArrow.getLayoutParams();
        arrowLps.endToStart = ConstraintLayout.LayoutParams.UNSET;
        arrowLps.startToEnd = R.id.bleftdicebg;
        arrowLps.topToTop = R.id.bleftdicebg;
        arrowLps.bottomToBottom = R.id.bleftdicebg;
        hintArrow.setRotation(0);
        hintArrow.setLayoutParams(arrowLps);
    }

    int[] getWinnerBlocks(int n)
    {
        switch (n)
        {
            case 0:
                return wBlocks52;
            case 11:
                return wBlocks58;
            case 24:
                return wBlocks64;
            case 37:
                return wBlocks70;
            default:
                return wBlocks52;
        }
    }

    @Override
    public void onBackPressed() {
        if(quitgamelayout.getVisibility()==View.VISIBLE)
        { ingamenobtn.callOnClick(); } else
        { menuexitbtn.callOnClick(); }
    }
}