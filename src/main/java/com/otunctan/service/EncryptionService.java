package com.otunctan.service;

public interface EncryptionService {

    String encrypt(String rawText);

    String decrypt(String encrptedText);
}
