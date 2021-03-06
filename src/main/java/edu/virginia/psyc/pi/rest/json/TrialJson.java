package edu.virginia.psyc.pi.rest.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dan
 * Date: 3/10/14
 * Time: 10:28 PM
 * An Java Object representation of data returned from PIPlayer.  This representation
 * is designed for storing in a relational database.
 */
public class TrialJson {

    private long id;
    private int log_serial;
    private String trial_id;
    private String name;
    private String responseHandle;
    private int latency;
    private List<String> stimuli;
    private List<String> media;
    private Map<String,String> data;


    @Override
    public String toString() {
        return "TrialJson{" +
                "log_serial=" + log_serial +
                ", trial_id=" + trial_id +
                ", name='" + name + '\'' +
                ", responseHandle='" + responseHandle + '\'' +
                ", stimuli=" + stimuli +
                ", media=" + media +
                ", data=" + data +
                '}';
    }

    /**
     * Retruns the headers of an interpretation report in the order they
     * should occur.
     * @return
     */
    public static List<String> interpretationReportHeaders() {
        List<String> headers = new ArrayList<String>();
        headers.add("session");
        headers.add("trial");
        headers.add("positive");
        headers.add("correct");
        headers.add("paragraph");
        headers.add("question");
        return headers;
    }

    public Map<String,String> toInterpretationReport() {

        Map<String,String> report = new HashMap<String, String>();

        // Return an empty row for invalid results.
        if(!this.name.equals("posneg")) return report;

        boolean isPositive = Boolean.valueOf(data.get("positive"));
        boolean correct;
        String  response = data.get("questionResponse");
        correct = (isPositive && response.equals("yes")) ||
                  (!isPositive && response.equals("no"));

        String base = id + "," + log_serial + "," + trial_id + ","
                        + data.get("questionResponse") + ","
                        + data.get("positive") + ","
                        + data.get("paragraph") + ","
                        + data.get("question");

        report.put("session",    log_serial+"");
        report.put("trial",      trial_id);
        report.put("positive",   data.get("positive"));
        report.put("correct",    correct + "");
        report.put("paragraph",  data.get("paragraph"));
        report.put("question",   data.get("question"));

        return report;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLog_serial() {
        return log_serial;
    }

    public void setLog_serial(int log_serial) {
        this.log_serial = log_serial;
    }

    public String getTrial_id() {
        return trial_id;
    }

    public void setTrial_id(String trial_id) {
        this.trial_id = trial_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponseHandle() {
        return responseHandle;
    }

    public void setResponseHandle(String responseHandle) {
        this.responseHandle = responseHandle;
    }

    public int getLatency() {
        return latency;
    }

    public void setLatency(int latency) {
        this.latency = latency;
    }

    public List<String> getStimuli() {
        return stimuli;
    }

    public void setStimuli(List<String> stimuli) {
        this.stimuli = stimuli;
    }

    public List<String> getMedia() {
        return media;
    }

    public void setMedia(List<String> media) {
        this.media = media;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

}