package com.you.mina.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class TimeClientHandler extends IoHandlerAdapter {

	public TimeClientHandler() { 
		
	} 
	@Override 
	public void messageReceived(IoSession session, Object message) throws Exception { 
		//显示接收到的消息 
		System.out.println(message);
	}

}
