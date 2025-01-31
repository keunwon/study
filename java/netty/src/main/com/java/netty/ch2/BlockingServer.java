package com.java.netty.ch2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockingServer {

	public static void main(String[] args) throws IOException {
		BlockingServer server = new BlockingServer();
		server.run();
	}
	
	private void run() throws IOException {
		ServerSocket server = new ServerSocket(8888);
		
		while (true) {
			Socket socket = server.accept();  // 디버그 시 멈춤 (클라이언트가 없으면 아무런 동작을 하지 않음)
			System.out.println("클라이언트 연결 성공");
			
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			
			while(true) {
				try {
					int request = in.read();  // 클라이언트에서 데이터를  입력해줘야함
					out.write(request);
				}
				catch (IOException e) {
					break;
				}
			}
		}
	}
}
