package com.hcl.JacksonStreamer;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonStreamDataBinder implements Iterator<Headline>, Closeable {
    private final InputStream inputStream;
    private JsonParser jsonParser;
    private Headline nextObject;

    public JsonStreamDataBinder(final URL url) {
        try {
            this.inputStream = url.openStream();
        } catch (IOException e) {
            throw new RuntimeException("There was a problem creating a stream from the given URL: " + e.getMessage(), e);
        }
        this.init();
    }

    public JsonStreamDataBinder(final InputStream inputStream) {
        this.inputStream = inputStream;
        this.init();
    }

    public void init() {
        this.initJsonParser();
        this.initFirstElement();
    }


    private void initJsonParser() {
        final JsonFactory jsonFactory = new ObjectMapper().getFactory();

        try {
            this.jsonParser = jsonFactory.createParser(this.inputStream);
        } catch (final IOException e) {
            throw new RuntimeException("There was a problem setting up the JsonParser: " + e.getMessage(), e);
        }
    }

    private void initFirstElement() {
        try {
            final JsonToken firstToken = this.jsonParser.nextToken();
            if (firstToken != JsonToken.START_ARRAY) {
                if (firstToken != JsonToken.START_OBJECT) {
                    throw new IllegalStateException("Invalid first token. Token found: " + firstToken);
                }
                this.nextObject = this.jsonParser.readValueAs(Headline.class);
                return;
            }
            this.initNextObject();
        } catch (final Exception e) {
            throw new RuntimeException("There was a problem initializing the first element of the Json Structure: " + e.getMessage(), e);
        }

    }

    private void initNextObject() {
        try {
            final JsonToken nextToken = this.jsonParser.nextToken();

            if ((nextToken == JsonToken.END_ARRAY) || (nextToken == null)) {
                this.nextObject = null;
                return;
            }

            if (nextToken != JsonToken.START_OBJECT) {
                throw new IllegalStateException("The next token of Json structure was expected to be a start object token, but it was: " + nextToken);
            }

            this.nextObject = this.jsonParser.readValueAs(Headline.class);
            if (this.nextObject == null) {
                throw new IllegalStateException("The next parsed object of the Json structure was null");
            }
        } catch (final Exception e) {
            throw new RuntimeException("There was a problem initializing the next Object: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean hasNext() {
        return this.nextObject != null;
    }

    @Override
    public Headline next() {
        final Headline currentNextObject = this.nextObject;
        this.initNextObject();
        return currentNextObject;
    }

    @Override
    public void close() throws IOException {
        this.jsonParser.close();
    }

}