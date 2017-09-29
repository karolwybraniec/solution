package com.github;


import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class Config {

    private Yaml yaml;
    private Map<?, ?> config;

    public Config() throws FileNotFoundException {
        this.yaml = new Yaml();
        File yamlFile = new File("src/test/config/config.yml");
        config =  (Map<?, ?>) this.yaml.load(new FileReader(new File(String.valueOf(yamlFile))));
    }


    public String getGitUsername() {
        return (String) this.config.get("gitUsername");
    }

    public String getGitPassword() {
        return (String) this.config.get("gitPassword");
    }

    public String getGitRemoteUrl() {
        return (String) this.config.get("gitRemoteUrl");
    }

    public String getGitRemoteName() {
        return (String) this.config.get("gitRemoteName");
    }

    public String getGitWorkingBranch() {
        return (String) this.config.get("gitWorkingBranch");
    }

    public String getLocalGitRepoDir() {
        return (String) this.config.get("localGitRepoDir");
    }

    public String getGitRepositoryName() {
        return (String) this.config.get("gitRepositoryName");
    }

    public String getGitPullRequetsNumber() {
        return (String) this.config.get("gitPullRequetsNumber");
    }

    public String getGithubUrl () { return (String) this.config.get("githubUrl"); }

    public String geGitProfileUrl () { return (String) this.config.get("gitProfileUrl"); }
}
