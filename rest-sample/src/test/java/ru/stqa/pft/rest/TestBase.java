package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {

  protected Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");

    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType() );
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");

  }

  protected int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json?limit=1000")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                      new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private boolean isIssueOpen(int issueId) throws IOException {
    String getIssueUrl = "http://demo.bugify.com/api/issues/" + String.valueOf(issueId) + ".json";
    String jsonResponse = getExecutor().execute(Request.Get(getIssueUrl)).returnContent().asString();
    String status = new JsonParser().parse(jsonResponse).getAsJsonObject()
            .get("issues").getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsString();
    return !status.equals("Resolved") && !status.equals("Closed");
  }
}
