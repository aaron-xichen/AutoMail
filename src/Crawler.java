import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.*;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cxlyc007 on 3/11/15.
 */
public class Crawler {
    public static void main(String[] args) {
        String root = "https://leetcode.com/";
        String problemSet = root + "problemset/algorithms/";

        Set<Record> records = new HashSet<Record>();
        Parser parser  = new Parser();
        try {
            parser.setEncoding("utf-8");
            parser.setURL(problemSet);

            NodeFilter primary = new TagNameFilter("tr");
            NodeList nodes = parser.extractAllNodesThatMatch(primary);
            if (null != nodes) {
                for (int i = 0; i < nodes.size(); i++) {
                    Node textNode = (Node) nodes.elementAt(i);
                    String html = textNode.toHtml();
                    if (html.contains("<i") || html.contains("header-id"))
                        continue;
                    Node problemIdNode = textNode.getFirstChild().getNextSibling().getNextSibling().getNextSibling();
                    int problemId = Integer.parseInt(problemIdNode.toPlainTextString());

                    Node problemNode = problemIdNode.getNextSibling().getNextSibling();
                    String problemString = problemNode.toHtml();
                    Pattern p1 = Pattern.compile("href=[\\s\\S]+?>");
                    Matcher m1 = p1.matcher(problemString);
                    String url = "";
                    if (m1.find()) {
                        url = root + m1.group(0).substring(7, m1.group(0).length() - 3);
                    }

                    String problemName = problemNode.toPlainTextString().trim();

                    Node correctnessNode = problemNode.getNextSibling().getNextSibling();
                    String correctness = correctnessNode.toPlainTextString();

                    Node difficultyNode = correctnessNode.getNextSibling().getNextSibling();
                    String difficulty = difficultyNode.toPlainTextString();

                    Record newRecord = new Record(problemName, problemId, correctness, difficulty, url);
                    records.add(newRecord);
                }
            }

            // save to file
            String saveFile = Config.getRecordsPath();

            PrintWriter writer = new PrintWriter(saveFile, "UTF-8");
            for (Record r : records) {
                writer.println(r.toString());
            }
            writer.close();
            System.out.println("Fetch #record:" + records.size());
        } catch (ParserException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}
