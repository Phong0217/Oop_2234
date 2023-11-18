package com.example.dictionary;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javax.speech.*;
public class Speaking {

//    public static String language = "en-gb";
//
//    public static String voiceNameUS;
//    public static String voiceNameUK;
//    //text to listen
//    String speaktext;

    public static String Name = "kevin16";

    //function that makes text audible
    public static void doSpeak(String speak) throws EngineException {

        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

        //creating an object of the Voice class
        Voice voice;
        //getting voice, here we have used kevin (male version) voice
        voice = VoiceManager.getInstance().getVoice(Name);
        if (voice != null) {
            //the Voice class allocate() method allocates this voice
            voice.allocate();
        }
        try {

            //sets the rate (words per minute i.e. 190) of the speech
            voice.setRate(165);
            //sets the baseline pitch (150) in hertz
            voice.setPitch(150);
            //sets the volume (10) of the voice
            voice.setVolume(30);
            //the speak() method speaks the specified text
            voice.speak(speak);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws EngineException {
        String speakText = "where are you from ?";
        System.out.print("Speaking: "+ speakText);
        Speaking obj = new Speaking();
        obj.doSpeak(speakText);

        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice[] voices = voiceManager.getVoices();
        for (Voice voice : voices) {
            System.out.print(voice.getName());
        }
    }

}
