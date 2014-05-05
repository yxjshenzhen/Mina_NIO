package com.you.mina.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class TimeClientHandler extends IoHandlerAdapter {

	public TimeClientHandler() { 
		
	} 
	@Override 
	public void messageReceived(IoSession session, Object message) throws Exception { 
		System.out.println(message);//显示接收到的消息 
	}

}
