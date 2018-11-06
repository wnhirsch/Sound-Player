package APIProblems;
import org.jfugue.player.Player;
import org.jfugue.temporal.TemporalPLP;
import org.staccato.StaccatoParser;
import org.staccato.StaccatoParserListener;


import org.jfugue.pattern.Pattern;


public class TemporalExample {
    private static final String MUSIC = "T120 X[Volume]=2500 I0 A5 I0 R5 I0 B5 I0 R5 I0 C5 I0 R5 T70 I0 R5 I0 R5 I0 R5 I0 R5 T120 I0 R5 I0 A5 I0 R5 I0 B5 I0 R5 I0 C5";


    private static final long TEMPORAL_DELAY = 0;

    public static void main(String[] args) {
        // Part 1. Parse the original music
        StaccatoParser parser = new StaccatoParser();
        TemporalPLP plp = new TemporalPLP();
        parser.addParserListener(plp);
        parser.parse(MUSIC);

        // Part 2. Send the events from Part 1, and play the original music with a delay
        myParserListener dpl = new myParserListener();
        plp.addParserListener(dpl);
        new Player().delayPlay(TEMPORAL_DELAY, MUSIC);
        plp.parse();
    }
}