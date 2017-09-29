package com.github;

import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.*;
import java.util.UUID;


public class GitClient {

    private CredentialsProvider credentialsProvider;
    private Git git;
    private Repository repository;
    private Config config;

    private String lastCommitHash;

    public GitClient() throws IOException {
        config = new Config();
        credentialsProvider = new UsernamePasswordCredentialsProvider(
                this.config.getGitUsername(),
                this.config.getGitPassword()
        );
        repository = getRepository();
        git = new Git(repository);

        StoredConfig storedConfig = git.getRepository().getConfig();
        storedConfig.setString("remote",
                this.config.getGitRemoteName(),
                "url",
                this.config.getGitRemoteUrl()
        );
        storedConfig.save();
    }

    public void add() throws GitAPIException, IOException {
        makeChanges();
        this.git.add().addFilepattern("testfile.txt").call();
    }

    public void commit() throws IOException, GitAPIException {
        this.git.commit().setAll(true).setMessage("Commit all changes").call();
    }

    public void createBranch() throws GitAPIException, FileNotFoundException {
        String branch = this.config.getGitWorkingBranch();
        git.branchCreate().setName(branch)
                .setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.NOTRACK)
                .setStartPoint("heads/" + "master")
                .setForce(true)
                .call();
        git.checkout().setName(branch).call();
    }

    public void push() throws GitAPIException, IOException {
        this.git.push()
                .setCredentialsProvider(credentialsProvider)
                .setRemote(this.config.getGitRemoteName())
                .setForce(true)
                .call();
        this.setLastCommitHash(this.lastCommitHash);
    }

    public String getLastCommitHash() throws IOException {
        git.log().all();
        ObjectId lastCommitId = repository.resolve(Constants.HEAD);
        RevWalk revWalk = new RevWalk(repository);
        RevCommit lastCommit = revWalk.parseCommit(lastCommitId);
        return lastCommit.toString().trim().substring(7, 14);
    }

    private void makeChanges() throws IOException {
        File myfile = new File(repository.getDirectory().getParent(),
                "testfile" + UUID.randomUUID().toString().replace("-", "") + ".txt");
        if(!myfile.exists()) {
            if (!myfile.createNewFile()) {
                throw new IOException("Unable to create file " + myfile);
            }
        }
        PrintWriter writer = new PrintWriter(myfile, "UTF-8");
        writer.println(UUID.randomUUID().toString().replace("-", ""));
        writer.close();
    }

    private Repository getRepository() throws IOException {
        File dir = new File(this.config.getLocalGitRepoDir());
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new IOException("Directory has not been created!");
            }
            Repository repository = FileRepositoryBuilder.create(new File(dir, ".git"));
            repository.create();

            return repository;
        }
        File gitDir = new File("src/gitrepository/.git");
        return new FileRepositoryBuilder().setGitDir(gitDir).build();
    }

    public String getRepoUrl() throws FileNotFoundException {
        return this.config.getGitRemoteUrl() + "/tree/" + this.getBranch();
    }

    public String getBranch() {
        return this.config.getGitWorkingBranch();
    }

    private void setLastCommitHash(String lastCommitHash) {
        this.lastCommitHash = lastCommitHash;
    }

    public String getPullReqestUrl() {
        return this.config.getGitRemoteUrl() + "/pull/" + this.config.getGitPullRequetsNumber();
    }
}

