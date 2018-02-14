package com.engagetech.clients;

public interface Client {
    <T> T sendGetRequest(String destinationUrl, Class<T> classType);
}
