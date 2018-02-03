package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("98a1ff413f343205779b2db9690244ca5d545a7c");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("epishev", "java_pft")).commits();
    for (RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
