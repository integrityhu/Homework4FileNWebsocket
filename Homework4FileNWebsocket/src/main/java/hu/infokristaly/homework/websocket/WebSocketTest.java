package hu.infokristaly.homework.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
/*
 * http://stackoverflow.com/questions/17936440/accessing-httpsession-from-httpservletrequest-in-a-web-socket-serverendpoint
 */
@ServerEndpoint("/websocket")
public class WebSocketTest {

	@OnMessage
	public void onMessage(String message, Session session) throws IOException,
			InterruptedException {	    
		System.out.println("User input: " + message);
		session.getBasicRemote().sendText("Hello world Mr. " + message);
		// Sending message to client each 1 second
		for (int i = 0; i <= 25; i++) {
			session.getBasicRemote().sendText(i + " Message from server");
			Thread.sleep(1000);

		}
	}

	@OnOpen
	public void onOpen() {
		System.out.println("Client connected");
	}

	@OnClose
	public void onClose() {
		System.out.println("Connection closed");
	}
}
