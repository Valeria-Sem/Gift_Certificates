package com.epam.esm.repository;

public class RepoException extends Exception {
    public RepoException() {
        super();
    }

    public RepoException(String message) {
        super(message);
    }

    public RepoException(Exception e) {
        super(e);
    }

    public RepoException(String message, Exception e) {
        super(message, e);
    }
}
