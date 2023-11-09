package com.example.base;

import javax.speech.*;
import java.util.*;
import javax.speech.synthesis.*;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Pronunciation {
    //text to listen
    String speaktext;

    //function that makes text audible
    public void dospeak(String speak, String voiceName) {

        voiceName = "kevin16";
        //creating an object of the Voice class
        Voice voice;
        //getting voice, here we have used kevin (male version) voice
        voice = VoiceManager.getInstance().getVoice("kevin");
        if (voice != null) {
            //the Voice class allocate() method allocates this voice
            voice.allocate();
        }
        try {
            //sets the rate (words per minute i.e. 190) of the speech
            voice.setRate(190);
            //sets the baseline pitch (150) in hertz
            voice.setPitch(150);
            //sets the volume (10) of the voice
            voice.setVolume(10);
            //the speak() method speaks the specified text
            voice.speak(speak);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) throws EngineException {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

        String speakText = "How are you, today ?";
        System.out.print("Speaking: "+ speakText);
        Pronunciation obj = new Pronunciation();
        obj.dospeak(speakText, "kevin16");
    }
}  