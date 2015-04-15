package processing.sound;

public class MethClaInterface
{ 

  // load Library
  static {
    String osName = System.getProperty("os.name");
    String arch = System.getProperty("os.arch");

    if (osName.startsWith("Win")){
      if (arch.equals("x86")){
        System.loadLibrary("LIBWINPTHREAD-1");
        System.loadLibrary("LIBSNDFILE-1");
        System.loadLibrary("LIBGCC_S_SJLJ-1");
        System.loadLibrary("LIBMPG123-0");
        System.loadLibrary("LIBMETHCLA");
        System.loadLibrary("LIBMETHCLAINTERFACE"); 
      }
      else {
        System.loadLibrary("LIBWINPTHREAD-1");
        System.loadLibrary("LIBSNDFILE-1");
        System.loadLibrary("LIBMPG123-0");
        System.loadLibrary("LIBMETHCLA");
        System.loadLibrary("LIBMETHCLAINTERFACE");  
      }
    }  
    else if (osName.startsWith("Mac")){
      System.loadLibrary("MethClaInterface");
    }
    else if (osName.equals("Linux")){
      System.loadLibrary("MethClaInterface");
    }
  }
  // Functions I want
  
  // Engine 
  
  public native int[] mixPlay(int[] input, float[] amp);

  public native int engineNew(int sampleRate, int bufferSize );
  
  public native void engineStart();
  
  public native void engineStop();
  
  // general Synth methods
  
  public native void synthStop(int[] nodeId);
  
  // general Oscillator methods
    
  public native void oscSet(float freq, float amp, float add, float pos, int[] nodeId);

  public native void oscAudioSet(int[] freqId, int[] ampId, int[] addId, int[] posId, int[] nodeId);
  
  // Sine Wave Oscillator
    
  public native int[] sinePlay(float freq, float amp, float add, float pos);
    
  //Saw Wave Oscillator
  
  public native int[] sawPlay(float freq, float amp, float add, float pos);

  //Square Wave Oscillator
  
  public native int[] sqrPlay(float freq, float amp, float add, float pos);
  
  public native void sqrSet(float freq, float amp, float add, float pos, int[] nodeId); 
  
  // Triangle Wave Oscillator
  
  public native int[] triPlay(float freq, float amp, float add, float pos);
  
  // Pulse Wave Oscillator
  
  public native int[] pulsePlay(float freq, float width, float amp, float add, float pos);
  
  public native void pulseSet(float freq, float width, float amp, float add, float pos, int[] nodeId); 
  
  // Audio In

  public native int[] audioInStart(float amp, float add, float pos, int in);

  public native void audioInPlay(int nodeId);

  public native void audioInSet(float amp, float add, float pos, int[] nodeId);

  // SoundFile
  
  public native int[] soundFileInfo(String path);
    
  public native int[] soundFilePlayMono(float rate, float pos, float amp, float add, boolean loop, String path, float dur, int cue);
  
  public native int[] soundFilePlayMulti(float rate, float amp, float add, boolean loop, String path, float dur, int cue);
  
  public native void soundFileSetMono(float rate, float pos, float amp, float add, int[] nodeId);
  
  public native void soundFileSetStereo(float rate, float amp, float add, int[] nodeId);  
  
  // White Noise
  
  public native int[] whiteNoisePlay(float amp, float add, float pos);
  
  public native void whiteNoiseSet(float amp, float add, float pos, int[] nodeId);
  
  // Pink Noise
  
  public native int[] pinkNoisePlay(float amp, float add, float pos);
  
  public native void pinkNoiseSet(float amp, float add, float pos, int[] nodeId);

  // Brown Noise
    
  public native int[] brownNoisePlay(float amp, float add, float pos);
  
  public native void brownNoiseSet(float amp, float add, float pos, int[] nodeId);

  // Envelope
    
  public native int[] envelopePlay(int[] input, float attackTime, float sustainTime, float sustainLevel, float releaseTime);
  
  public native int doneAfter(float seconds);
    
  // Filters
    
  public native int[] highPassPlay(int[] input, float freq);

  public native int[] lowPassPlay(int[] input, float freq);
  
  public native int[] bandPassPlay(int[] input, float freq, float bw);

  public native void filterSet(float freq, int nodeId);

  public native void filterBwSet(float freq, float bw, int nodeId);


  // Delay

  public native int[] delayPlay(int[] input, float maxDelayTime, float delayTime, float feedBack);
  
  public native void delaySet(float delayTime, float feedBack, int nodeId);

  // Reverb

  public native int[] reverbPlay(int[] input, float room, float damp, float wet);
  
  public native void reverbSet(float room, float damp, float wet, int nodeId);
  
  // Patch cable
  
  //public native int out(int[] in, int[] out);
  
  // Pan + Out
  
  public native void out(int out, int[] nodeId);  
  
  // connect
  
  // public native void connect(int nodeIdOut, int nodeIdIn);
  
  // Descriptors
  
  // Amplitude Follower
  
  public native long amplitude(int[] nodeId);
  
  public native float poll_amplitude(long ptr);
  
  public native void destroy_amplitude(long ptr);
  
  // FFT
  
  public native long fft(int[] nodeId, int fftSize);
  
  public native float[] poll_fft(long ptr);  
  
  public native void destroy_fft(long ptr);

}
