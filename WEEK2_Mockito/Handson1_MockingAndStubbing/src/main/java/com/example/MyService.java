package com.example;

public class MyService {
	private ExtAPI externalApi;

    public MyService(ExtAPI externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }
}
