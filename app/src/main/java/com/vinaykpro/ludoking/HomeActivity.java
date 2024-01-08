package com.vinaykpro.ludoking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    double dpHeight;
    double dpWidth;
    double pxWidth;
    double pxHeight;
    MediaPlayer m,clickSoundEffect;
    ConstraintLayout topNavBar,coinAndDiamondBG,settingspopupbg,guestprofilecreateoreditlayout,statisticslayout, passnplaylayout,passnplayclassiclayout,p2layout,p3layout,p4layout,pnpteamuplayout,computerlayout,computerclassiclayout,computerrushmode;
    ObjectAnimator rotateAnimator;
    ImageView nointernet;

    Handler nointernethandler;
    Runnable nointernetrunnable;

    boolean isSoundOn = true;
    boolean isMusicOn = true;

    boolean tokenNormal = true;
    // home layout views
    ConstraintLayout exitAppLayout;
    ImageView profiletop,playonline,playwithfriends,computer,passnplay,shopbutton,exitgamebtn,settingsbtn,invitefriendsbtn,tmntbtn;
    TextView usernamehomescreen,diamondscount1,diamondscount2,coinscount1,coinscount2;
    // settings layout views
    ImageView stgsbackbtn,stgssharebtn,stgslikebtn,stgsgeturpverifiedbtn,stgssoundonoffbtn,stgsmusiconoffbtn,stgsplaybtn,stgslanguagebtn,stgsselectbtn,stgsviewbtn1,stgsfaqbtn,stgscomposebtn,stgsrulesbtn,stgsviewbtn2,stgsmoregamesbtn,stgslboardbtn,stgsviewbtn3,stgseditbtn,stgsinfobtn,stgsdelaccountbtn,stgsfbbtn,stgsinstabtn,stgstwitterbtn,stgsytbtn,stgsmoregameslongbtn;
    // statistics layout views
    ImageView stcsusernamebtn,stcsprofpic,stcsbackbtn,stcseditprofile,stcslgwfb,stcslgwggl,stcslgwpg,stcsfshare;
    TextView stcsusernametextview;
    //first login screen views
    ConstraintLayout firstLoginLayout;
    ImageView lgnwfbbtn,snwgglbtn,snwpgbtn,playasguestbtn;
    //guest profile/second login screen views
    ImageView pselecturctrybtn,pprofilepicview,plgnwfbbtn,psnwgglbtn,psnwpgbtn,pcontinuebtn;
    TextView playerNameBtnComTextView;
    // passnplay layout views
    ImageView pnptokentype1,pnptokentype2,pnpqmark,pnpclassic,pnpteamup,pnpquick,pnpbackbtn,pnpnext;
    int pnpgametype=1;
    // pnp classic layout views
    ImageView p2pbg,p3pbg,p4pbg,p5pbg,p6pbg,p5pbgtxt,p6pbgtxt,pnpclassicbackbtn,pnpclassicplaybtn,pnpclassicplay1tknout;
    int currentplayermode=-1;
    TextView botsCountMonitor;
    // p2 layout views
    ConstraintLayout p2l1,p2l2; View p2l1inactiveview,p2l2inactiveview;
    ImageView p2l1tick,p2l2tick,p2l1piece1,p2l1piece2,p2l2piece1,p2l2piece2;
    TextView p2l1player1name,p2l1player2name,p2l2player1name,p2l2player2name;
    boolean isP2L1True=true;

    // p3 layout views
    ImageView p3l1pb,p3l1pr,p3l1pg,p3l1py,p3l2pb,p3l2pr,p3l2pg,p3l2py,p3l3pb,p3l3pr,p3l3pg,p3l3py,p3bot1,p3bot2,p3bot3;
    int p3l1value=1,p3l2value=2,p3l3value=3,p3botcount=0;
    boolean isP3bot1Active=false,isP3bot2Active=false,isP3bot3Active=false;
    TextView p3player1name,p3player2name,p3player3name;
    // p4 layout views
    ImageView p4p1,p4p2,p4p3,p4p4,p4p5,p4p6,p4bot1,p4bot2,p4bot3,p4bot4,p4bot5,p4bot6;
    TextView p4pname1,p4pname2,p4pname3,p4pname4,p4pname5,p4pname6;
    RelativeLayout player5namebg,player6namebg;
    int p4botcount=0;
    boolean isP4bot1Active=false,isP4bot2Active=false,isP4bot3Active=false,isP4bot4Active=false,isP4bot5Active=false,isP4bot6Active=false;

    // pnpteamup layout views
    ImageView pnpt2help,pnpt2playbtn,pnpt2backbtn,t2t1p1,t2t1p2,t2t2p1,t2t2p2;
    TextView pnpt2t1p1name,pnpt2t1p2name,pnpt2t2p1name,pnpt2t2p2name;

    /* pnpquick layout is same as classic layout manipulated and managed programatically :-)) */

    // computer layout views
    ImageView compclassicbtn,comprushmodebtn,comphelpbtn,compbackbtn,compnextbtn;
    int computergametype = 1;

    // computer classic layout views
    ImageView compl2bt,compl2rt,compl2gt,compl2yt,players2tick,players4tick,compl2backbtn,compl2playbtn;
    TextView players2txtview,players4txtview;
    boolean comp2players = true;
    int computerselectedcolor=1;

    EditText mainNamesEditText; TextView editTextOKBtn,currentActiveTextView=null;

    SharedPreferences sharedPreferences;


    ImageView exitYesBtn,exitNoBtn;
    private float onedp;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_home);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initViews();
        View v = findViewById(R.id.homebackgroundview);
        ConstraintLayout.LayoutParams bgparams = new ConstraintLayout.LayoutParams((int)pxWidth,(int)(pxHeight+getStatusBarHeight()+60));
        v.setLayoutParams(bgparams);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        int navHeight = (int)(pxWidth*0.23518);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams((int)pxWidth,navHeight);
        topNavBar.setLayoutParams(params);
        params = new ConstraintLayout.LayoutParams((int)(pxWidth*0.62222), (int)(navHeight*0.22834));
        params.topMargin = (int)(navHeight*0.043307f);
        params.startToStart = R.id.topNavBar;
        params.topToTop = R.id.topNavBar;
        params.endToEnd = R.id.topNavBar;
        coinAndDiamondBG.setLayoutParams(params);
        params = new ConstraintLayout.LayoutParams((int)(pxWidth*0.32), (int)((pxWidth*0.32)+((pxWidth*0.32)*0.14)));
        params.topMargin = (int)(pxWidth*0.05);
        params.leftMargin = (int)(pxWidth*0.15);
        params.topToBottom = R.id.imageView15;
        params.startToStart = R.id.root;
        playonline.setLayoutParams(params);
        params = new ConstraintLayout.LayoutParams((int)(pxWidth*0.32), (int)((pxWidth*0.32)+((pxWidth*0.32)*0.14)));
        params.topMargin = (int)(pxWidth*0.05);
        params.rightMargin = (int)(pxWidth*0.15);
        params.topToBottom = R.id.imageView15;
        params.endToEnd = R.id.root;
        playwithfriends.setLayoutParams(params);
        params = new ConstraintLayout.LayoutParams((int)(pxWidth*0.32), (int)((pxWidth*0.32)+((pxWidth*0.32)*0.14)));
        params.leftToLeft = playonline.getId();
        params.topToBottom = playonline.getId();
        computer.setLayoutParams(params);
        params = new ConstraintLayout.LayoutParams((int)(pxWidth*0.32), (int)((pxWidth*0.32)+((pxWidth*0.32)*0.14)));
        params.rightToRight = playwithfriends.getId();
        params.topToBottom = playwithfriends.getId();
        passnplay.setLayoutParams(params);

        if(isMusicOn) {
            m = MediaPlayer.create(this, R.raw.music);
            m.setLooping(true);
            m.start();
            stgsmusiconoffbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.musicon,null));
        } else { stgsmusiconoffbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.musicoff,null)); }

        if(isSoundOn) {
            stgssoundonoffbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.soundon,null));
        } else { stgssoundonoffbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.soundoff,null)); }

        clickSoundEffect = MediaPlayer.create(this,R.raw.click);
        clickSoundEffect.seekTo(0);

        ImageView imageView = findViewById(R.id.imageView15);
        Glide.with(this)
                .asGif()
                .load(R.raw.ludotopmainicon)
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
        rotateAnimator = ObjectAnimator.ofFloat(findViewById(R.id.imageView21),"rotation",0,360);
        rotateAnimator.setDuration(900);
        rotateAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        rotateAnimator.setRepeatMode(ObjectAnimator.RESTART);
        rotateAnimator.setInterpolator(new LinearInterpolator());
        rotateAnimator.start();


        // player login logic
        //sharedPreferences = getSharedPreferences("LudoModUser",MODE_PRIVATE);

        boolean isPlayerLoggedIn = sharedPreferences.getBoolean("logged",false);

        if(!isPlayerLoggedIn) {
            int tempnum = (int) Math.floor(Math.random()*7777)+2145;
            firstLoginLayout.setVisibility(View.VISIBLE);
            String generatedguestname = "Guest"+tempnum;
            stcsusernametextview.setText(generatedguestname);
            playerNameBtnComTextView.setText(generatedguestname);
            usernamehomescreen.setText(generatedguestname);
            this.onClick(imageView);
            coinscount1.setText(("1,000"));
            coinscount2.setText(("1,000"));
        } else {
            int dpindex = sharedPreferences.getInt("dp",0);
            setProfilePic(dpindex);
            String username = sharedPreferences.getString("username","Guest4886");
            usernamehomescreen.setText(username);
            stcsusernametextview.setText(username);
            playerNameBtnComTextView.setText(username);
            diamondscount1.setText(("100"));
            coinscount1.setText(("1,000"));
            diamondscount2.setText(("100"));
            coinscount2.setText(("1,000"));
        }

        ImageView ad1 = findViewById(R.id.imageView142);
        AnimationDrawable animationDrawable = (AnimationDrawable) ad1.getDrawable();
        ad1.setImageDrawable(animationDrawable);
        animationDrawable.setOneShot(false); // Set to true if you want to run the animation only once
        animationDrawable.start();




















                View.OnTouchListener clickEffect = new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ((ImageView)v).setColorFilter(ResourcesCompat.getColor(getResources(),R.color.dim_color,null), PorterDuff.Mode.SRC_ATOP);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        ((ImageView)v).clearColorFilter();
                        break;
                }
                return false;
            }
        };

        View.OnTouchListener opaqueLayout = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        };

        View.OnClickListener noInternetOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giveClickSound();
                showNoInternet();
            }
        };

        View.OnTouchListener clickBounceEffect = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            view.animate().setListener(null);
                            view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start();
                        }
                    }).start();
                }
                return false;
            }
        };

        View.OnClickListener editTextOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView thisview = ((TextView) view);
                mainNamesEditText.setVisibility(View.VISIBLE);
                mainNamesEditText.requestFocus();
                mainNamesEditText.setText(thisview.getText().toString());
                mainNamesEditText.setSelection(thisview.getText().length());
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput(mainNamesEditText, InputMethodManager.SHOW_IMPLICIT);
                currentActiveTextView = thisview;
            }
        };

        View.OnClickListener hideEditTextOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainNamesEditText.clearFocus();
                mainNamesEditText.setVisibility(View.GONE);
                if(currentActiveTextView!=null && !((mainNamesEditText.getText().toString()).equals("")) && mainNamesEditText.getText().toString().length()>=3) {
                    currentActiveTextView.setText(mainNamesEditText.getText().toString());
                    if(currentActiveTextView.getId()==p2l1player1name.getId()) {
                        p2l2player1name.setText(mainNamesEditText.getText().toString());
                    } else if(currentActiveTextView.getId()==p2l1player2name.getId()) {
                        p2l2player2name.setText(mainNamesEditText.getText().toString());
                    } else if(currentActiveTextView.getId()==p2l2player1name.getId()) {
                        p2l1player1name.setText(mainNamesEditText.getText().toString());
                    } else if(currentActiveTextView.getId()==p2l2player2name.getId()) {
                        p2l1player2name.setText(mainNamesEditText.getText().toString());
                    }
                    if(currentActiveTextView.getId() == playerNameBtnComTextView.getId()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        String username = mainNamesEditText.getText().toString();
                        editor.putString("username",username);
                        editor.apply();
                        usernamehomescreen.setText(username);
                        stcsusernametextview.setText(username);
                        playerNameBtnComTextView.setText(username);
                    }
                }
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mainNamesEditText.getWindowToken(), 0);
                } catch (Exception e) {};
            }
        };

        playonline.setOnTouchListener(clickBounceEffect);
        playonline.setOnClickListener(noInternetOnClick);
        playwithfriends.setOnTouchListener(clickBounceEffect);
        playwithfriends.setOnClickListener(noInternetOnClick);
        computer.setOnTouchListener(clickBounceEffect);
        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giveClickSound();
                computerlayout.setScaleX(0.0f);computerlayout.setScaleY(0.0f);
                computerlayout.setVisibility(View.VISIBLE);
                computerlayout.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        computerlayout.animate().setListener(null);
                    }
                }).start();
            }
        });
        passnplay.setOnTouchListener(clickBounceEffect);
        passnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giveClickSound();
                passnplaylayout.setScaleX(0.0f);
                passnplaylayout.setScaleY(0.0f);
                passnplaylayout.setVisibility(View.VISIBLE);
                passnplaylayout.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        passnplaylayout.animate().setListener(null);
                    }
                }).start();
            }
        });



        settingspopupbg.setOnTouchListener(opaqueLayout);
        shopbutton.setOnTouchListener(clickEffect);
        shopbutton.setOnClickListener(noInternetOnClick);
        exitgamebtn.setOnTouchListener(clickEffect);
        exitAppLayout = findViewById(R.id.confirmexitlayout);
        exitgamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitAppLayout.setScaleX(0.0f);exitAppLayout.setScaleY(0.0f);
                exitAppLayout.setVisibility(View.VISIBLE);
                exitAppLayout.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        exitAppLayout.animate().setListener(null);
                    }
                }).start();
            }
        });
        exitYesBtn=findViewById(R.id.imageView49);exitNoBtn=findViewById(R.id.imageView50);
        exitYesBtn.setOnTouchListener(clickEffect);exitNoBtn.setOnTouchListener(clickEffect);

        exitYesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
            }
        });
        exitNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitAppLayout.animate().scaleX(0.0f).scaleY(0.0f).setInterpolator(new AnticipateInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        exitAppLayout.setVisibility(View.GONE);
                    }
                }).start();
            }
        });
        settingsbtn.setOnTouchListener(clickEffect);
        settingsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giveClickSound();
                settingspopupbg.setScaleX(0.0f);settingspopupbg.setScaleY(0.0f);
                settingspopupbg.setVisibility(View.VISIBLE);
                settingspopupbg.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        settingspopupbg.animate().setListener(null);
                    }
                }).start();
            }
        });
        invitefriendsbtn.setOnTouchListener(clickEffect);
        invitefriendsbtn.setOnClickListener(noInternetOnClick);
        tmntbtn.setOnTouchListener(clickEffect);
        tmntbtn.setOnClickListener(noInternetOnClick);
        profiletop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statisticslayout.setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.imageView22).setOnClickListener(noInternetOnClick);

        //setting listeners for views of statistics view

        statisticslayout.setOnTouchListener(opaqueLayout);

        stcsusernamebtn.setOnTouchListener(clickEffect);
        stcsbackbtn.setOnTouchListener(clickEffect);
        stcseditprofile.setOnTouchListener(clickEffect);
        stcslgwfb.setOnTouchListener(clickEffect);
        stcslgwggl.setOnTouchListener(clickEffect);
        stcslgwpg.setOnTouchListener(clickEffect);
        stcsfshare.setOnTouchListener(clickEffect);

        stcsusernamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statisticslayout.setVisibility(View.GONE);
                findViewById(R.id.guestprofilelayout).setVisibility(View.VISIBLE);
            }
        });
        stcsprofpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statisticslayout.setVisibility(View.GONE);
                findViewById(R.id.guestprofilelayout).setVisibility(View.VISIBLE);
            }
        });
        stcsbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statisticslayout.setVisibility(View.GONE);
            }
        });
        stcseditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statisticslayout.setVisibility(View.GONE);
                findViewById(R.id.guestprofilelayout).setVisibility(View.VISIBLE);
            }
        });
        stcslgwfb.setOnClickListener(noInternetOnClick);
        stcslgwggl.setOnClickListener(noInternetOnClick);
        stcslgwpg.setOnClickListener(noInternetOnClick);
        stcsfshare.setOnClickListener(noInternetOnClick);


        //setting listeners to views of settings view

        stgsbackbtn.setOnTouchListener(clickEffect);stgssoundonoffbtn.setOnTouchListener(clickEffect);stgsmusiconoffbtn.setOnTouchListener(clickEffect);stgssharebtn.setOnTouchListener(clickEffect);stgslikebtn.setOnTouchListener(clickEffect);stgsgeturpverifiedbtn.setOnTouchListener(clickEffect);stgsplaybtn.setOnTouchListener(clickEffect);stgslanguagebtn.setOnTouchListener(clickEffect);stgsselectbtn.setOnTouchListener(clickEffect);stgsviewbtn1.setOnTouchListener(clickEffect);stgsfaqbtn.setOnTouchListener(clickEffect);stgscomposebtn.setOnTouchListener(clickEffect);stgsrulesbtn.setOnTouchListener(clickEffect);stgsviewbtn2.setOnTouchListener(clickEffect);stgsmoregamesbtn.setOnTouchListener(clickEffect);stgslboardbtn.setOnTouchListener(clickEffect);stgsviewbtn3.setOnTouchListener(clickEffect);stgseditbtn.setOnTouchListener(clickEffect);stgsinfobtn.setOnTouchListener(clickEffect);stgsdelaccountbtn.setOnTouchListener(clickEffect);stgsfbbtn.setOnTouchListener(clickEffect);stgsinstabtn.setOnTouchListener(clickEffect);stgstwitterbtn.setOnTouchListener(clickEffect);stgsytbtn.setOnTouchListener(clickEffect);stgsmoregameslongbtn.setOnTouchListener(clickEffect);

        stgsbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingspopupbg.animate().scaleX(0.0f).scaleY(0.0f).setInterpolator(new AnticipateInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        settingspopupbg.setVisibility(View.GONE);
                    }
                }).start();
            }
        });
        stgssoundonoffbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSoundOn)
                {
                    sharedPreferences.edit().putBoolean("sound",false).apply();
                    isSoundOn = false;
                    stgssoundonoffbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.soundoff,null));
                } else {
                    sharedPreferences.edit().putBoolean("sound",true).apply();
                    isSoundOn = true;
                    giveClickSound();
                    stgssoundonoffbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.soundon,null));
                }
            }
        });
        stgsmusiconoffbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isMusicOn)
                {
                    sharedPreferences.edit().putBoolean("music",false).apply();
                    isMusicOn = false;
                    m.stop();
                    m.release();
                    stgsmusiconoffbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.musicoff,null));
                } else {
                    giveClickSound();
                    sharedPreferences.edit().putBoolean("music",true).apply();
                    isMusicOn = true;
                    m = MediaPlayer.create(HomeActivity.this,R.raw.music);
                    m.start();
                    stgsmusiconoffbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.musicon,null));
                }
            }
        });
        stgssharebtn.setOnClickListener(noInternetOnClick);stgslikebtn.setOnClickListener(noInternetOnClick);stgsgeturpverifiedbtn.setOnClickListener(noInternetOnClick);stgsplaybtn.setOnClickListener(noInternetOnClick);stgslanguagebtn.setOnClickListener(noInternetOnClick);stgsselectbtn.setOnClickListener(noInternetOnClick);stgsviewbtn1.setOnClickListener(noInternetOnClick);stgsfaqbtn.setOnClickListener(noInternetOnClick);stgscomposebtn.setOnClickListener(noInternetOnClick);stgsrulesbtn.setOnClickListener(noInternetOnClick);stgsviewbtn2.setOnClickListener(noInternetOnClick);stgsmoregamesbtn.setOnClickListener(noInternetOnClick);stgslboardbtn.setOnClickListener(noInternetOnClick);stgsviewbtn3.setOnClickListener(noInternetOnClick);stgseditbtn.setOnClickListener(noInternetOnClick);stgsinfobtn.setOnClickListener(noInternetOnClick);stgsdelaccountbtn.setOnClickListener(noInternetOnClick);stgsfbbtn.setOnClickListener(noInternetOnClick);stgsinstabtn.setOnClickListener(noInternetOnClick);stgstwitterbtn.setOnClickListener(noInternetOnClick);stgsytbtn.setOnClickListener(noInternetOnClick);stgsmoregameslongbtn.setOnClickListener(noInternetOnClick);


        // setting listeners to first login layout views
        lgnwfbbtn.setOnTouchListener(clickEffect);snwgglbtn.setOnTouchListener(clickEffect);snwpgbtn.setOnTouchListener(clickEffect);playasguestbtn.setOnTouchListener(clickEffect);
        lgnwfbbtn.performClick();
        lgnwfbbtn.setOnClickListener(noInternetOnClick);snwgglbtn.setOnClickListener(noInternetOnClick);snwpgbtn.setOnClickListener(noInternetOnClick);
        playasguestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giveClickSound();
                findViewById(R.id.loginscreen).setVisibility(View.GONE);
                findViewById(R.id.guestprofilelayout).setVisibility(View.VISIBLE);
            }
        });

        // setting listeners for second login layout/guest profile layout

        guestprofilecreateoreditlayout.setOnClickListener(hideEditTextOnClick);
        pselecturctrybtn.setOnTouchListener(clickEffect);plgnwfbbtn.setOnTouchListener(clickEffect);psnwgglbtn.setOnTouchListener(clickEffect);psnwpgbtn.setOnTouchListener(clickEffect);pcontinuebtn.setOnTouchListener(clickEffect);
        pselecturctrybtn.setOnClickListener(noInternetOnClick);plgnwfbbtn.setOnClickListener(noInternetOnClick);psnwgglbtn.setOnClickListener(noInternetOnClick);psnwpgbtn.setOnClickListener(noInternetOnClick);




        playerNameBtnComTextView.setOnClickListener(editTextOnClick);


        pcontinuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giveClickSound();
                findViewById(R.id.guestprofilelayout).setVisibility(View.GONE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("logged", true);
                editor.putString("username",playerNameBtnComTextView.getText().toString());
                editor.apply();
            }
        });

        findViewById(R.id.imageView23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoInternet();
            }
        });

        findViewById(R.id.imageView26).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoInternet();
            }
        });

        findViewById(R.id.imageView27).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoInternet();
            }
        });


        // setting listeners for passnplay layout 1 views
        passnplaylayout.setOnTouchListener(opaqueLayout);

        pnptokentype1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pnptokentype2.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.piecetype2unselected,null));
                pnptokentype1.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.piecetype1selected,null));
                tokenNormal = true;
            }
        });
        pnptokentype2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pnptokentype1.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.piecetype1unselected,null));
                pnptokentype2.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.piecetype2selected,null));
                tokenNormal = false;
            }
        });
        pnpqmark.setOnTouchListener(clickEffect);pnpqmark.setOnClickListener(noInternetOnClick);
        pnpclassic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pnpteamup.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.teamupunselectedtab,null));
                pnpquick.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.quickunselectedtab,null));
                pnpclassic.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.classicselectedtab,null));
                pnpgametype=1;
            }
        });
        pnpteamup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pnpquick.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.quickunselectedtab,null));
                pnpclassic.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.classicunselectedtab,null));
                pnpteamup.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.teamupselectedtab,null));
                pnpgametype=2;
            }
        });
        pnpquick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pnpteamup.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.teamupunselectedtab,null));
                pnpclassic.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.classicunselectedtab,null));
                pnpquick.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.quickselectedtab,null));
                pnpgametype=3;
            }
        });
        pnpbackbtn.setOnTouchListener(clickEffect);
        pnpbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passnplaylayout.animate().scaleX(0.0f).scaleY(0.0f).setInterpolator(new AnticipateInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        passnplaylayout.setVisibility(View.GONE);
                    }
                }).start();
            }
        });
        pnpnext.setOnTouchListener(clickEffect);
        pnpnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passnplaylayout.setVisibility(View.GONE);
                if(pnpgametype==1 || pnpgametype==3) {
                    p2pbg.callOnClick();
                    passnplayclassiclayout.setVisibility(View.VISIBLE);
                    p3botcount = 0;
                    p3bot1.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    p3bot2.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    p3bot3.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    p4bot1.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    p4bot2.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    p4bot3.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    p4bot4.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    p4bot5.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    p4bot6.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);

                    if(pnpgametype==3) {
                        p5pbg.setVisibility(View.GONE);
                        p6pbg.setVisibility(View.GONE);
                        p5pbgtxt.setVisibility(View.GONE);
                        p6pbgtxt.setVisibility(View.GONE);
                        pnpclassicplay1tknout.setVisibility(View.GONE);
                    } else {
                        p5pbg.setVisibility(View.VISIBLE);
                        p6pbg.setVisibility(View.VISIBLE);
                        p5pbgtxt.setVisibility(View.VISIBLE);
                        p6pbgtxt.setVisibility(View.VISIBLE);
                        pnpclassicplay1tknout.setVisibility(View.VISIBLE);
                    }

                    if (tokenNormal) {
                        // 2p pieces
                        p2l1piece1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.bluepiece, null));
                        p2l1piece2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiece, null));
                        p2l2piece1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiece, null));
                        p2l2piece2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiece, null));
                        p2l1piece1.setScaleX(1.0f);
                        p2l1piece1.setScaleY(1.0f);
                        p2l1piece2.setScaleX(1.0f);
                        p2l1piece2.setScaleY(1.0f);
                        p2l2piece1.setScaleX(1.0f);
                        p2l2piece1.setScaleY(1.0f);
                        p2l2piece2.setScaleX(1.0f);
                        p2l2piece2.setScaleY(1.0f);
                        // 3p pieces
                        int dp3ppadding = (int) onedp * 2;
                        p3l1pb.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.bluepiece, null));
                        p3l1pr.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiece, null));
                        p3l1pg.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiece, null));
                        p3l1py.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiece, null));
                        p3l2pb.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.bluepiece, null));
                        p3l2pr.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiece, null));
                        p3l2pg.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiece, null));
                        p3l2py.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiece, null));
                        p3l3pb.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.bluepiece, null));
                        p3l3pr.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiece, null));
                        p3l3pg.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiece, null));
                        p3l3py.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiece, null));
                        p3l1pb.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l1pr.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l1pg.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l1py.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l2pb.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l2pr.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l2pg.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l2py.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l3pb.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l3pr.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l3pg.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l3py.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        //4p pieces
                        p4p1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.bluepiece, null));
                        p4p2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiece, null));
                        p4p3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiece, null));
                        p4p4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiece, null));
                        p4p5.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.orangepiece, null));
                        p4p6.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.purplepiece, null));
                    } else {
                        // 2p pieces
                        p2l1piece1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.bluepiecestylish, null));
                        p2l1piece2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiecestylish, null));
                        p2l2piece1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiecestylish, null));
                        p2l2piece2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiecestylish, null));
                        p2l1piece1.setScaleX(0.8f);
                        p2l1piece1.setScaleY(0.8f);
                        p2l1piece2.setScaleX(0.8f);
                        p2l1piece2.setScaleY(0.8f);
                        p2l2piece1.setScaleX(0.8f);
                        p2l2piece1.setScaleY(0.8f);
                        p2l2piece2.setScaleX(0.8f);
                        p2l2piece2.setScaleY(0.8f);
                        // 3p pieces
                        int dp3ppadding = (int) onedp * 9;
                        p3l1pb.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.bluepiecestylish, null));
                        p3l1pr.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiecestylish, null));
                        p3l1pg.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiecestylish, null));
                        p3l1py.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiecestylish, null));
                        p3l2pb.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.bluepiecestylish, null));
                        p3l2pr.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiecestylish, null));
                        p3l2pg.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiecestylish, null));
                        p3l2py.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiecestylish, null));
                        p3l3pb.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.bluepiecestylish, null));
                        p3l3pr.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiecestylish, null));
                        p3l3pg.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiecestylish, null));
                        p3l3py.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiecestylish, null));
                        p3l1pb.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l1pr.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l1pg.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l1py.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l2pb.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l2pr.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l2pg.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l2py.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l3pb.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l3pr.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l3pg.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        p3l3py.setPadding(dp3ppadding, dp3ppadding, dp3ppadding, dp3ppadding);
                        //4p pieces
                        p4p1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.bluepiecestylish, null));
                        p4p2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiecestylish, null));
                        p4p3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiecestylish, null));
                        p4p4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiecestylish, null));
                        p4p5.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.orangepiecestylish, null));
                        p4p6.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.purplepiecestylish, null));
                    }
                } else {
                    giveClickSound();
                    pnpteamuplayout.setVisibility(View.VISIBLE);
                    if(tokenNormal) {
                        t2t1p1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.bluepiece, null));
                        t2t1p2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiece, null));
                        t2t2p1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiece, null));
                        t2t2p2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiece, null));
                    } else {
                        t2t1p1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.bluepiecestylish, null));
                        t2t1p2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiecestylish, null));
                        t2t2p1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiecestylish, null));
                        t2t2p2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiecestylish, null));
                    }
                }
            }
        });


        // setting listeners for pnp classic layout
        ConstraintLayout.LayoutParams thisparams = (ConstraintLayout.LayoutParams) passnplayclassiclayout.getLayoutParams();
        thisparams.height = (int)(pxHeight+getStatusBarHeight()+60);

        //passnplayclassiclayout.setOnTouchListener(opaqueLayout);
        passnplayclassiclayout.setOnClickListener(hideEditTextOnClick);
        pnpclassicbackbtn.setOnTouchListener(clickEffect);
        pnpclassicbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passnplayclassiclayout.setVisibility(View.GONE);
                passnplaylayout.setVisibility(View.VISIBLE);
                currentplayermode = 0;
            }
        });

        pnpclassicplaybtn.setOnTouchListener(clickEffect);
        pnpclassicplaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean bot1,bot2,bot3,bot4,bot5,bot6;
                bot1=bot2=bot3=bot4=bot5=bot6=false;
                if(currentplayermode==3) {
                    bot1 = isP3bot1Active;
                    bot2 = isP3bot2Active;
                    bot3 = isP3bot3Active;
                } else if(currentplayermode>=4){
                    bot1 = isP4bot1Active;
                    bot2 = isP4bot2Active;
                    bot3 = isP4bot3Active;
                    bot4 = isP4bot4Active;
                    bot5 = isP4bot5Active;
                    bot6 = isP4bot6Active;
                }
                if(currentplayermode==2) {
                    String colour = isP2L1True?"blue":"red";
                    Intent i = new Intent(HomeActivity.this,MainActivity.class);
                    i.putExtra("type", pnpgametype);
                    i.putExtra("nop",2);
                    i.putExtra("color",colour); // in case of 2 players it is main color of 3rd section.
                    i.putExtra("player3name",p2l1player1name.getText().toString());
                    i.putExtra("player2name",p2l1player2name.getText().toString());
                    i.putExtra("normalPiece",tokenNormal);
                    startActivity(i);
                    overridePendingTransition(0,0);
                } else if(currentplayermode==3) {
                    String[] colours = {"blue","red","green","yellow"};
                    String calculatedpname1="1",calculatedpname2="2",calculatedpname3="3";
                    switch (getThisPlayerNameIndex(colours[getLeftOverColour()],colours[p3l1value-1])) {
                        case 1:
                            calculatedpname1=p3player1name.getText().toString();
                            break;
                        case 2:
                            calculatedpname2=p3player1name.getText().toString();
                            break;
                        case 3:
                            calculatedpname3=p3player1name.getText().toString();
                            break;
                    }
                    switch (getThisPlayerNameIndex(colours[getLeftOverColour()],colours[p3l2value-1])) {
                        case 1:
                            calculatedpname1=p3player2name.getText().toString();
                            break;
                        case 2:
                            calculatedpname2=p3player2name.getText().toString();
                            break;
                        case 3:
                            calculatedpname3=p3player2name.getText().toString();
                            break;
                    }
                    switch (getThisPlayerNameIndex(colours[getLeftOverColour()],colours[p3l3value-1])) {
                        case 1:
                            calculatedpname1=p3player3name.getText().toString();
                            break;
                        case 2:
                            calculatedpname2=p3player3name.getText().toString();
                            break;
                        case 3:
                            calculatedpname3=p3player3name.getText().toString();
                            break;
                    }
                    /*calculatedpname1=p3player1name.getText().toString();
                    calculatedpname2=p3player2name.getText().toString();
                    calculatedpname3=p3player3name.getText().toString();*/
                    Intent i = new Intent(HomeActivity.this,MainActivity.class);
                    i.putExtra("type", pnpgametype);
                    i.putExtra("nop",3);
                    i.putExtra("color",colours[getLeftOverColour()]); // in case of 3 players it is left over color placed at 4th section.
                    i.putExtra("player1name",calculatedpname1);
                    i.putExtra("player2name",calculatedpname2);
                    i.putExtra("player3name",calculatedpname3);
                    i.putExtra("player1color",colours[p3l1value-1]);
                    i.putExtra("player2color",colours[p3l2value-1]);
                    i.putExtra("player3color",colours[p3l3value-1]);
                    i.putExtra("normalPiece",tokenNormal);
                    i.putExtra("player1bot",bot1);
                    i.putExtra("player2bot",bot2);
                    i.putExtra("player3bot",bot3);
                    i.putExtra("player4bot",bot4);
                    i.putExtra("player5bot",bot5);
                    i.putExtra("player6bot",bot6);
                    startActivity(i);
                    overridePendingTransition(0,0);
                    //Toast.makeText(HomeActivity.this, colours[getLeftOverColour()], Toast.LENGTH_SHORT).show();
                } else if(currentplayermode==4) {
                    Intent i = new Intent(HomeActivity.this,MainActivity.class);
                    i.putExtra("type", pnpgametype);
                    i.putExtra("nop",4);
                    i.putExtra("color","blue");
                    i.putExtra("player1name",p4pname2.getText().toString());
                    i.putExtra("player2name",p4pname3.getText().toString());
                    i.putExtra("player3name",p4pname1.getText().toString());
                    i.putExtra("player4name",p4pname4.getText().toString());
                    i.putExtra("normalPiece",tokenNormal);
                    i.putExtra("player1bot",bot1);
                    i.putExtra("player2bot",bot2);
                    i.putExtra("player3bot",bot3);
                    i.putExtra("player4bot",bot4);
                    i.putExtra("player5bot",bot5);
                    i.putExtra("player6bot",bot6);
                    startActivity(i);
                    overridePendingTransition(0,0);
                } else {
                    showNoInternet();
                }
            }
        });

        pnpclassicplay1tknout.setOnTouchListener(clickEffect);
        pnpclassicplay1tknout.setOnClickListener(noInternetOnClick);

        p2pbg.setOnTouchListener(clickEffect);
        p3pbg.setOnTouchListener(clickEffect);
        p4pbg.setOnTouchListener(clickEffect);
        p5pbg.setOnTouchListener(clickEffect);
        p6pbg.setOnTouchListener(clickEffect);

        p2pbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentplayermode!=2) {
                    currentplayermode = 2;
                    unselectAllAndSelectThisP(view);
                    p3layout.setVisibility(View.GONE);
                    p4layout.setVisibility(View.GONE);
                    p2layout.setVisibility(View.VISIBLE);
                    botsCountMonitor.setText(("0/0"));
                    isP2L1True = true;
                } else {
                    p2pbg.setColorFilter(ResourcesCompat.getColor(getResources(),R.color.dim_color_dark,null), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        p3pbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentplayermode!=3) {
                    currentplayermode = 3;
                    unselectAllAndSelectThisP(view);
                    p2layout.setVisibility(View.GONE);
                    p4layout.setVisibility(View.GONE);
                    p3layout.setVisibility(View.VISIBLE);
                    botsCountMonitor.setText(("0/1"));
                    p3bot1.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    p3bot2.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    p3bot3.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    p3bot1.setVisibility(View.VISIBLE);
                    p3bot2.setVisibility(View.VISIBLE);
                    p3bot3.setVisibility(View.VISIBLE);
                    if(isP3bot1Active) { p3player1name.setText("Player 1"); p3player1name.setOnClickListener(editTextOnClick);}
                    if(isP3bot2Active) { p3player2name.setText("Player 2"); p3player2name.setOnClickListener(editTextOnClick);}
                    if(isP3bot3Active) { p3player3name.setText("Player 3"); p3player3name.setOnClickListener(editTextOnClick);}
                    isP3bot1Active = isP3bot2Active = isP3bot3Active = false;
                } else {
                    p3pbg.setColorFilter(ResourcesCompat.getColor(getResources(),R.color.dim_color_dark,null), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        p4pbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentplayermode!=4) {
                currentplayermode = 4;
                unselectAllAndSelectThisP(view);
                p2layout.setVisibility(View.GONE);
                p3layout.setVisibility(View.GONE);
                p4layout.setVisibility(View.VISIBLE);
                botsCountMonitor.setText(("0/2"));
                p4p5.setVisibility(View.GONE);
                player5namebg.setVisibility(View.GONE);
                p4p6.setVisibility(View.GONE);
                player6namebg.setVisibility(View.GONE);
                p4bot5.setVisibility(View.GONE);
                p4bot6.setVisibility(View.GONE);
                p4bot1.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                p4bot2.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                p4bot3.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                p4bot4.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    p4bot1.setVisibility(View.VISIBLE);
                    p4bot2.setVisibility(View.VISIBLE);
                    p4bot3.setVisibility(View.VISIBLE);
                    p4bot4.setVisibility(View.VISIBLE);
                    if(isP4bot1Active) { p4pname1.setText("Player 1"); p4pname1.setOnClickListener(editTextOnClick);}
                    if(isP4bot2Active) { p4pname2.setText("Player 2"); p4pname2.setOnClickListener(editTextOnClick);}
                    if(isP4bot3Active) { p4pname3.setText("Player 3"); p4pname3.setOnClickListener(editTextOnClick);}
                    if(isP4bot4Active) { p4pname4.setText("Player 4"); p4pname4.setOnClickListener(editTextOnClick);}
                    if(isP4bot5Active) { p4pname5.setText("Player 5"); p4pname5.setOnClickListener(editTextOnClick);}
                    if(isP4bot6Active) { p4pname6.setText("Player 6"); p4pname6.setOnClickListener(editTextOnClick);}
                isP4bot1Active = isP4bot2Active = isP4bot3Active = isP4bot4Active = isP4bot5Active = isP4bot6Active = false; p4botcount = 0;
                if (tokenNormal) {
                    p4p2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiece, null));
                    p4p3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiece, null));
                    p4p4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiece, null));
                } else {
                    p4p2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiecestylish, null));
                    p4p3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiecestylish, null));
                    p4p4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiecestylish, null));
                }
            } else {
                    p4pbg.setColorFilter(ResourcesCompat.getColor(getResources(),R.color.dim_color_dark,null), PorterDuff.Mode.SRC_ATOP);
            }
            }
        });

        p5pbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentplayermode!=5) {
                    currentplayermode = 5;
                unselectAllAndSelectThisP(view);
                p2layout.setVisibility(View.GONE);
                p3layout.setVisibility(View.GONE);
                p4layout.setVisibility(View.VISIBLE);
                botsCountMonitor.setText(("0/3"));
                p4p5.setVisibility(View.VISIBLE);
                player5namebg.setVisibility(View.VISIBLE);
                p4p6.setVisibility(View.GONE);
                player6namebg.setVisibility(View.GONE);
                p4bot5.setVisibility(View.VISIBLE);
                p4bot6.setVisibility(View.GONE);
                p4bot1.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                p4bot2.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                p4bot3.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                p4bot4.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                p4bot5.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    p4bot1.setVisibility(View.VISIBLE);
                    p4bot2.setVisibility(View.VISIBLE);
                    p4bot3.setVisibility(View.VISIBLE);
                    p4bot4.setVisibility(View.VISIBLE);
                    if(isP4bot1Active) { p4pname1.setText("Player 1"); p4pname1.setOnClickListener(editTextOnClick);}
                    if(isP4bot2Active) { p4pname2.setText("Player 2"); p4pname2.setOnClickListener(editTextOnClick);}
                    if(isP4bot3Active) { p4pname3.setText("Player 3"); p4pname3.setOnClickListener(editTextOnClick);}
                    if(isP4bot4Active) { p4pname4.setText("Player 4"); p4pname4.setOnClickListener(editTextOnClick);}
                    if(isP4bot5Active) { p4pname5.setText("Player 5"); p4pname5.setOnClickListener(editTextOnClick);}
                    if(isP4bot6Active) { p4pname6.setText("Player 6"); p4pname6.setOnClickListener(editTextOnClick);}
                isP4bot1Active = isP4bot2Active = isP4bot3Active = isP4bot4Active = isP4bot5Active = isP4bot6Active = false; p4botcount = 0;
                if (tokenNormal) {
                    p4p2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.orangepiece, null));
                    p4p3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiece, null));
                    p4p4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiece, null));
                    p4p5.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiece, null));
                } else {
                    p4p2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.orangepiecestylish, null));
                    p4p3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiecestylish, null));
                    p4p4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiecestylish, null));
                    p4p5.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiecestylish, null));
                }
            } else {
                    p5pbg.setColorFilter(ResourcesCompat.getColor(getResources(),R.color.dim_color_dark,null), PorterDuff.Mode.SRC_ATOP);
            }
            }
        });

        p6pbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentplayermode!=6) {
                currentplayermode = 6;
                unselectAllAndSelectThisP(view);
                p2layout.setVisibility(View.GONE);
                p3layout.setVisibility(View.GONE);
                p4layout.setVisibility(View.VISIBLE);
                botsCountMonitor.setText(("0/4"));
                p4p5.setVisibility(View.VISIBLE);
                player5namebg.setVisibility(View.VISIBLE);
                p4p6.setVisibility(View.VISIBLE);
                player6namebg.setVisibility(View.VISIBLE);
                p4bot5.setVisibility(View.VISIBLE);
                p4bot6.setVisibility(View.VISIBLE);
                p4bot1.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                p4bot2.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                p4bot3.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                p4bot4.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                p4bot5.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                p4bot6.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    p4bot1.setVisibility(View.VISIBLE);
                    p4bot2.setVisibility(View.VISIBLE);
                    p4bot3.setVisibility(View.VISIBLE);
                    p4bot4.setVisibility(View.VISIBLE);
                    if(isP4bot1Active) { p4pname1.setText("Player 1"); p4pname1.setOnClickListener(editTextOnClick);}
                    if(isP4bot2Active) { p4pname2.setText("Player 2"); p4pname2.setOnClickListener(editTextOnClick);}
                    if(isP4bot3Active) { p4pname3.setText("Player 3"); p4pname3.setOnClickListener(editTextOnClick);}
                    if(isP4bot4Active) { p4pname4.setText("Player 4"); p4pname4.setOnClickListener(editTextOnClick);}
                    if(isP4bot5Active) { p4pname5.setText("Player 5"); p4pname5.setOnClickListener(editTextOnClick);}
                    if(isP4bot6Active) { p4pname6.setText("Player 6"); p4pname6.setOnClickListener(editTextOnClick);}
                isP4bot1Active = isP4bot2Active = isP4bot3Active = isP4bot4Active = isP4bot5Active = isP4bot6Active = false; p4botcount = 0;
                if (tokenNormal) {
                    p4p2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiece, null));
                    p4p3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.purplepiece, null));
                    p4p4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiece, null));
                    p4p5.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiece, null));
                    p4p6.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.orangepiece, null));
                } else {
                    p4p2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellowpiecestylish, null));
                    p4p3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.purplepiecestylish, null));
                    p4p4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redpiecestylish, null));
                    p4p5.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.greenpiecestylish, null));
                    p4p6.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.orangepiecestylish, null));
                }
            } else {
                    p6pbg.setColorFilter(ResourcesCompat.getColor(getResources(),R.color.dim_color_dark,null), PorterDuff.Mode.SRC_ATOP);
            }
            }
        });

        // settings listeners for 2p layout
        p2l1inactiveview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p2l1inactiveview.setVisibility(View.GONE);
                p2l2inactiveview.setVisibility(View.VISIBLE);
                isP2L1True = true;
                p2l2tick.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.greenunselectedcircle,null));
                p2l1tick.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.greenselectedcircle,null));
            }
        });

        p2l2inactiveview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p2l2inactiveview.setVisibility(View.GONE);
                p2l1inactiveview.setVisibility(View.VISIBLE);
                isP2L1True = false;
                p2l1tick.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.greenunselectedcircle,null));
                p2l2tick.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.greenselectedcircle,null));
            }
        });

        p2l1player1name.setOnClickListener(editTextOnClick);

        p2l1player2name.setOnClickListener(editTextOnClick);

        p2l2player1name.setOnClickListener(editTextOnClick);

        p2l2player2name.setOnClickListener(editTextOnClick);

        // setting listeners for 3p layout
        p3l1pb.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            unselectAllP3L1AndSelectThis(view);
            if(p3l2value==1) {
                p3l2value = p3l1value;
                unselectAllP3L2AndSelectThis(getP3L2PbyIndex(p3l2value));
                p3l1value = 1;
            } else if(p3l3value==1) {
                p3l3value = p3l1value;
                unselectAllP3L3AndSelectThis(getP3L3PbyIndex(p3l3value));
                p3l1value = 1;
            }
            p3l1value = 1;
        }});
        p3l1pr.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            unselectAllP3L1AndSelectThis(view);
            if(p3l2value==2) {
                p3l2value = p3l1value;
                unselectAllP3L2AndSelectThis(getP3L2PbyIndex(p3l2value));
                p3l1value = 2;
            } else if(p3l3value==2) {
                p3l3value = p3l1value;
                unselectAllP3L3AndSelectThis(getP3L3PbyIndex(p3l3value));
                p3l1value = 2;
            }p3l1value = 2;
        }});
        p3l1pg.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            unselectAllP3L1AndSelectThis(view);
            if(p3l2value==3) {
                p3l2value = p3l1value;
                unselectAllP3L2AndSelectThis(getP3L2PbyIndex(p3l2value));
                p3l1value = 3;
            } else if(p3l3value==3) {
                p3l3value = p3l1value;
                unselectAllP3L3AndSelectThis(getP3L3PbyIndex(p3l3value));
                p3l1value = 3;
            }p3l1value = 3;
        }});
        p3l1py.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            unselectAllP3L1AndSelectThis(view);
            if(p3l2value==4) {
                p3l2value = p3l1value;
                unselectAllP3L2AndSelectThis(getP3L2PbyIndex(p3l2value));
                p3l1value = 4;
            } else if(p3l3value==4) {
                p3l3value = p3l1value;
                unselectAllP3L3AndSelectThis(getP3L3PbyIndex(p3l3value));
                p3l1value = 4;
            }p3l1value = 4;
        }});
        p3l2pb.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            unselectAllP3L2AndSelectThis(view);
            if(p3l1value==1) {
                p3l1value = p3l2value;
                unselectAllP3L1AndSelectThis(getP3L1PbyIndex(p3l1value));
                p3l2value = 1;
            } else if(p3l3value==1) {
                p3l3value = p3l2value;
                unselectAllP3L3AndSelectThis(getP3L3PbyIndex(p3l3value));
                p3l2value = 1;
            }p3l2value = 1;

        }});
        p3l2pr.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            unselectAllP3L2AndSelectThis(view);
            if(p3l1value==2) {
                p3l1value = p3l2value;
                unselectAllP3L1AndSelectThis(getP3L1PbyIndex(p3l1value));
                p3l2value = 2;
            } else if(p3l3value==2) {
                p3l3value = p3l2value;
                unselectAllP3L3AndSelectThis(getP3L3PbyIndex(p3l3value));
                p3l2value = 2;
            }p3l2value = 2;
        }});
        p3l2pg.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            unselectAllP3L2AndSelectThis(view);
            if(p3l1value==3) {
                p3l1value = p3l2value;
                unselectAllP3L1AndSelectThis(getP3L1PbyIndex(p3l1value));
                p3l2value = 3;
            } else if(p3l3value==3) {
                p3l3value = p3l2value;
                unselectAllP3L3AndSelectThis(getP3L3PbyIndex(p3l3value));
                p3l2value = 3;
            }p3l2value = 3;
        }});
        p3l2py.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            unselectAllP3L2AndSelectThis(view);
            if(p3l1value==4) {
                p3l1value = p3l2value;
                unselectAllP3L1AndSelectThis(getP3L1PbyIndex(p3l1value));
                p3l2value = 4;
            } else if(p3l3value==4) {
                p3l3value = p3l2value;
                unselectAllP3L3AndSelectThis(getP3L3PbyIndex(p3l3value));
                p3l2value = 4;
            }p3l2value = 4;
        }});
        p3l3pb.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            unselectAllP3L3AndSelectThis(view);
            if(p3l2value==1) {
                p3l2value = p3l3value;
                unselectAllP3L2AndSelectThis(getP3L2PbyIndex(p3l2value));
                p3l3value = 1;
            } else if(p3l1value==1) {
                p3l1value = p3l3value;
                unselectAllP3L1AndSelectThis(getP3L1PbyIndex(p3l1value));
                p3l3value = 1;
            }p3l3value = 1;
        }});
        p3l3pr.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            unselectAllP3L3AndSelectThis(view);
            if(p3l2value==2) {
                p3l2value = p3l3value;
                unselectAllP3L2AndSelectThis(getP3L2PbyIndex(p3l2value));
                p3l3value = 2;
            } else if(p3l1value==2) {
                p3l1value = p3l3value;
                unselectAllP3L1AndSelectThis(getP3L1PbyIndex(p3l1value));
                p3l3value = 2;
            }p3l3value = 2;
        }});
        p3l3pg.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            unselectAllP3L3AndSelectThis(view);
            if(p3l2value==3) {
                p3l2value = p3l3value;
                unselectAllP3L2AndSelectThis(getP3L2PbyIndex(p3l2value));
                p3l3value = 3;
            } else if(p3l1value==3) {
                p3l1value = p3l3value;
                unselectAllP3L1AndSelectThis(getP3L1PbyIndex(p3l1value));
                p3l3value = 3;
            }p3l3value = 3;
        }});
        p3l3py.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            unselectAllP3L3AndSelectThis(view);
            if(p3l2value==4) {
                p3l2value = p3l3value;
                unselectAllP3L2AndSelectThis(getP3L2PbyIndex(p3l2value));
                p3l3value = 4;
            } else if(p3l1value==4) {
                p3l1value = p3l3value;
                unselectAllP3L1AndSelectThis(getP3L1PbyIndex(p3l1value));
                p3l3value = 4;
            }p3l3value = 4;
        }});

        p3player1name.setOnClickListener(editTextOnClick);
        p3player2name.setOnClickListener(editTextOnClick);
        p3player3name.setOnClickListener(editTextOnClick);

        p3bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isP3bot2Active && !isP3bot3Active){
                if (!isP3bot1Active) {
                    p3bot1.clearColorFilter();
                    p3bot2.setVisibility(View.INVISIBLE);
                    p3bot3.setVisibility(View.INVISIBLE);
                    //p3bot2.setAlpha(0.5f);p3bot3.setAlpha(0.5f);
                    isP3bot1Active = true;
                    p3player1name.setOnClickListener(null);
                    p3player1name.setText("Bot");
                    botsCountMonitor.setText(("1/1"));
                } else {
                    p3bot1.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    p3bot2.setVisibility(View.VISIBLE);
                    p3bot3.setVisibility(View.VISIBLE);
                    //p3bot2.setAlpha(1.0f);p3bot3.setAlpha(1.0f);
                    p3player1name.setText("Player 1");
                    p3player1name.setOnClickListener(editTextOnClick);
                    isP3bot1Active = false;
                    botsCountMonitor.setText(("0/1"));
                }
            }
            }
        });
        p3bot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isP3bot1Active && !isP3bot3Active) {
                    if (!isP3bot2Active) {
                        p3bot2.clearColorFilter();
                        p3bot1.setVisibility(View.INVISIBLE);
                        p3bot3.setVisibility(View.INVISIBLE);
                        //p3bot1.setAlpha(0.5f);p3bot3.setAlpha(0.5f);
                        p3player2name.setText("Bot");
                        p3player2name.setOnClickListener(null);
                        isP3bot2Active = true;botsCountMonitor.setText(("1/1"));
                    } else {
                        p3bot2.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                        p3bot1.setVisibility(View.VISIBLE);
                        p3bot3.setVisibility(View.VISIBLE);
                        //p3bot1.setAlpha(1.0f);p3bot3.setAlpha(1.0f);
                        p3player2name.setText("Player 2");
                        p3player2name.setOnClickListener(editTextOnClick);
                        isP3bot2Active = false;botsCountMonitor.setText(("0/1"));
                    }
                }
            }
        });
        p3bot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isP3bot2Active && !isP3bot1Active) {
                    if (!isP3bot3Active) {
                        p3bot3.clearColorFilter();
                        p3bot2.setVisibility(View.INVISIBLE);
                        p3bot1.setVisibility(View.INVISIBLE);botsCountMonitor.setText(("1/1"));
                        p3player3name.setOnClickListener(null);
                        p3player3name.setText("Bot");
                        //p3bot2.setAlpha(0.5f);p3bot1.setAlpha(0.5f);
                        isP3bot3Active = true;
                    } else {
                        p3bot3.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                        p3bot2.setVisibility(View.VISIBLE);
                        p3bot1.setVisibility(View.VISIBLE);
                        p3player3name.setText("Player 3");
                        p3player3name.setOnClickListener(editTextOnClick);
                        //p3bot2.setAlpha(1.0f);p3bot1.setAlpha(1.0f);
                        isP3bot3Active = false;botsCountMonitor.setText(("0/1"));
                    }
                }
            }
        });

        // setting listeners for 4p layout

        p4bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((p4botcount <= currentplayermode-2) && !isP4bot1Active)
                {
                    p4botcount++;
                    isP4bot1Active = true;
                    p4bot1.clearColorFilter();
                    p4pname1.setOnClickListener(null);
                    p4pname1.setText("Bot");
                } else if((p4botcount <= currentplayermode-2) && isP4bot1Active) {
                    p4botcount--;
                    isP4bot1Active = false;
                    p4pname1.setOnClickListener(editTextOnClick);
                    p4pname1.setText("Player 1");
                    p4bot1.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    if(p4botcount==currentplayermode-3) checkAndMakeOtherBotsVisible();
                }
                if(p4botcount==currentplayermode-2) {
                    checkAndMakeOtherBotsInvisible();
                }
                botsCountMonitor.setText((p4botcount+"/"+((currentplayermode-2))));
            }
        });

        p4bot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((p4botcount <= currentplayermode-2) && !isP4bot2Active)
                {
                    p4botcount++;
                    isP4bot2Active = true;
                    p4bot2.clearColorFilter();
                    p4pname2.setOnClickListener(null);
                    p4pname2.setText("Bot");
                } else if((p4botcount <= currentplayermode-2) && isP4bot2Active) {
                    p4botcount--;
                    isP4bot2Active = false;
                    p4pname2.setOnClickListener(editTextOnClick);
                    p4pname2.setText("Player 2");
                    p4bot2.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    if(p4botcount==currentplayermode-3) checkAndMakeOtherBotsVisible();
                }
                if(p4botcount==currentplayermode-2) {
                    checkAndMakeOtherBotsInvisible();
                }botsCountMonitor.setText((p4botcount+"/"+((currentplayermode-2))));
            }
        });

        p4bot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((p4botcount <= currentplayermode-2) && !isP4bot3Active)
                {
                    p4botcount++;
                    isP4bot3Active = true;
                    p4bot3.clearColorFilter();
                    p4pname3.setOnClickListener(null);
                    p4pname3.setText("Bot");
                } else if((p4botcount <= currentplayermode-2) && isP4bot3Active) {
                    p4botcount--;
                    isP4bot3Active = false;
                    p4pname3.setOnClickListener(editTextOnClick);
                    p4pname3.setText("Player 3");
                    p4bot3.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    if(p4botcount==currentplayermode-3) checkAndMakeOtherBotsVisible();
                }
                if(p4botcount==currentplayermode-2) {
                    checkAndMakeOtherBotsInvisible();
                }botsCountMonitor.setText((p4botcount+"/"+((currentplayermode-2))));
            }
        });

        p4bot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((p4botcount <= currentplayermode-2) && !isP4bot4Active)
                {
                    p4botcount++;
                    isP4bot4Active = true;
                    p4bot4.clearColorFilter();
                    p4pname4.setOnClickListener(null);
                    p4pname4.setText("Bot");
                } else if((p4botcount <= currentplayermode-2) && isP4bot4Active) {
                    p4botcount--;
                    isP4bot4Active = false;
                    p4pname4.setOnClickListener(editTextOnClick);
                    p4pname4.setText("Player 4");
                    p4bot4.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    if(p4botcount==currentplayermode-3) checkAndMakeOtherBotsVisible();
                }
                if(p4botcount==currentplayermode-2) {
                    checkAndMakeOtherBotsInvisible();
                }botsCountMonitor.setText((p4botcount+"/"+((currentplayermode-2))));
            }
        });

        p4bot5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((p4botcount <= currentplayermode-2) && !isP4bot5Active)
                {
                    p4botcount++;
                    isP4bot5Active = true;
                    p4bot5.clearColorFilter();
                    p4pname5.setOnClickListener(null);
                    p4pname5.setText("Bot");
                } else if((p4botcount <= currentplayermode-2) && isP4bot5Active) {
                    p4botcount--;
                    isP4bot5Active = false;
                    p4pname5.setOnClickListener(editTextOnClick);
                    p4pname5.setText("Player 5");
                    p4bot5.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    if(p4botcount==currentplayermode-3) checkAndMakeOtherBotsVisible();
                }
                if(p4botcount==currentplayermode-2) {
                    checkAndMakeOtherBotsInvisible();
                }botsCountMonitor.setText((p4botcount+"/"+((currentplayermode-2))));
            }
        });

        p4bot6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((p4botcount <= currentplayermode-2) && !isP4bot6Active)
                {
                    p4botcount++;
                    isP4bot6Active = true;
                    p4bot6.clearColorFilter();
                    p4pname6.setOnClickListener(null);
                    p4pname6.setText("Bot");
                } else if((p4botcount <= currentplayermode-2) && isP4bot6Active) {
                    p4botcount--;
                    isP4bot6Active = false;
                    p4pname6.setOnClickListener(editTextOnClick);
                    p4pname6.setText("Player 6");
                    p4bot6.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.dim_color_dark, null), PorterDuff.Mode.SRC_ATOP);
                    if(p4botcount==currentplayermode-3) checkAndMakeOtherBotsVisible();
                }
                if(p4botcount==currentplayermode-2) {
                    checkAndMakeOtherBotsInvisible();
                }botsCountMonitor.setText((p4botcount+"/"+((currentplayermode-2))));
            }
        });

        p4pname1.setOnClickListener(editTextOnClick);
        p4pname2.setOnClickListener(editTextOnClick);
        p4pname3.setOnClickListener(editTextOnClick);
        p4pname4.setOnClickListener(editTextOnClick);
        p4pname5.setOnClickListener(editTextOnClick);
        p4pname6.setOnClickListener(editTextOnClick);

        // setting listeners for pnpteamup layout views
        thisparams = (ConstraintLayout.LayoutParams) pnpteamuplayout.getLayoutParams();
        thisparams.height = (int)(pxHeight+getStatusBarHeight()+60);

        pnpteamuplayout.setOnTouchListener(opaqueLayout);
        pnpteamuplayout.setOnClickListener(hideEditTextOnClick);
        pnpt2backbtn.setOnTouchListener(clickEffect);

        pnpt2help.setOnTouchListener(clickEffect);
        pnpt2help.setOnClickListener(noInternetOnClick);

        pnpt2backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pnpteamuplayout.setVisibility(View.GONE);
                passnplaylayout.setVisibility(View.VISIBLE);
            }
        });

        pnpt2playbtn.setOnTouchListener(clickEffect);
        pnpt2playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,MainActivity.class);
                i.putExtra("type", pnpgametype);
                i.putExtra("nop",4);
                i.putExtra("color","blue");
                i.putExtra("player1name",pnpt2t2p1name.getText().toString());
                i.putExtra("player2name",pnpt2t1p2name.getText().toString());
                i.putExtra("player3name",pnpt2t1p1name.getText().toString());
                i.putExtra("player4name",pnpt2t2p2name.getText().toString());
                i.putExtra("normalPiece",tokenNormal);
                i.putExtra("player1bot",false);
                i.putExtra("player2bot",false);
                i.putExtra("player3bot",false);
                i.putExtra("player4bot",false);
                i.putExtra("player5bot",false);
                i.putExtra("player6bot",false);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });

        pnpt2t1p1name.setOnClickListener(editTextOnClick);
        pnpt2t1p2name.setOnClickListener(editTextOnClick);
        pnpt2t2p1name.setOnClickListener(editTextOnClick);
        pnpt2t2p2name.setOnClickListener(editTextOnClick);

        // setting listeners for computer layout
        computerlayout.setOnTouchListener(opaqueLayout);
        comphelpbtn.setOnTouchListener(clickEffect);
        comphelpbtn.setOnClickListener(noInternetOnClick);
        compclassicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(computergametype!=1) {
                    computergametype=1;
                    comprushmodebtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.computerrushunselectedtab,null));
                    compclassicbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.computerclassicselectedtab,null));
                }
            }
        });

        comprushmodebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(computergametype!=2) {
                    computergametype = 2;
                    comprushmodebtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.computerrushselectedtab,null));
                    compclassicbtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.computerclassicunselectedtab,null));
                }
            }
        });

        compbackbtn.setOnTouchListener(clickEffect);
        compbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computerlayout.animate().scaleX(0.0f).scaleY(0.0f).setInterpolator(new AnticipateInterpolator()).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        computerlayout.setVisibility(View.GONE);
                    }
                }).start();
            }
        });

        compnextbtn.setOnTouchListener(clickEffect);
        compnextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(computergametype!=1) {
                    findViewById(R.id.computerselecturplayerslayout).setVisibility(View.GONE);
                    comp2players = false;
                } else {
                    findViewById(R.id.computerselecturplayerslayout).setVisibility(View.VISIBLE);
                    comp2players = true;
                    players2tick.callOnClick();
                }
                computerlayout.setVisibility(View.GONE);
                computerclassiclayout.setVisibility(View.VISIBLE);
            }
        });

        // setting listeners for computer classic layout
        computerclassiclayout.setOnTouchListener(opaqueLayout);

        compl2bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compl2bt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.bluetick,null));
                compl2rt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.redcircle_unfilled,null));
                compl2gt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.greencircle_unfilled,null));
                compl2yt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.yellowcircle_unfilled,null));
                computerselectedcolor = 1;
            }
        });
        compl2rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compl2bt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.bluecircle_unfilled,null));
                compl2rt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.redtick,null));
                compl2gt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.greencircle_unfilled,null));
                compl2yt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.yellowcircle_unfilled,null));
                computerselectedcolor = 2;
            }
        });
        compl2gt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compl2bt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.bluecircle_unfilled,null));
                compl2rt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.redcircle_unfilled,null));
                compl2gt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.greentick,null));
                compl2yt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.yellowcircle_unfilled,null));
                computerselectedcolor = 3;
            }
        });
        compl2yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compl2bt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.bluecircle_unfilled,null));
                compl2rt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.redcircle_unfilled,null));
                compl2gt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.greencircle_unfilled,null));
                compl2yt.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.yellowtick,null));
                computerselectedcolor = 4;
            }
        });

        players2tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                players2tick.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.selectfill,null));
                players4tick.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.selectblank,null));
                comp2players = true;
            }
        });

        players4tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                players2tick.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.selectblank,null));
                players4tick.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.selectfill,null));
                comp2players = false;
            }
        });

        players2txtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                players2tick.callOnClick();
            }
        });

        players4txtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                players4tick.callOnClick();
            }
        });





        compl2backbtn.setOnTouchListener(clickEffect);
        compl2backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computerclassiclayout.setVisibility(View.GONE);
                computer.callOnClick();
            }
        });

        compl2playbtn.setOnTouchListener(clickEffect);
        compl2playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] colours = {"blue","red","green","yellow"};
                if (comp2players) {
                    Intent i = new Intent(HomeActivity.this, MainActivity.class);
                    i.putExtra("type", 4);
                    i.putExtra("nop", 2);
                    i.putExtra("color", colours[computerselectedcolor - 1]);
                    i.putExtra("player2name", "Computer");
                    i.putExtra("player3name", "You");
                    i.putExtra("normalPiece", true);
                    i.putExtra("player1bot", false);
                    i.putExtra("player2bot", true);
                    i.putExtra("player3bot", false);
                    i.putExtra("player4bot", false);
                    startActivity(i);
                    overridePendingTransition(0,0);
                } else {
                    Intent i = new Intent(HomeActivity.this,MainActivity.class);
                    i.putExtra("type",4);
                    i.putExtra("nop",4);
                    i.putExtra("color", colours[computerselectedcolor-1]);
                    i.putExtra("player1name","Computer 1");
                    i.putExtra("player2name","Computer 2");
                    i.putExtra("player3name","You");
                    i.putExtra("player4name","Computer 4");
                    i.putExtra("normalPiece",true);
                    i.putExtra("player1bot",false);
                    i.putExtra("player2bot",true);
                    i.putExtra("player3bot",true);
                    i.putExtra("player4bot",true);
                    startActivity(i);
                    overridePendingTransition(0,0);
                }
            }
        });

        editTextOKBtn.setOnClickListener(hideEditTextOnClick);
    }

    private int getThisPlayerNameIndex(String leftOverColour, String colour) {
        switch (leftOverColour) {
            case "red": // blue
                switch (colour) {
                    case "green":
                        return 3;
                    case "yellow":
                        return 1;
                    case "blue":
                        return 2;
                }
                break;
            case "green": // red
                switch (colour) {
                    case "yellow":
                        return 3;
                    case "blue":
                        return 1;
                    case "red":
                        return 2;
                }
                break;
            case "blue": // green
                switch (colour) {
                    case "red":
                        return 3;
                    case "green":
                        return 1;
                    case "yellow":
                        return 2;
                }
                break;
            case "yellow":// yellow
                switch (colour) {
                    case "blue":
                        return 3;
                    case "red":
                        return 1;
                    case "green":
                        return 2;
                }
                break;
        }
        return 1;
    }

    private int getLeftOverColour() {
        int value = 1;
        int[] values = {p3l1value,p3l2value,p3l3value};
        for(int i=1;i<=4;i++) {
            for(int v:values) {
                if(value==v) {
                    value++;
                }
            }
        }
        return value-1;
    }


    private void checkAndMakeOtherBotsVisible() {
        p4bot1.setVisibility(View.VISIBLE);
        p4bot2.setVisibility(View.VISIBLE);
        p4bot3.setVisibility(View.VISIBLE);
        p4bot4.setVisibility(View.VISIBLE);
        if(currentplayermode==5) { p4bot5.setVisibility(View.VISIBLE); }
        else if(currentplayermode==6) { p4bot5.setVisibility(View.VISIBLE); p4bot6.setVisibility(View.VISIBLE); }
    }

    private void checkAndMakeOtherBotsInvisible() {
        if(isP4bot1Active) p4bot1.setVisibility(View.VISIBLE); else p4bot1.setVisibility(View.INVISIBLE);
        if(isP4bot2Active) p4bot2.setVisibility(View.VISIBLE); else p4bot2.setVisibility(View.INVISIBLE);
        if(isP4bot3Active) p4bot3.setVisibility(View.VISIBLE); else p4bot3.setVisibility(View.INVISIBLE);
        if(isP4bot4Active) p4bot4.setVisibility(View.VISIBLE); else p4bot4.setVisibility(View.INVISIBLE);
        if(currentplayermode==5) {
            if (isP4bot5Active) p4bot5.setVisibility(View.VISIBLE);
            else p4bot5.setVisibility(View.INVISIBLE);
        } else if(currentplayermode==6) {
            if (isP4bot5Active) p4bot5.setVisibility(View.VISIBLE);
            else p4bot5.setVisibility(View.INVISIBLE);
            if (isP4bot6Active) p4bot6.setVisibility(View.VISIBLE);
            else p4bot6.setVisibility(View.INVISIBLE);
        }
    }
    void unselectAllP3L1AndSelectThis(View v) {
        p3l1pb.setBackground(null);
        p3l1pr.setBackground(null);
        p3l1pg.setBackground(null);
        p3l1py.setBackground(null);
        v.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.greenunselectedcircle,null));
    }

    void unselectAllP3L2AndSelectThis(View v) {
        p3l2pb.setBackground(null);
        p3l2pr.setBackground(null);
        p3l2pg.setBackground(null);
        p3l2py.setBackground(null);
        v.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.greenunselectedcircle,null));
    }

    void unselectAllP3L3AndSelectThis(View v) {
        p3l3pb.setBackground(null);
        p3l3pr.setBackground(null);
        p3l3pg.setBackground(null);
        p3l3py.setBackground(null);
        v.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.greenunselectedcircle,null));
    }


    private ImageView getP3L1PbyIndex(int i) {
        switch (i)
        {
            case 1:
                return p3l1pb;
            case 2:
                return p3l1pr;
            case 3:
                return p3l1pg;
            case 4:
                return p3l1py;
            default:
                return p3l1pb;
        }
    }
    private ImageView getP3L2PbyIndex(int i) {
        switch (i)
        {
            case 1:
                return p3l2pb;
            case 2:
                return p3l2pr;
            case 3:
                return p3l2pg;
            case 4:
                return p3l2py;
            default:
                return p3l2pb;
        }
    }
    private ImageView getP3L3PbyIndex(int i) {
        switch (i)
        {
            case 1:
                return p3l3pb;
            case 2:
                return p3l3pr;
            case 3:
                return p3l3pg;
            case 4:
                return p3l3py;
            default:
                return p3l3pb;
        }
    }

    private void unselectAllAndSelectThisP(View v) {
        p2pbg.clearColorFilter();
        p3pbg.clearColorFilter();
        p4pbg.clearColorFilter();
        p5pbg.clearColorFilter();
        p6pbg.clearColorFilter();
        giveClickSound();
        ((ImageView)v).setColorFilter(ResourcesCompat.getColor(getResources(),R.color.dim_color_dark,null), PorterDuff.Mode.SRC_ATOP);
    }

    private void giveClickSound() {
        if(isSoundOn) {
            clickSoundEffect.seekTo(0);
            clickSoundEffect.start();
        }
    }

    private void initViews() {

        sharedPreferences = getSharedPreferences("LudoModUser",MODE_PRIVATE);

        isMusicOn = sharedPreferences.getBoolean("music",true);
        isSoundOn = sharedPreferences.getBoolean("sound",true);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        onedp = displayMetrics.density;
        dpHeight = displayMetrics.heightPixels/displayMetrics.density;
        dpWidth = displayMetrics.widthPixels/displayMetrics.density;
        pxWidth = displayMetrics.widthPixels;
        pxHeight = displayMetrics.heightPixels-getStatusBarHeight();
        topNavBar = findViewById(R.id.topNavBar);
        coinAndDiamondBG = findViewById(R.id.coinndiamondbg);
        diamondscount1 = findViewById(R.id.textView2);
        coinscount1 = findViewById(R.id.textView);
        diamondscount2 = findViewById(R.id.textView13);
        coinscount2 = findViewById(R.id.textView12);


        profiletop = findViewById(R.id.imageView19); // 18 -> bg
        usernamehomescreen = findViewById(R.id.textView4);
        playonline = findViewById(R.id.imageView8);
        playwithfriends = findViewById(R.id.imageView9);
        computer = findViewById(R.id.imageView4);
        passnplay = findViewById(R.id.imageView5);
        shopbutton = findViewById(R.id.imageView11);
        exitgamebtn = findViewById(R.id.imageView12);
        settingsbtn = findViewById(R.id.imageView13);
        invitefriendsbtn = findViewById(R.id.imageView14);
        tmntbtn = findViewById(R.id.imageView16);

        // settings layout views
        settingspopupbg = findViewById(R.id.settingspopup);
        stgsbackbtn = findViewById(R.id.settingsbackbutton);
        stgssharebtn = findViewById(R.id.settingssharebutton);
        stgslikebtn = findViewById(R.id.settingslikebutton);
        stgsgeturpverifiedbtn = findViewById(R.id.getverifiedbtnid);
        stgssoundonoffbtn = findViewById(R.id.soundtogglebtn);
        stgsmusiconoffbtn = findViewById(R.id.musictogglebtn);
        stgsplaybtn = findViewById(R.id.playbtnid);
        stgslanguagebtn = findViewById(R.id.languagebtnid);
        stgsselectbtn = findViewById(R.id.themesbtnid);
        stgsviewbtn1 = findViewById(R.id.storebtnid);
        stgsfaqbtn = findViewById(R.id.faqbtnid);
        stgscomposebtn = findViewById(R.id.composebtnid);
        stgsrulesbtn = findViewById(R.id.rulesbtnid);
        stgsviewbtn2 = findViewById(R.id.privacypolicybtnid);
        stgsmoregamesbtn = findViewById(R.id.moregamesbtnid);
        stgslboardbtn = findViewById(R.id.lboardbtnid);
        stgsviewbtn3 = findViewById(R.id.view2btnid);
        stgseditbtn = findViewById(R.id.editbtnid);
        stgsinfobtn = findViewById(R.id.infobtnid);
        stgsdelaccountbtn = findViewById(R.id.deletebtnid);
        stgsfbbtn = findViewById(R.id.fbiconbtn);
        stgsinstabtn = findViewById(R.id.instaiconbtn);
        stgstwitterbtn = findViewById(R.id.twittericonbtn);
        stgsytbtn = findViewById(R.id.yticonbtn);
        stgsmoregameslongbtn = findViewById(R.id.settingsmoregamesbtn);

        // statistics layout views
        statisticslayout = findViewById(R.id.statisticslayout);
        stcsusernamebtn = findViewById(R.id.imageView61);
        stcsusernametextview = findViewById(R.id.textView18);
        stcsprofpic = findViewById(R.id.imageprofilestatisticstab);
        stcsbackbtn = findViewById(R.id.imageView78);
        stcseditprofile = findViewById(R.id.imageView79);
        stcslgwfb = findViewById(R.id.imageView372);
        stcslgwggl = findViewById(R.id.imageView402);
        stcslgwpg = findViewById(R.id.imageView452);
        stcsfshare = findViewById(R.id.imageView85);

        // first login screen views
        firstLoginLayout = findViewById(R.id.loginscreen);
        lgnwfbbtn = findViewById(R.id.imageView23);
        snwgglbtn = findViewById(R.id.imageView26);
        snwpgbtn = findViewById(R.id.imageView27);
        playasguestbtn = findViewById(R.id.imageView24);

        // second login/guest profile layout views
        guestprofilecreateoreditlayout = findViewById(R.id.guestprofilelayout);
        pselecturctrybtn = findViewById(R.id.imageView35);
        playerNameBtnComTextView = findViewById(R.id.textView9);
        pprofilepicview = findViewById(R.id.profilepicprofileedit);
        plgnwfbbtn = findViewById(R.id.imageView37);
        psnwgglbtn = findViewById(R.id.imageView40);
        psnwpgbtn = findViewById(R.id.imageView45);
        pcontinuebtn = findViewById(R.id.imageView46);

        // passnplay main layout views
        passnplaylayout = findViewById(R.id.passnplayscreen1);
        passnplayclassiclayout = findViewById(R.id.pnpclassiclayout);
        pnptokentype1 = findViewById(R.id.piecetype1layout);
        pnptokentype2 = findViewById(R.id.piecetype2layout);
        pnpqmark = findViewById(R.id.imageView58);
        pnpclassic = findViewById(R.id.imageView59);
        pnpteamup = findViewById(R.id.imageView87);
        pnpquick = findViewById(R.id.imageView88);
        pnpbackbtn = findViewById(R.id.imageView90);
        pnpnext = findViewById(R.id.imageView89);

        //passnplay classic layout views
        p2pbg = findViewById(R.id.npbg2p);
        p3pbg = findViewById(R.id.npbg3p);
        p4pbg = findViewById(R.id.npbg4p);
        p5pbg = findViewById(R.id.npbg5p);
        p6pbg = findViewById(R.id.npbg6p);
        p5pbgtxt = findViewById(R.id.p5ptxt);
        p6pbgtxt = findViewById(R.id.p6ptxt);
        pnpclassicbackbtn = findViewById(R.id.choosecolornnamebackbtn);
        pnpclassicplaybtn = findViewById(R.id.passnplaymainplaybtn);
        pnpclassicplay1tknout = findViewById(R.id.playonetokenoutbutton);

        // p2,p3,p4 layout inside passnplay classic
        p2layout = findViewById(R.id.classicp2layout);
        p3layout = findViewById(R.id.classicp3layout);
        p4layout = findViewById(R.id.classicp4layout);
        botsCountMonitor = findViewById(R.id.textView11);

        // p2 layout inside views
        p2l1 = findViewById(R.id.bluegreentype);
        p2l2 = findViewById(R.id.redyellowtype);
        p2l1inactiveview = findViewById(R.id.view2);
        p2l2inactiveview = findViewById(R.id.view3);
        p2l1tick = findViewById(R.id.imageView105);
        p2l2tick = findViewById(R.id.imageView106);
        p2l1piece1 = findViewById(R.id.imageView97);
        p2l1piece2 = findViewById(R.id.imageView98);
        p2l2piece1 = findViewById(R.id.imageView99);
        p2l2piece2 = findViewById(R.id.imageView100);
        p2l1player1name = findViewById(R.id.t1name1textview);
        p2l1player2name = findViewById(R.id.t1name2textview);
        p2l2player1name = findViewById(R.id.t2name1textview);
        p2l2player2name = findViewById(R.id.t2name2textview);

        // p3 layout inside views
        p3l1pb = findViewById(R.id.p3pl1b);
        p3l1pr = findViewById(R.id.p3pl1r);
        p3l1pg = findViewById(R.id.p3pl1g);
        p3l1py = findViewById(R.id.p3pl1y);
        p3l2pb = findViewById(R.id.p3pl2b);
        p3l2pr = findViewById(R.id.p3pl2r);
        p3l2pg = findViewById(R.id.p3pl2g);
        p3l2py = findViewById(R.id.p3pl2y);
        p3l3pb = findViewById(R.id.p3pl3b);
        p3l3pr = findViewById(R.id.p3pl3r);
        p3l3pg = findViewById(R.id.p3pl3g);
        p3l3py = findViewById(R.id.p3pl3y);

        p3bot1 = findViewById(R.id.p3botp1);
        p3bot2 = findViewById(R.id.p3botp2);
        p3bot3 = findViewById(R.id.p3botp3);

        p3player1name = findViewById(R.id.p3p1nametextview);
        p3player2name = findViewById(R.id.p3p2nametextview);
        p3player3name = findViewById(R.id.p3p3nametextview);

        //p4 layout inside views
        p4p1 = findViewById(R.id.p4p1p);
        p4p2 = findViewById(R.id.p4p2p);
        p4p3 = findViewById(R.id.p4p3p);
        p4p4 = findViewById(R.id.p4p4p);
        p4p5 = findViewById(R.id.p4p5p);
        p4p6 = findViewById(R.id.p4p6p);
        p4bot1 = findViewById(R.id.p4botp1);
        p4bot2 = findViewById(R.id.p4botp2);
        p4bot3 = findViewById(R.id.p4botp3);
        p4bot4 = findViewById(R.id.p4botp4);
        p4bot5 = findViewById(R.id.p4botp5);
        p4bot6 = findViewById(R.id.p4botp6);
        p4pname1 = findViewById(R.id.p4p1nametextview);
        p4pname2 = findViewById(R.id.p4p2nametextview);
        p4pname3 = findViewById(R.id.p4p3nametextview);
        p4pname4 = findViewById(R.id.p4p4nametextview);
        p4pname5 = findViewById(R.id.p4p5nametextview);
        p4pname6 = findViewById(R.id.p4p6nametextview);
        player5namebg = findViewById(R.id.p4p5name);
        player6namebg = findViewById(R.id.p4p6name);

        // pnpteamup layout views
        pnpteamuplayout = findViewById(R.id.pnpteamuplayout);
        pnpt2help = findViewById(R.id.teamuphelpbtn);
        pnpt2playbtn = findViewById(R.id.teamupplaybtn);
        pnpt2backbtn = findViewById(R.id.teamupbackbtn);
        t2t1p1 = findViewById(R.id.t1bp);
        t2t1p2 = findViewById(R.id.t1gp);
        t2t2p1 = findViewById(R.id.t2rp);
        t2t2p2 = findViewById(R.id.t2yp);
        pnpt2t1p1name = findViewById(R.id.t1p1name);
        pnpt2t1p2name = findViewById(R.id.t1p2name);
        pnpt2t2p1name = findViewById(R.id.t2p1name);
        pnpt2t2p2name = findViewById(R.id.t2p2name);

        // computer layout views
        computerlayout = findViewById(R.id.computerlayout1);
        compclassicbtn = findViewById(R.id.computerclassicbtn);
        comprushmodebtn = findViewById(R.id.computerrushmodebtn);
        comphelpbtn = findViewById(R.id.computerhelpbtn);
        compbackbtn = findViewById(R.id.computerselectgamebackbtn);
        compnextbtn = findViewById(R.id.computernxtbtn);

        // computer classic layout views
        computerclassiclayout = findViewById(R.id.computerclassiclayout);
        compl2bt = findViewById(R.id.imageView122);
        compl2rt = findViewById(R.id.imageView123);
        compl2gt = findViewById(R.id.imageView124);
        compl2yt = findViewById(R.id.imageView125);
        players2tick = findViewById(R.id.imageView127);
        players4tick = findViewById(R.id.imageView128);
        players2txtview = findViewById(R.id.comp2playerstxtviewnouse);
        players4txtview = findViewById(R.id.comp4playerstxtviewnouse);
        compl2backbtn = findViewById(R.id.compl2backbtn2);
        compl2playbtn = findViewById(R.id.computermainplaybtn);

        // editText and editText OK btn
        mainNamesEditText = findViewById(R.id.editTextText);
        editTextOKBtn = findViewById(R.id.edittextokbtn);




        nointernet = findViewById(R.id.nointernet);
        nointernethandler = new Handler();
        nointernetrunnable = new Runnable() {
            @Override
            public void run() {
                nointernet.setScaleX(1.0f);
                nointernet.setScaleY(1.0f);
                nointernet.animate().scaleX(0.0f).scaleY(0.0f).setInterpolator(new AnticipateInterpolator()).setDuration(500).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        nointernet.setVisibility(View.GONE);
                        nointernethandler.removeCallbacks(nointernetrunnable);
                    }
                }).start();
            }
        };
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
    public float getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    void showNoInternet() {
        nointernet.setVisibility(View.VISIBLE);
        nointernet.setScaleX(0.0f);nointernet.setScaleY(0.0f);
        nointernethandler.removeCallbacks(nointernetrunnable);
        nointernet.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(300).setListener(null).start();
        nointernethandler.postDelayed(nointernetrunnable,1500);
    }

    @Override
    public void onBackPressed() {
        if(exitAppLayout.getVisibility()==View.VISIBLE) {
            exitNoBtn.callOnClick();
        } else {
            exitgamebtn.callOnClick();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isMusicOn) {
            m.pause();
        }
    }

    @Override
    public void onClick(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Drawable d;
        switch (view.getId())
        {
            case R.id.p1:
                setProfilePic(1);
                editor.putInt("dp",1);
                editor.apply();
                break;
            case R.id.p2:
                setProfilePic(2);
                editor.putInt("dp",2);
                editor.apply();
                break;
            case R.id.p3:
                setProfilePic(3);
                editor.putInt("dp",3);
                editor.apply();
                break;
            case R.id.p4:
                setProfilePic(4);
                editor.putInt("dp",4);
                editor.apply();
                break;
            case R.id.p5layout:
                setProfilePic(5);
                editor.putInt("dp",5);
                editor.apply();
                break;
            case R.id.p6layout:
                setProfilePic(6);
                editor.putInt("dp",6);
                editor.apply();
                break;
            case R.id.p7layout:
                setProfilePic(7);
                editor.putInt("dp",7);
                editor.apply();
                break;
            case R.id.p8layout:
                setProfilePic(8);
                editor.putInt("dp",8);
                editor.apply();
                break;
            case R.id.p9layout:
                setProfilePic(9);
                editor.putInt("dp",9);
                editor.apply();
                break;
            case R.id.p10layout:
                setProfilePic(10);
                editor.putInt("dp",10);
                editor.apply();
                break;
            case R.id.p11layout:
                setProfilePic(11);
                editor.putInt("dp",11);
                editor.apply();
                break;
            case R.id.p12layout:
                setProfilePic(12);
                editor.putInt("dp",12);
                editor.apply();
                break;
            case R.id.p13layout:
                setProfilePic(13);
                editor.putInt("dp",12);
                editor.apply();
                break;
            case R.id.p14layout:
                setProfilePic(14);
                editor.putInt("dp",14);
                editor.apply();
                break;
            case R.id.p15layout:
                setProfilePic(15);
                editor.putInt("dp",15);
                editor.apply();
                break;
            case R.id.p16layout:
                setProfilePic(16);
                editor.putInt("dp",16);
                editor.apply();
                break;
            case R.id.p17layout:
                setProfilePic(17);
                editor.putInt("dp",17);
                editor.apply();
                break;
            case R.id.p18layout:
                setProfilePic(18);
                editor.putInt("dp",18);
                editor.apply();
                break;
            case R.id.p19layout:
                setProfilePic(19);
                editor.putInt("dp",19);
                editor.apply();
                break;
            case R.id.p20layout:
                setProfilePic(20);
                editor.putInt("dp",20);
                editor.apply();
                break;
            case R.id.p21layout:
                setProfilePic(21);
                editor.putInt("dp",21);
                editor.apply();
                break;
            case R.id.p22layout:
                setProfilePic(22);
                editor.putInt("dp",22);
                editor.apply();
                break;
            case R.id.p23layout:
                setProfilePic(23);
                editor.putInt("dp",23);
                editor.apply();
                break;
            case R.id.p24layout:
                setProfilePic(24);
                editor.putInt("dp",24);
                editor.apply();
                break;
            case R.id.p25layout:
                setProfilePic(25);
                editor.putInt("dp",25);
                editor.apply();
                break;
            case R.id.p26layout:
                setProfilePic(26);
                editor.putInt("dp",26);
                editor.apply();
                break;
            case R.id.p27layout:
                setProfilePic(27);
                editor.putInt("dp",27);
                editor.apply();
                break;
            case R.id.p28layout:
                setProfilePic(28);
                editor.putInt("dp",28);
                editor.apply();
                break;
            case R.id.p29layout:
                setProfilePic(29);
                editor.putInt("dp",29);
                editor.apply();
                break;
            case R.id.p30layout:
                setProfilePic(30);
                editor.putInt("dp",30);
                editor.apply();
                break;
            case R.id.p31layout:
                setProfilePic(31);
                editor.putInt("dp",31);
                editor.apply();
                break;
            case R.id.p32layout:
                setProfilePic(32);
                editor.putInt("dp",32);
                editor.apply();
                break;
            case R.id.p33layout:
                setProfilePic(33);
                editor.putInt("dp",32);
                editor.apply();
                break;
            case R.id.p34layout:
                setProfilePic(34);
                editor.putInt("dp",34);
                editor.apply();
                break;
            case R.id.p35layout:
                setProfilePic(35);
                editor.putInt("dp",35);
                editor.apply();
                break;
            case R.id.p36layout:
                setProfilePic(36);
                editor.putInt("dp",36);
                editor.apply();
                break;
            case R.id.p37layout:
                setProfilePic(37);
                editor.putInt("dp",37);
                editor.apply();
                break;
            case R.id.p38layout:
                setProfilePic(38);
                editor.putInt("dp",38);
                editor.apply();
                break;
            case R.id.p39layout:
                setProfilePic(39);
                editor.putInt("dp",39);
                editor.apply();
                break;
            case R.id.p40layout:
                setProfilePic(40);
                editor.putInt("dp",40);
                editor.apply();
                break;
            case R.id.p41layout:
                setProfilePic(41);
                editor.putInt("dp",41);
                editor.apply();
                break;
            case R.id.p42layout:
                setProfilePic(42);
                editor.putInt("dp",42);
                editor.apply();
                break;
            case R.id.p43layout:
                setProfilePic(43);
                editor.putInt("dp",43);
                editor.apply();
                break;
            case R.id.p44layout:
                setProfilePic(44);
                editor.putInt("dp",44);
                editor.apply();
                break;
            case R.id.p45layout:
                setProfilePic(45);
                editor.putInt("dp",45);
                editor.apply();
                break;
            case R.id.p46layout:
                setProfilePic(46);
                editor.putInt("dp",46);
                editor.apply();
                break;
            case R.id.p47layout:
                setProfilePic(47);
                editor.putInt("dp",47);
                editor.apply();
                break;
            case R.id.p48layout:
                setProfilePic(48);
                editor.putInt("dp",48);
                editor.apply();
                break;
            default:
                setProfilePic(0);
                editor.putInt("dp",0);
                editor.apply();
        }
    }

    void setProfilePic(int i) {
        Drawable d;
        switch (i)
        {
            case 1:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p1,null);
                break;
            case 2:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p2,null);
                break;
            case 3:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p3,null);
                break;
            case 4:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p4,null);
                break;
            case 5:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p5,null);
                break;
            case 6:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p6,null);
                break;
            case 7:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p7,null);
                break;
            case 8:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p8,null);
                break;
            case 9:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p9,null);
                break;
            case 10:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p10,null);
                break;
            case 11:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p11,null);
                break;
            case 12:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p12,null);
                break;
            case 13:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p13,null);
                break;
            case 14:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p14,null);
                break;
            case 15:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p15,null);
                break;
            case 16:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p16,null);
                break;
            case 17:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p17,null);
                break;
            case 18:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p18,null);
                break;
            case 19:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p19,null);
                break;
            case 20:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p20,null);
                break;
            case 21:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p21,null);
                break;
            case 22:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p22,null);
                break;
            case 23:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p23,null);
                break;
            case 24:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p24,null);
                break;
            case 25:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p25,null);
                break;
            case 26:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p26,null);
                break;
            case 27:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p27,null);
                break;
            case 28:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p28,null);
                break;
            case 29:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p29,null);
                break;
            case 30:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p30,null);
                break;
            case 31:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p31,null);
                break;
            case 32:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p32,null);
                break;
            case 33:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p33,null);
                break;
            case 34:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p34,null);
                break;
            case 35:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p35,null);
                break;
            case 36:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p36,null);
                break;
            case 37:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p37,null);
                break;
            case 38:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p38,null);
                break;
            case 39:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p39,null);
                break;
            case 40:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p40,null);
                break;
            case 41:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p41,null);
                break;
            case 42:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p42,null);
                break;
            case 43:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p43,null);
                break;
            case 44:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p44,null);
                break;
            case 45:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p45,null);
                break;
            case 46:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p46,null);
                break;
            case 47:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p47,null);
                break;
            case 48:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.p48,null);
                break;
            default:
                d = ResourcesCompat.getDrawable(getResources(),R.drawable.dpdefault,null);
        }
        pprofilepicview.setImageDrawable(d);
        profiletop.setImageDrawable(d);
        stcsprofpic.setImageDrawable(d);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isMusicOn) {
            if(!m.isPlaying()) {
                m.start();
            }
        }
    }
}