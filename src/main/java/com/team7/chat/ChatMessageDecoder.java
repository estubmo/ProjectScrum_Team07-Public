package com.team7.chat;

import java.io.StringReader;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {

    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public ChatMessage decode(final String textMessage) throws DecodeException {
        ChatMessage chatMessage = new ChatMessage();
        JsonObject obj = Json.createReader(new StringReader(textMessage))
                .readObject();
        chatMessage.setMessage(fiksQuote(obj.getString("message")));
        chatMessage.setSender(obj.getString("sender"));
        chatMessage.setReceived(new Date());
        return chatMessage;
    }

    private String fiksQuote(String tekst) {
        String ret = "";
        for (int i = 0; i < tekst.length(); i++) {
            switch (tekst.charAt(i)) {
                case '"':
                    ret += "&quot;";
                    break;
                case '<':
                    ret += "&lt;";
                    break;
                case '>':
                    ret += "&gt;";
                    break;
                /*case '$':
                 ret += "&#36;";
                 break;*/
                default:
                    ret += tekst.charAt(i);
                    break;
            }
        }
        return ret;
    }

    @Override
    public boolean willDecode(final String s
    ) {
        return true;
    }
}
