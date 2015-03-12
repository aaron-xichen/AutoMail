/**
 * Created by cxlyc007 on 3/11/15.
 */

public class Record{
    private String title;
    private int id;
    private String correctness;
    private String difficulty;
    private String url;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCorrectness() {
        return correctness;
    }

    public void setCorrectness(String correctness) {
        this.correctness = correctness;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Record(Record that){
        if(null == that)
            return;
        this.title = that.getTitle();
        this.id = that.id;
        this.correctness = that.getCorrectness();
        this.difficulty = that.getDifficulty();
        this.url = that.getUrl();
    }

    public Record(String record){
        String [] fields = record.split(";");
        if(fields.length==5){
            this.title = fields[0];
            this.id = Integer.parseInt(fields[1]);
            this.correctness = fields[2];
            this.difficulty = fields[3];
            this.url = fields[4];
        }
    }

    public Record(String title, int id, String correctness, String difficulty, String url) {
        this.title = title;
        this.id = id;
        this.correctness = correctness;
        this.difficulty = difficulty;
        this.url = url;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(title + ";");
        sb.append(id +";");
        sb.append(correctness + ";");
        sb.append(difficulty + ";");
        sb.append(url);
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;

        Record record = (Record) o;

        if (id != record.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public String format(){
         String html = "<html>" +
                 "<head></head>"+
                 "<body>"+
                 "<ul>"+
                 "<li><font color=\"red\">Problem Name: </font>" + title + "</li>"+
                 "<li><font color=\"red\">Problem Id: </font>" + id + "</li>"+
                 "<li><font color=\"red\">Correctness: </font>" + correctness + "</li>"+
                 "<li><font color=\"red\">Difficulty: </font>" + difficulty + "</li>"+
                 "<li><font color=\"red\">Url: </font>" + url + "</li>"+
                 "</ul>"+
                 "</body>"+
                 "</html>";
        return html;
    }
}