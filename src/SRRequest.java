import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class SRRequest
{
    private HttpURLConnection con;
    private String time;
    private String runner;

    public SRRequest() throws ProtocolException {
        con.setRequestMethod("GET");
    }

    public String getRunner() {
        return runner;
    }

    public String getTime() {
        return time;
    }

    public void setRunner(String runner) {
        this.runner = runner;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String[] requestWR()
    {

        requestRun(scanTitle(), ); //@todo TTVRequest Methode für den Titel in scanTitle einfügen. TTVRequest Methode für das Spiel als zweiten Parameter einfügen
        requestRunner(this.getRunner());
        return new String[] {this.getRunner(), this.getTime()};
    }

    private void requestRun(String category, String game) throws IOException {
        URL url = new URL("https://www.speedrun.com/api/v1/leaderboards/" + game + "/category/" + category + "?top=1");
        con = (HttpURLConnection) url.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        //@todo Rückgabe nach Zeit und RunnerID durchscannen, damit man diese dann setzt und in requestWR zurück geben kann
    }

    private void requestRunner(String runner) throws IOException {
        URL url = new URL("https://www.speedrun.com/api/v1/Users/" + runner);
        con = (HttpURLConnection) url.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        //@todo Rückgabe nach RunnerNamen durchsuchen, damit man diesen dann setzten und in requestWR zurück geben kann
    }

    private String scanTitle(String title)
    {
        //@todo Titel nach [ ] durchsuchen und darin dann Kategorie raussuchen
    }


}
