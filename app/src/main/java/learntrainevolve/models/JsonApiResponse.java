package learntrainevolve.models;

import com.google.api.services.calendar.model.Event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * A model to assist Jackson in deserializing response returned from Google into Event objects
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonApiResponse {

    private List<Event> items;


    public List<Event> getItems() {
        return items;
    }

    public void setItems(List<Event> items) {
        this.items = items;
    }



}