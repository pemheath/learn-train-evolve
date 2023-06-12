package learntrainevolve.models;

import com.google.api.services.calendar.model.Event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonApiResponse {
    private List<Event> data;

    public JsonApiResponse() {}

    public JsonApiResponse(List<Event> data) {
        this.data = data;}


    public List<Event> getData() {
        return data;
    }

    public void setData(List<Event> data) {
        this.data = data;
    }

}
